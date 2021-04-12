package com.bcp.bootcamp.sample;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private Integer id ;
    private String title;
    private Integer year;

    private List<Editorial> editorials;
}
