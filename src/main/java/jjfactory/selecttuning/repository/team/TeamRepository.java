package jjfactory.selecttuning.repository.team;

import jjfactory.selecttuning.domain.team.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {

    @EntityGraph(attributePaths = "member")
    @Query("select t from Team t")
    List<Team> findAllTeam();
}
