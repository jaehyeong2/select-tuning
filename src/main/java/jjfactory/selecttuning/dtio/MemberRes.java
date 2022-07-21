package jjfactory.selecttuning.dtio;

import jjfactory.selecttuning.domain.Member;
import lombok.Getter;

@Getter
public class MemberRes {
    private String name;
    private int age;

    public MemberRes(Member member) {
        this.name = member.getName();
        this.age = member.getAge();
    }
}
