package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "delivery_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Member member, OrderStatus orderStatus,LocalDateTime orderDate) {
        this.member = member;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public void updateDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.updateOrder(this);
    }

    void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.updateOrder(this);
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static Order create(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.builder()
                .member(member)
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        order.updateDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public void orderCancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소불가");
        }
        changeOrderStatus(OrderStatus.CANCEL);
        orderItems.forEach(OrderItem::cancel);
    }

    public int getTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
