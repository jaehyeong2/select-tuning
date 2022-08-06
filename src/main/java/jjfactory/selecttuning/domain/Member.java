package jjfactory.selecttuning.domain;

import jjfactory.selecttuning.domain.orders.Order;
import jjfactory.selecttuning.dto.MemberDto;
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
public class Member {

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

    public static Member create(MemberDto dto){
        return Member.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }
}
