package com.service;

import com.domain.Member;
import com.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setUsername(name);
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id).get();
    }

    public List<Member> findMembers() {
        List<Member> all = memberRepository.findAll();

        return all;
    }
}
