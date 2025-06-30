package kr.co.chan.week2.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.co.chan.week2.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

  Long id;

  String name;

  String description;

  Integer price;

  Integer stock;

  Category category;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  LocalDateTime createdAt;

  @QueryProjection
  public ProductResponse(
      Long id,
      String name,
      String description,
      BigDecimal price,
      Integer stock,
      LocalDateTime createdAt,
      Category category) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = (price != null) ? price.intValue() : null;
    this.stock = stock;
    this.createdAt = createdAt;
    this.category = category;
  }
}
