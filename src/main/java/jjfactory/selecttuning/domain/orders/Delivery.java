package jjfactory.selecttuning.domain.orders;

import jjfactory.selecttuning.domain.BaseTimeEntity;
import jjfactory.selecttuning.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
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

    private String location;

    private DeliveryStatus status;

    @Builder
    public Delivery(String location, DeliveryStatus status) {
        this.location = location;
        this.status = status;
    }

    public static Delivery create(Member member){
        return Delivery.builder()
                .location(member.getLocation())
                .status(DeliveryStatus.READY)
                .build();
    }

    public void updateOrder(Order order) {
        this.order = order;
    }
}
