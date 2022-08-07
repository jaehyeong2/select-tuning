package jjfactory.selecttuning.repository.post;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.dto.req.MemberDto;
import jjfactory.selecttuning.dto.res.MemberRes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member find(Long id){
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<MemberRes> findAll(){
        List<Member> members = em.createQuery("select * from Member m", Member.class)
                .getResultList();
        return members.stream().map(MemberRes::new).collect(Collectors.toList());
    }

    public void save(MemberDto dto){
        Member member = Member.create(dto);
        em.persist(member);
    }

}
