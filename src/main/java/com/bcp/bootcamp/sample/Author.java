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

    public void addBook(Integer id, String title, Integer year, String editorial) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }

        var listaEditorial = List.of( new Editorial(editorial));
        this.books.add(new Book(id, title, year, listaEditorial));
    }

}
