package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.dto.req.OrderItemCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int price; // 할인, 쿠폰등이 잇을 수 잇으므로 item의 필드와 따로가져가는게 맞음
    private int count;

    @Builder
    public OrderItem(int price, int count,Item item) {
        this.price = price;
        this.count = count;
        this.item = item;
    }

    public static OrderItem create(OrderItemCreate dto, Item item){
        OrderItem orderItem = OrderItem.builder()
                .price(dto.getPrice())
                .count(dto.getCount())
                .item(item)
                .build();

        item.removeStock(dto.getCount());
        return orderItem;
    }

    public void updateOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
        getItem().addStock(count); // 재고수량 원상복구
    }

    public int getTotalPrice() {
        return getPrice() * getCount();
    }
}
