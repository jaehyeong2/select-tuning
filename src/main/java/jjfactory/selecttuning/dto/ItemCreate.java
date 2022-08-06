package jjfactory.selecttuning.dto;

import jjfactory.selecttuning.domain.orders.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemCreate {
    private int stockQuantity;
    private int price;
    private String name;

    @Builder
    public ItemCreate(int stockQuantity, int price, String name) {
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.name = name;
    }

    public ItemCreate(Item item) {
        this.stockQuantity = item.getStockQuantity();
        this.price = item.getPrice();
        this.name = item.getName();
    }

}
