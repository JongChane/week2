package kr.co.chan.week2.domain.product.controller;

import kr.co.chan.week2.common.response.ApiResponse;
import kr.co.chan.week2.common.response.PageResponse;
import kr.co.chan.week2.domain.product.dto.ProductSearchRequest;
import kr.co.chan.week2.domain.product.dto.ProductSearchResponse;
import kr.co.chan.week2.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<PageResponse<ProductSearchResponse>> findProducts(@ModelAttribute ProductSearchRequest productSearchRequest) {
    return ApiResponse.success(productService.findProducts(productSearchRequest));
  }
}
