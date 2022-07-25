package jjfactory.selecttuning.domain.academy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @JoinColumn(name = "academy_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Academy academy;

    @Builder
    public Subject(String name, Teacher teacher, Academy academy) {
        this.name = name;
        this.teacher = teacher;
        this.academy = academy;
    }

    public void updateAcademy(Academy academy) {
        this.academy = academy;
    }
}
