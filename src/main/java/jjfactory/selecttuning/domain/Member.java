package jjfactory.selecttuning.domain;

import jjfactory.selecttuning.domain.orders.Order;
import jjfactory.selecttuning.dto.req.MemberCreate;
import jjfactory.selecttuning.dto.req.MemberUpdate;
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
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String location;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public static Member create(MemberCreate dto){
        return Member.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public void updateInfo(MemberUpdate dto) {
        this.name = dto.getName();
        this.age = dto.getAge();
        this.location = dto.getLocation();
    }

}
