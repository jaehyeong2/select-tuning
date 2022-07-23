package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

//    @JoinColumn(name = "delivery_id")
//    @OneToOne(fetch = FetchType.LAZY)
//    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
