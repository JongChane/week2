package kr.co.chan.week2.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyResponse {
  long id;
  String name;
  Long parentId;
  List<CategoryHierarchyResponse> children = new ArrayList<>();
}
