package kr.co.chan.week2.domain.purchase.repository;

import kr.co.chan.week2.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

}
