package jjfactory.selecttuning.service.post;


import jjfactory.selecttuning.dto.req.MemberCreate;
import jjfactory.selecttuning.dto.res.MemberRes;
import jjfactory.selecttuning.repository.post.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(MemberCreate dto){
        memberRepository.save(dto);
        return dto.getMemberId();
    }

    @Transactional(readOnly = true)
    public List<MemberRes> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MemberRes findOne(Long id){
        return new MemberRes(memberRepository.find(id));
    }

}
