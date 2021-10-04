package org.y3s.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.y3s.Member;
import org.y3s.MemberRepository;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> list(Pageable pageable) {
        log.info("{}", pageable);
        Page<Member> memberPage = memberRepository.findAll(pageable);
        return memberPage.getContent();
    }
}
