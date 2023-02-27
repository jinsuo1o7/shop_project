package com.jinsuo_develop.shop.service;

import com.jinsuo_develop.shop.domain.Member;
import com.jinsuo_develop.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    @Transactional
    public Long join(Member member) {
        validDuplicatedMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validDuplicatedMember(Member member) {
        List<Member> findMembers = repository.findByName(member.getName());
        if(findMembers.size() != 0){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public List<Member> findAllMembers() {
        return repository.findAll();
    }

    public Member findOne(Long id) {
        return repository.findById(id).get();
    }
}
