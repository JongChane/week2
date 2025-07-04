package kr.co.chan.week2.domain.category.mapper;

import java.util.List;
import kr.co.chan.week2.domain.category.dto.CategoryHierarchyResponse;
import kr.co.chan.week2.domain.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  @Mapping(target = "parentId", source = "parent.id")
  CategoryHierarchyResponse toHierarchyResponse(Category category);

  List<CategoryHierarchyResponse> toHierarchyResponseList(List<Category> categories);

}
