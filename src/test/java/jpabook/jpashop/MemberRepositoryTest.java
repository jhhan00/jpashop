package jpabook.jpashop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void testMember() throws Exception {
        Member m1 = new Member();
        m1.setUsername("m1");

        Long savedId = memberRepository.save(m1);
        Member m2 = memberRepository.find(savedId);

        org.assertj.core.api.Assertions.assertThat(m2.getId()).isEqualTo(m1.getId());
        org.assertj.core.api.Assertions.assertThat(m2.getUsername()).isEqualTo(m1.getUsername());
        org.assertj.core.api.Assertions.assertThat(m2).isEqualTo(m1);
    }
}