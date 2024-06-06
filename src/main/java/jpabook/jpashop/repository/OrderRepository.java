package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    /**
     * 주문 저장
     * @param order
     */
    public void save(Order order) {
        em.persist(order);
    }

    /**
     * 주문 1개 id로 찾기
     * @param id
     * @return
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll() {}   // 나중에 추가할 내용 - 필터에 따른 조회하기
}
