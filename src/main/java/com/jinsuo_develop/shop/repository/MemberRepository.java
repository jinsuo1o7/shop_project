package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
