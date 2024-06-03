package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        System.out.println("회원가입 테스트 시작!");
        Member m1 = new Member();
        m1.setName("Kim");

        Long saveId = memberService.join(m1);
        System.out.println("saveId = " + saveId);

        assertEquals(m1, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_에러() throws Exception {
        System.out.println("중복_회원_에러 테스트 시작!");
        Member m1 = new Member();
        m1.setName("Kim");
        Member m2 = new Member();
        m2.setName("Kim");

        memberService.join(m1);
        try {
            memberService.join(m2);     // 동일한 이름을 넣어서 여기서 예외발생!
        } catch(IllegalStateException ie) {
            return;
        }

        fail("예외가 발생해야 한다.");   // 이 코드에 도달하면 안된다.
    }
}