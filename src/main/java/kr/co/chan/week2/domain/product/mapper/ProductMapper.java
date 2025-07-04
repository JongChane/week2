package kr.co.chan.week2.domain.product.mapper;

import kr.co.chan.week2.domain.category.dto.CategoryResponse;
import kr.co.chan.week2.domain.category.entity.Category;
import kr.co.chan.week2.domain.product.dto.ProductResponse;
import kr.co.chan.week2.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  CategoryResponse toCategoryResponse(Category category);

  ProductResponse toProductResponse(Product product);
}
