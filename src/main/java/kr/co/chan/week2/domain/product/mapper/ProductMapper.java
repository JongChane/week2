package kr.co.chan.week2.domain.product.mapper;

import kr.co.chan.week2.domain.product.dto.ProductResponse;
import kr.co.chan.week2.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductResponse toProductResponse(Product product);
}
