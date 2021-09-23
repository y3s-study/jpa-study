package com.y3sstudy.skhelloshop.service;

import com.y3sstudy.skhelloshop.domain.Member;
import com.y3sstudy.skhelloshop.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Kim");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findById(saveId).get());
    }

    @Test
    @Order(2)
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("Kim");
        Member member2 = new Member();
        member2.setName("Kim");
        //when
        memberService.join(member1);
        IllegalStateException actualException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
        assertEquals("이미 존재하는 회원입니다.", actualException.getMessage());
    }
}