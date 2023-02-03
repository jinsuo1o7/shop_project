package com.jinsuo_develop.shop.service;

import com.jinsuo_develop.shop.domain.Member;
import com.jinsuo_develop.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        Member member = new Member();
        member.setName("hi");
        Long savedId = memberService.join(member);
        assertThat(member.getId()).isEqualTo(savedId);
    }

    @Test
    void joinDuplicate() {
        Member member1 = new Member();
        member1.setName("hi");
        Member member2 = new Member();
        member2.setName("hi");

        memberService.join(member1);

        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}