package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    /**
     * 엔티티에 핵심 비즈니스 로직들이 들어가 있음...
     * 이런 것을 '도메인 모델 패턴'이라고 함
     */

    // 의존관계 설정(DI)
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문 1개 생성
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회 - 주문과 상품 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 설정
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성 - 기본 create
        // 단순화를 위해 주문상품의 종류는 1개만 주문할 수 있도록 함
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 참고
        // 생성자를 protected로 설정했기 때문에 에러가 남, 사용 불가능
        // createOrderItem만 강제로 사용하게 할 수 있음
        // OrderItem orderItem1 = new OrderItem();

        // 주문 생성 - 기본 create
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 검색 (지금은 껍데기만 존재)
     */
//    public List<Order> findOrders() {
//
//    }
}
