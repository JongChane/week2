package kr.co.chan.week2.domain.product.repository;

import static kr.co.chan.week2.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.co.chan.week2.common.response.PageInfo;
import kr.co.chan.week2.common.response.PageResponse;
import kr.co.chan.week2.domain.product.dto.ProductSearchRequest;
import kr.co.chan.week2.domain.product.dto.ProductSearchResponse;
import kr.co.chan.week2.domain.product.dto.QProductSearchResponse;
import kr.co.chan.week2.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
  private final JPAQueryFactory queryFactory;

  public PageResponse<ProductSearchResponse> findProducts(ProductSearchRequest productSearchRequest,
      String field, String order) {

    List<ProductSearchResponse> content = queryFactory.select(new QProductSearchResponse(
            product.id,
            product.category.id,
            product.name,
            product.price,
            product.stock,
            product.createdAt
        ))
        .from(product)
        .where(
          eqCategory(productSearchRequest.getCategory()),
          goePrice(productSearchRequest.getMinPrice()),
          loePrice(productSearchRequest.getMaxPrice())
        )
        .orderBy(getOrderSpecifier(field, order))
        .offset(productSearchRequest.getPage())
        .limit(productSearchRequest.getSize())
        .fetch();

    long total = queryFactory
        .select(product.count())
        .from(product)
        .where(
            eqCategory(productSearchRequest.getCategory()),
            goePrice(productSearchRequest.getMinPrice()),
            loePrice(productSearchRequest.getMaxPrice())
        )
        .fetchOne();

    int totalPages = (int) Math.ceil((double) total / productSearchRequest.getSize());

    return new PageResponse<>(
        content,
        new PageInfo(productSearchRequest.getPage(), productSearchRequest.getSize()),
        totalPages,
        total
    );
  }

  private BooleanExpression eqCategory(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression goePrice(Integer minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression loePrice(Integer maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }

  private OrderSpecifier<?> getOrderSpecifier(String field, String order) {
    System.out.println(field + " " + order);
    PathBuilder<Product> path = new PathBuilder<>(Product.class, "product");
    Order sortOrder = order.equalsIgnoreCase("desc") ? Order.DESC : Order.ASC;
    return new OrderSpecifier<>(sortOrder, path.getString(field));
  }

}
