package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.domain.BaseTimeEntity;
import jjfactory.selecttuning.dto.ItemCreate;
import jjfactory.selecttuning.exception.NoEnoughStockException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    private String name;

    private int price;
    private int stockQuantity;

    @Builder
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Item create(ItemCreate dto){
        return Item.builder()
                .name(dto.getName())
                .stockQuantity(dto.getStockQuantity())
                .price(dto.getPrice())
                .build();
    }

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int rest = this.stockQuantity - quantity;
        if(rest<0){
            throw new NoEnoughStockException("need more stock");
        }
        this.stockQuantity = rest;
    }
}
