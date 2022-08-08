package jjfactory.selecttuning.service.orders;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.dto.req.MemberCreate;
import jjfactory.selecttuning.dto.req.MemberUpdate;
import jjfactory.selecttuning.dto.res.MemberRes;
import jjfactory.selecttuning.repository.post.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberRes findMember(Long memberId){
        Member member = memberRepository.find(memberId);
        return new MemberRes(member);
    }

    public Long create(MemberCreate dto){
        return memberRepository.save(dto);
    }

    public void updateMemberInfo(Long memberId, MemberUpdate dto){
        Member member = memberRepository.find(memberId);
        member.updateInfo(dto);
    }

    public void delete(Long memberId){
        Member member = memberRepository.find(memberId);
        member.delete();
    }
}
