package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;        // 상품 수정이 필요하기 때문에 id 컬럼 필요

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
