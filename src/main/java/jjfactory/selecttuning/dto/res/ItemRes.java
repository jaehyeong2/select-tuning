package jjfactory.selecttuning.dto.res;

import jjfactory.selecttuning.domain.orders.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemRes {
    private int stockQuantity;
    private int price;
    private String name;

    @Builder
    public ItemRes(int stockQuantity, int price, String name) {
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.name = name;
    }

    public ItemRes(Item item) {
        this.stockQuantity = item.getStockQuantity();
        this.price = item.getPrice();
        this.name = item.getName();
    }

}
