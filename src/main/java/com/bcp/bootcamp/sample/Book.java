package com.bcp.bootcamp.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id ;
    private String title;
    private Integer year;

    private List<Editorial> editorials;
}
