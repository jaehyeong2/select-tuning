package jjfactory.selecttuning.dto.res;

import jjfactory.selecttuning.domain.orders.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class OrderRes {
    private Long orderId;
    private Long memberId;
    private String memberName;
    private int totalPrice;
    private LocalDateTime orderDate;

    public OrderRes(Long orderId, Long memberId, String memberName, int totalPrice, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public OrderRes(Order order) {
        this.totalPrice = order.getTotalPrice();
        this.orderDate = order.getOrderDate();
        this.orderId = order.getId();
        this.memberId = order.getMember().getId();
        this.memberName = order.getMember().getName();
    }
}
