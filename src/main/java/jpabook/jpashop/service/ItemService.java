package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItemBook(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId);
        if(bookParam.getName() != null) {
            findItem.setName(bookParam.getName());
        }
        if(bookParam.getPrice() != 0) {
            findItem.setPrice(bookParam.getPrice());
        }
        if(bookParam.getStockQuantity() >= 0) {
            findItem.setStockQuantity(bookParam.getStockQuantity());
        }
//        itemRepository.save(findItem);    // 영속 상태이기 때문에 수정만 하면 save 를 부르지 않아도 자동 커밋 됨
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
