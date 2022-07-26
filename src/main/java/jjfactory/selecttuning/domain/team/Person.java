package jjfactory.selecttuning.domain.team;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    private Team team;

    private String name;

    @Builder
    public Person(Team team, String name) {
        this.team = team;
        this.name = name;
    }
}
