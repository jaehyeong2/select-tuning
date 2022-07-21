package jjfactory.selecttuning.service;


import jjfactory.selecttuning.dtio.MemberDto;
import jjfactory.selecttuning.dtio.MemberRes;
import jjfactory.selecttuning.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(MemberDto dto){
        memberRepository.save(dto);
        return dto.getMemberId();
    }

    @Transactional(readOnly = true)
    public List<MemberRes> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MemberRes findOne(Long id){
        return memberRepository.find(id);
    }

}
