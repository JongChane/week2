package kr.co.chan.week2.domain.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductRequest {
  @NotNull
  String name;
  @NotNull
  String description;
  @NotNull
  @PositiveOrZero
  Integer price;
  @NotNull
  @PositiveOrZero
  Integer stock;
  @NotNull
  Long categoryId;
}
