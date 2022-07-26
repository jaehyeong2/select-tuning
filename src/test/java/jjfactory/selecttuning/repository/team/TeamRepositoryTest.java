package jjfactory.selecttuning.repository.team;

import jjfactory.selecttuning.domain.team.Person;
import jjfactory.selecttuning.domain.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    void findAll(){
        Team teamA = Team.builder().name("TeamA").build();
        Team teamB = Team.builder().name("TeamB").build();
        Team teamC = Team.builder().name("TeamC").build();
        Team teamD = Team.builder().name("TeamD").build();
        teamRepository.saveAll(List.of(teamA,teamB,teamC,teamD));

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = Person.builder().name("person" + i).team(teamA).build();
            personList.add(p);
        }

        personRepository.saveAll(personList);

        teamRepository.findAll();
    }

}