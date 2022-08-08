package jjfactory.selecttuning.dto.req;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdate {
    private String name;
    private int age;
    private String location;

    @Builder
    public MemberUpdate(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }
}
