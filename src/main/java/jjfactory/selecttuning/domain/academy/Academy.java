package jjfactory.selecttuning.domain.academy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Academy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    @Builder
    public Academy(String name) {
        this.name = name;
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
        subject.updateAcademy(this);
    }
}
