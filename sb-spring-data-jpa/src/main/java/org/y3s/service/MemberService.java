package org.y3s.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.y3s.domain.Member;
import org.y3s.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
}
