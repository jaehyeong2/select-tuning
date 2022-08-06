package jjfactory.selecttuning.repository.nba;

import jjfactory.selecttuning.domain.nba.Player;
import jjfactory.selecttuning.domain.nba.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void findAll(){
        Team teamA = Team.builder().name("TeamA").build();
        Team teamB = Team.builder().name("TeamB").build();
        Team teamC = Team.builder().name("TeamC").build();
        Team teamD = Team.builder().name("TeamD").build();
        teamRepository.saveAll(List.of(teamA,teamB,teamC,teamD));

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Player p = Player.builder().name("person" + i).team(teamA).build();
            playerList.add(p);
        }

        playerRepository.saveAll(playerList);

        teamRepository.findAll();
    }

}