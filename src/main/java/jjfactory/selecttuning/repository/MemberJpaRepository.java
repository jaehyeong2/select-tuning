package jjfactory.selecttuning.repository;

import jjfactory.selecttuning.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {
}
