package kr.co.chan.week2.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductSearchRequest {
  Long category;
  Integer minPrice;
  Integer maxPrice;
  Integer page = 0;
  Integer size = 10;
  String sortBy;
}
