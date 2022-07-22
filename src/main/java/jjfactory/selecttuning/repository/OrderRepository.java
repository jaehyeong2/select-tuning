package jjfactory.selecttuning.repository;

import jjfactory.selecttuning.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
