package jjfactory.selecttuning.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
    private Long memberId;
    private String name;
    private int age;
    private String location;

    @Builder
    public MemberDto(Long memberId, String name, int age, String location) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.location = location;
    }
}
