package jjfactory.selecttuning.domain.academy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    private String name;
}
