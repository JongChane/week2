package kr.co.chan.week2.domain.product.service;

import kr.co.chan.week2.common.exception.ServiceException;
import kr.co.chan.week2.common.exception.ServiceExceptionCode;
import kr.co.chan.week2.common.response.PageResponse;
import kr.co.chan.week2.domain.product.dto.ProductResponse;
import kr.co.chan.week2.domain.product.dto.ProductSearchRequest;
import kr.co.chan.week2.domain.product.dto.ProductSearchResponse;
import kr.co.chan.week2.domain.product.entity.Product;
import kr.co.chan.week2.domain.product.repository.ProductQueryRepository;
import kr.co.chan.week2.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;

  public PageResponse<ProductSearchResponse> findProducts(ProductSearchRequest productSearchRequest) {
    if(StringUtils.hasText(productSearchRequest.getSortBy())) {
      if(!productSearchRequest.getSortBy().contains(",")) throw new ServiceException(
          ServiceExceptionCode.NOT_FOUND_COMMA);
      String[] sortBy = productSearchRequest.getSortBy().split(",");
      String field = sortBy[0].contains("price") ? "price" : sortBy[0];
      String order = sortBy[1];
      return productQueryRepository.findProducts(productSearchRequest, field, order);
    }
    return productQueryRepository.findProducts(productSearchRequest, "createdAt", "desc");
  }


  public ProductResponse findProductById(Long productId) {
    Product product = getProduct(productId);

  }

  public Product getProduct(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
  }
}
