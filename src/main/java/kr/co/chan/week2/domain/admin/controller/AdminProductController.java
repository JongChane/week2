package kr.co.chan.week2.domain.admin.controller;

import jakarta.validation.Valid;
import kr.co.chan.week2.common.response.ApiResponse;
import kr.co.chan.week2.domain.admin.dto.AdminProductRequest;
import kr.co.chan.week2.domain.admin.service.AdminProductResponse;
import kr.co.chan.week2.domain.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

  private final AdminProductService adminProductService;

  @PostMapping
  public ResponseEntity<ApiResponse<AdminProductResponse>> createProduct(@Valid @RequestBody AdminProductRequest request) {
    return ApiResponse.created(adminProductService.createProduct(request));
  }
}
