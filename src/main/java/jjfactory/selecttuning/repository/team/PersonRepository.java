package jjfactory.selecttuning.repository.team;

import jjfactory.selecttuning.domain.team.Person;
import jjfactory.selecttuning.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
