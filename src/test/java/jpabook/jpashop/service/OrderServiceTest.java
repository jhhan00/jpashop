package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderRepository;
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
class OrderServiceTest {
    /**
     * 실제 현업에서는 이보다 훨씬 자세하게 테스트를 진행한다.
     */

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member m1 = createMember();
        Book book1 = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(m1.getId(), book1.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문 시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book1.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember();
        Book book = createBook("시골 JPA1", 20000, 15);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);    // 8개로 감소
        orderService.cancelOrder(orderId);                                              // 주문 취소했으니 10개로 복구

        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL이다.");
        assertEquals(15, book.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member m1 = createMember();
        Book b1 = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        Long orderId = orderService.order(m1.getId(), b1.getId(), orderCount);

        fail("재고 수량 부족 예외가 발생해야 함");
    }

    private Member createMember() {
        Member m1 = new Member();
        m1.setName("회원1");
        m1.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(m1);
        return m1;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book b1 = new Book();
        b1.setName(name);
        b1.setPrice(price);
        b1.setStockQuantity(stockQuantity);
        em.persist(b1);
        return b1;
    }
}