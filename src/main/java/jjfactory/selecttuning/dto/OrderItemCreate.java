package jjfactory.selecttuning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderItemCreate {
    private int price;
    private int count;

    public OrderItemCreate(int price, int count) {
        this.price = price;
        this.count = count;
    }
}
