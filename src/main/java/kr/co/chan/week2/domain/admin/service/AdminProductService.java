package kr.co.chan.week2.domain.admin.service;

import kr.co.chan.week2.common.exception.ServiceException;
import kr.co.chan.week2.common.exception.ServiceExceptionCode;
import kr.co.chan.week2.common.response.ApiResponse;
import kr.co.chan.week2.domain.admin.dto.AdminProductRequest;
import kr.co.chan.week2.domain.admin.mapper.AdminProductMapper;
import kr.co.chan.week2.domain.category.entity.Category;
import kr.co.chan.week2.domain.category.repository.CategoryRepository;
import kr.co.chan.week2.domain.product.entity.Product;
import kr.co.chan.week2.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {
  private final ProductRepository productRepository;
  private final AdminProductMapper adminProductMapper;
  private final CategoryRepository categoryRepository;

  public AdminProductResponse createProduct(AdminProductRequest request) {
    if(productRepository.findByName(request.getName()).isPresent()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATED_PRODUCT_NAME);
    }
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    Product product = productRepository.save(
        Product.builder()
            .category(category)
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .build());

    return AdminProductResponse.builder().productId(product.getId()).build();
  }


  public Product getProduct(String name) {
    return productRepository.findByName(name)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
  }

}
