package kr.co.chan.week2.domain.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.chan.week2.domain.category.dto.CategoryHierarchyResponse;
import kr.co.chan.week2.domain.category.entity.Category;
import kr.co.chan.week2.domain.category.mapper.CategoryMapper;
import kr.co.chan.week2.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  public List<CategoryHierarchyResponse> getHierarchy() {
    Map<Long, CategoryHierarchyResponse> map = new HashMap<>();
    List<Category> list = categoryRepository.findAll();
    List<CategoryHierarchyResponse> dtos = categoryMapper.toHierarchyResponseList(list);
    List<CategoryHierarchyResponse> roots = new ArrayList<>();

    for (CategoryHierarchyResponse dto : dtos) {
      map.put(dto.getId(), dto);
    }

    // 트리 조립
    for (CategoryHierarchyResponse dto : dtos) {
      if (dto.getParentId() == null) {
        roots.add(dto);
      } else {
        CategoryHierarchyResponse parent = map.get(dto.getParentId());
        if (parent != null) {
          parent.getChildren().add(dto);
        }
      }
    }
    return roots;
  }
}
