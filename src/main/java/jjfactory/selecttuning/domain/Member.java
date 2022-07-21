package jjfactory.selecttuning.domain;

import jjfactory.selecttuning.dtio.MemberDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Member create(MemberDto dto){
        return Member.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .build();
    }
}
