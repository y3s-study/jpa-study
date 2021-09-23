package com.y3sstudy.skhelloshop.repository;

import com.y3sstudy.skhelloshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findByName(String name);
}
