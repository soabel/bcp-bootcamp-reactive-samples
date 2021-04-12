package com.bcp.bootcamp.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);

//        imperativa();
//
//        funcional();

        funcionalObjetos();;

    }

    private static void imperativa() {
        System.out.println("IMPERATIVA");

        //1.
        List<Integer> listaEnteros = List.of(1, 2, 3, 5, 8, 13, 21, 34, 55);
        System.out.println("listaEnteros = " + listaEnteros);

        //2.
        List<Integer> listaPares = new ArrayList<>();
        for (Integer i = 0; i < listaEnteros.size(); i++) {
            if (listaEnteros.get(i) % 2 == 0) {
                listaPares.add(listaEnteros.get(i));
            }
        }
        System.out.println("listaPares = " + listaPares);

        //3.
        List<Integer> listaCuadradosPares = new ArrayList<>();
        for (Integer i = 0; i < listaPares.size(); i++) {
            listaCuadradosPares.add(listaPares.get(i) * listaPares.get(i));
        }
        System.out.println("listaCuadradosPares = " + listaCuadradosPares);

        //4.
        Integer sumaCuadradosPares = 0;
        for (Integer i = 0; i < listaCuadradosPares.size(); i++) {
            sumaCuadradosPares += listaCuadradosPares.get(i);
        }
        System.out.println("sumaCuadradosPares = " + sumaCuadradosPares);

        //5.
        Integer maximoCuadrado = 0;
//        Integer
        for (Integer i = 0; i < listaCuadradosPares.size(); i++) {
            if (maximoCuadrado < listaCuadradosPares.get(i)){
                maximoCuadrado=listaCuadradosPares.get(i);
            }
        }
        System.out.println("maximoCuadrado = " + maximoCuadrado);
        
        //6.
        var primervalor = listaCuadradosPares.get(0);
        System.out.println("primervalor = " + primervalor);


    }

    private static void funcional() {
        System.out.println("FUNCIONAL");

        List<Integer> listaEnteros = List.of(1, 22,22, 2, 3, 5, 8, 13, 21, 34, 55);
        System.out.println("listaEnteros = " + listaEnteros);

        List<Integer> listaPares = listaEnteros.stream()
//                .filter(p -> p % 2 == 0)  // Lambda expressions
//                .filter(p -> {              // Funciones anónimas
//                    return p % 2 == 0;
//                })
//                .filter(p-> esPar(p)) // utilizando una funcion
                .filter(SampleApplication::esPar)
                .collect(Collectors.toList());
        System.out.println("listaPares = " + listaPares);


//        List<Integer> listaCuadradosPares = listaPares.stream()
//                .map(p-> p * p)
//                .collect(Collectors.toList());

        List<Integer> listaCuadradosPares = listaEnteros.stream()
                .filter(SampleApplication::esPar)
                .map(p -> {
                    return p * p;
                })
                .collect(Collectors.toList());
        System.out.println("listaCuadradosPares = " + listaCuadradosPares);

//        var sumaCuadradosPares = listaCuadradosPares.stream()
//                .reduce((sum, p) -> {
//                    return sum + p;
//                }).get();
//        System.out.println("sumaCuadradosPares = " + sumaCuadradosPares);

        var sumaCuadradosPares = listaEnteros.stream()
                .filter(SampleApplication::esPar)
                .map(p -> {
                    return p * p;
                })
                .reduce((sum, p)-> sum + p).get();

        System.out.println("sumaCuadradosPares = " + sumaCuadradosPares);
        
        var maximoCuadrado = listaCuadradosPares.stream()
                .max(Comparator.naturalOrder())
                .get();

        System.out.println("maximoCuadrado = " + maximoCuadrado);

        var minimoCuadrado = listaCuadradosPares.stream()
                .min(Comparator.naturalOrder())
                .get();

        System.out.println("minimoCuadrado = " + minimoCuadrado);
        
        var primerElemento = listaCuadradosPares.stream().findFirst().get();
        System.out.println("primerElemento = " + primerElemento);

        var eliminarDuplicados = listaCuadradosPares.stream().distinct()
                .collect(Collectors.toList());
        System.out.println("eliminarDuplicados = " + eliminarDuplicados);

        var sortedList =listaCuadradosPares.stream()
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println("sortedList = " + sortedList);

    }

    private static boolean esPar(Integer num) {
        return num % 2 == 0;
    }

    private static void funcionalObjetos(){

        System.out.println("FUNCIONAL CON OBJETOS");

        List<Author> authors = new ArrayList<>();

        Author author1 = new Author(1, "Shakespeare", 1700, null);
        author1.addBook(1, "Romeo y Julieta", 2019);
        author1.addBook(2, "Hamlet", 2020);

        Author author2 = new Author(2, "Mario Vargas llosa", 1960, null);
        author2.addBook(3, "La ciudad y los perros", 2018);
        author2.addBook(4, "Lituma en los andes", 2017);

        Author author3 = new Author(3, "Ciro Alegria", 1910, null);
        author3.addBook(5, "Los perros hambrientos", 2020);
        author3.addBook(6, "El mundo es ancho y ajeno", 2020);

        Author author4 = new Author(4, "Jane Austen", 1800, null);
        author4.addBook(7, "Sentido y Sensibilidad", 2019);

        authors = List.of(author1, author2, author3, author4);

        //3.

        var autores2Libros = authors.stream()
                .filter(a-> a.getBooks().size() ==2)
                .collect(Collectors.toList());

        System.out.println("autores2Libros = " + autores2Libros);

        //3.1

        var nombresAutores2Libros = authors.stream()
                .filter(a-> a.getBooks().size() ==2)
                .map(a-> a.getName())
                .collect(Collectors.toList());

        System.out.println("nombresAutores2Libros = " + nombresAutores2Libros);


        //4.
        var nombreAutoresLibros2020 = authors.stream()
                .filter(a-> a.getBooks().stream()
                        .anyMatch(b-> b.getYear()==2020) )
                .map(a-> a.getName())
                .collect(Collectors.toList());
        System.out.println("nombreAutoresLibros2020 = " + nombreAutoresLibros2020);

        //5.






    }

}
