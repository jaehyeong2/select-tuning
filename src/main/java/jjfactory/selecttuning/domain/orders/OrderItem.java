package jjfactory.selecttuning.domain.orders;

import lombok.AccessLevel;
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

    private int price;
    private int count;

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
