package org.y3s;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.y3s.domain.Member;
import org.y3s.repository.MemberRepository;
import org.y3s.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() {
		//given
		Member member = new Member("kim");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberRepository.findOne(saveId);
		assertTrue(member == findMember);
	}
}
