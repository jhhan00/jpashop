package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 멤버 1명 찾기
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * 멤버 모두 찾기
     * JPQL 사용
     * @return
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 멤버 이름으로 찾기
     * JPQL 사용
     * @return
     */
    public List<Member> findName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
