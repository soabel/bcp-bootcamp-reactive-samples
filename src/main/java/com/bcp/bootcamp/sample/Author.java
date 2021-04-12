package com.bcp.bootcamp.sample;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {
    private Integer id;
    private String name;
    private Integer birthYear;
    private List<Book> books;

    public void addBook(Integer id, String title, Integer year) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(new Book(id, title, year, null));
    }

}
