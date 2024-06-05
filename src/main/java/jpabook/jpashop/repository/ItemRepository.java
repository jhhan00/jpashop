package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * 아이템 저장 (등록 & 갱신)
     * @param item
     */
    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);   // insert
        } else {
            em.merge(item);     // update..?
        }
    }

    /**
     * 한 개의 상품 찾기
     * @param id
     * @return
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * 모든 상품 찾기
     * @return
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
