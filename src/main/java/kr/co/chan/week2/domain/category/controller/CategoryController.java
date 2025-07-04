package kr.co.chan.week2.domain.category.controller;

import java.util.List;
import kr.co.chan.week2.common.response.ApiResponse;
import kr.co.chan.week2.domain.category.dto.CategoryHierarchyResponse;
import kr.co.chan.week2.domain.category.dto.CategoryResponse;
import kr.co.chan.week2.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping("hierarchy")
  public ApiResponse<List<CategoryHierarchyResponse>> getHierarchy() {
    return ApiResponse.success(categoryService.getHierarchy());
  }
}
