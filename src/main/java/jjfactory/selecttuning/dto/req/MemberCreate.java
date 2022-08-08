package jjfactory.selecttuning.dto.req;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreate {
    private Long memberId;
    private String name;
    private int age;
    private String location;

    @Builder
    public MemberCreate(Long memberId, String name, int age, String location) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.location = location;
    }
}
