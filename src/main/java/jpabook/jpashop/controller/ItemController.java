package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createFormBook(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createBook(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String listItem(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemInfo(@PathVariable("itemId") Long itemId, Model model) {
        Book book1 = (Book) itemService.findOne(itemId);

        BookForm bf = new BookForm();
        bf.setId(book1.getId());
        bf.setName(book1.getName());
        bf.setPrice(book1.getPrice());
        bf.setStockQuantity(book1.getStockQuantity());
        bf.setAuthor(book1.getAuthor());
        bf.setIsbn(book1.getIsbn());

        model.addAttribute("form", bf);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {
        Book book1 = new Book();
        book1.setId(form.getId());
        book1.setName(form.getName());
        book1.setPrice(form.getPrice());
        book1.setStockQuantity(form.getStockQuantity());
        book1.setAuthor(form.getAuthor());
        book1.setIsbn(form.getIsbn());

        itemService.saveItem(book1);
        return "redirect:/items";
    }
}
