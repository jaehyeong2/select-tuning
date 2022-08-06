package jjfactory.selecttuning.repository.orders;

import jjfactory.selecttuning.domain.orders.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
