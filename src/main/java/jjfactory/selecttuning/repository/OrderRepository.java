package jjfactory.selecttuning.repository;

import jjfactory.selecttuning.domain.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
