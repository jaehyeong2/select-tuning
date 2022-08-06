package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.domain.BaseTimeEntity;
import jjfactory.selecttuning.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private DeliveryStatus status;

    public void updateOrder(Order order) {
        this.order = order;
    }
}
