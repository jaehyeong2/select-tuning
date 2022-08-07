package jjfactory.selecttuning.dto.req;

import jjfactory.selecttuning.domain.orders.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    @Builder
    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}
