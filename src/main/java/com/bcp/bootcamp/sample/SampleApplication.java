package com.bcp.bootcamp.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);

//        imperativa();
//        funcional();
//        funcionalObjetos();;

//        reactiva();

        reactivaObjetos();
    }

    private static void imperativa() {
        System.out.println("IMPERATIVA");

        //1.
        List<Integer> listaEnteros = List.of(1, 22, 22, 2, 3, 5, 8, 13, 21, 34, 55);
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
//
//        //6.
//        var primervalor = listaCuadradosPares.get(0);
//        System.out.println("primervalor = " + primervalor);


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

//
////        List<Integer> listaCuadradosPares = listaPares.stream()
////                .map(p-> p * p)
////                .collect(Collectors.toList());
//
        List<Integer> listaCuadradosPares = listaEnteros.stream()
                .filter(SampleApplication::esPar)
                .map(p -> p * p)
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
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        System.out.println("sortedList = " + sortedList);

    }

    private static boolean esPar(Integer num) {
        return num % 2 == 0;
    }

//    private static void funcionalObjetos(){
//
//        System.out.println("FUNCIONAL CON OBJETOS");
//
//        List<Author> authors = new ArrayList<>();
//
//        Author author1 = new Author(1, "Shakespeare", 1700, null);
//        author1.addBook(1, "Romeo y Julieta", 2019);
//        author1.addBook(2, "Hamlet", 2020);
//
//        Author author2 = new Author(2, "Mario Vargas llosa", 1960, null);
//        author2.addBook(3, "La ciudad y los perros", 2018);
//        author2.addBook(4, "Lituma en los andes", 2017);
//
//        Author author3 = new Author(3, "Ciro Alegria", 1910, null);
//        author3.addBook(5, "Los perros hambrientos", 2020);
//        author3.addBook(6, "El mundo es ancho y ajeno", 2020);
//
//        Author author4 = new Author(4, "Jane Austen", 1800, null);
//        author4.addBook(7, "Sentido y Sensibilidad", 2019);
//
//        authors = List.of(author1, author2, author3, author4);
//
//        //3.
//
////        var autores2Libros = authors.stream()
////                .filter(a-> a.getBooks().size() ==1)
////                .collect(Collectors.toList());
////
////        System.out.println("autores2Libros = " + autores2Libros);
//
//        //3.1
//
////        var nombresAutores2Libros = authors.stream()
////                .filter(a-> a.getBooks().size() ==1)
////                .map(a-> a.getName())
////                .collect(Collectors.toList());
////
////        System.out.println("nombresAutores2Libros = " + nombresAutores2Libros);
//
//
//        //4.
////        var nombreAutoresLibros2020 = authors.stream()
////                .filter(a-> a.getBooks().stream()
////                        .anyMatch(b-> b.getYear()==2020) )
////                .map(a-> a.getName())
////                .collect(Collectors.toList());
////        System.out.println("nombreAutoresLibros2020 = " + nombreAutoresLibros2020);
//
//
//        var nombreAutoresLibros2020 = authors.stream()
//                .filter(a-> a.getBooks().stream()
//                        .filter(b-> b.getYear()==2020).count()>0)
//                .map(a-> a.getName())
//                .collect(Collectors.toList());
//        System.out.println("nombreAutoresLibros2020 = " + nombreAutoresLibros2020);
//
////
////        //5.
//
//
//
//
//
//
//    }

    public static void reactiva() {
        System.out.println("======= REACTIVA ======== ");


        // Inicializar un flujo vacío
        Mono<Integer> monoVacio = Mono.empty();
        Flux<Integer> fluxVacio = Flux.empty();


        Mono<Integer> monoInteger = Mono.just(12);
        Flux<Integer> fluxInteger = Flux.just(12,14);

        fluxInteger.subscribe(x-> System.out.println(x));


        List<Integer> listaEntero = List.of( 12,14);

        System.out.println("listaEntero = " + listaEntero);


//        //CREAR FLUJOS MONO Y FLUX

//        monoInteger.
//                subscribe(x -> System.out.println("x = " + x));
//
//        Flux<Integer> fluxInteger = Flux.just(1, 2, 4);
//        fluxInteger.subscribe(y -> System.out.println("y = " + y));
//
//        fluxInteger
//                .doOnNext(y -> System.out.println("do y = " + y))
//                .subscribe(y -> System.out.println("y = " + y));
//
//
        //INICIALIZAR DESDE ITERABLES
        List<Integer> listaEnteros = List.of(1, 2, 3, 5, 8, 34, 8, 13, 21, 34);

        var flujoIterable  = Flux.fromIterable(listaEnteros);


        flujoIterable
                .subscribe(p -> System.out.println("flujoIterable p = " + p));


        flujoIterable
                .map(t -> t * t)
                .filter(t-> t < 100 )
                .doOnNext(d-> System.out.println("d = " + d))
                .sort(Comparator.naturalOrder())
                .collectList()
                .subscribe(cuadrados-> System.out.println("cuadrados = " + cuadrados));

//        var flujoStream = Flux.fromStream(listaEnteros.stream());
//        flujoStream.subscribe(p -> System.out.println("flujoStream p = " + p));
//
//        Flux<Integer> fluxEnteros = Flux.fromIterable(listaEnteros);
//
////        fluxEnteros
////                .filter(y -> y < 8)
////                .map(y -> y * 2)
////                .sort(Comparator.reverseOrder())
////                .collectList()
////                .subscribe(y -> System.out.println("y = " + y));



    }


    public static void reactivaObjetos() {

        System.out.println("REACTIVA CON OBJETOS");

        List<Author> authors = new ArrayList<>();

        Author author1 = new Author(1, "Shakespeare", 1700,null);
        author1.addBook(1, "Romeo y Julieta", 2019, "Planeta");
        author1.addBook(2, "Hamlet", 2020, "Santillana");

        Author author2 = new Author(2, "Mario Vargas llosa", 1960, null);
        author2.addBook(3, "La ciudad y los perros", 2018, "Planeta");
        author2.addBook(4, "Lituma en los andes", 2017, "Planeta");

        Author author3 = new Author(3, "Ciro Alegria", 1910, null);
        author3.addBook(5, "Los perros hambrientos", 2020, "IBK");
        author3.addBook(6, "El mundo es ancho y ajeno", 2020,"IBK");

        Author author4 = new Author(4, "Jane Austen", 1800, null);
        author4.addBook(7, "Sentido y Sensibilidad", 2019, "Santillana");

        authors = List.of(author1, author2, author3, author4);

        var flujoMono = Mono.just(author1);
        var flujo2 = Mono.just(authors);

        var flujo= Flux.just(author1, author2);
        var flujo3 = Flux.just(authors, authors, authors);

//        var mono1 = Mono.just(author1);
//        var mono2 = Mono.just(author2);
//        Flux.merge(mono1, mono2)
//                .subscribe(a -> System.out.println("merge a = " + a));
//
        Flux<Author> fluxAuthors = Flux.fromIterable(authors);
        fluxAuthors
                .collectList()
                .subscribe(x-> System.out.println("x = " + x));


        fluxAuthors
                .filter(x -> x.getBooks().size() > 1)
                .subscribe(author ->
                        System.out.println("author = " + author.getName()));


        fluxAuthors
                .filter(x -> x.getBooks().size() > 1)
                .map(x -> x.getName())
                .collectList()
                .subscribe(x ->
                        System.out.println("x = " + x));

//
//        // Convertir de flux a colección
//        fluxAuthors
//                .collectList()
//                .doOnNext(d -> System.out.println("d = " + d))
//                .subscribe(lista -> System.out.println("lista = " + lista));
//
        fluxAuthors
                .filter(au -> au.getBooks().stream()
                        .anyMatch(b -> b.getYear() == 2020))
                .map(au -> au.getName())
                .collectList()
                .subscribe(au ->
                        System.out.println("authors2020 = " + au));

//
//        fluxAuthors.map(au -> au.getBooks())
//                .collectList()
//                .subscribe(au -> System.out.println(" map-collectList au = " + au));
//
//
//
//
//
//        Author 1
//            Libro 1
//                Editorial 1
//                Editorial 2
//            Libro 2
//        Author 2
//            Libro 3
//            Libro 4




//            Libro 1
//            Libro 2
//            Libro 3
//            Libro 4




        fluxAuthors
                .map(au -> au.getBooks())
                .collectList()
                .subscribe(x -> System.out.println("map-libros = " + x));


        fluxAuthors
                .flatMap(au -> Flux.fromIterable(au.getBooks()))
                .filter(b-> b.getYear()==2017)
                .collectList()
                .subscribe(x -> System.out.println("libros 2017 = " + x));

        fluxAuthors
                .flatMap(au -> Flux.fromIterable(au.getBooks()))
                .filter(b-> b.getYear()==2020)
                .flatMap(b-> Flux.fromIterable(b.getEditorials()))
                .collectList()
                .subscribe(x -> System.out.println("fmap-editoriales = " + x));


//        fluxAuthors
//                .flatMap(au -> {
//                    return Flux.fromIterable(au.getBooks().stream()
//                            .filter(b -> b.getYear().equals(2020))
//                            .collect(Collectors.toList()));
//                })
////                .flatMap(ed -> Flux.fromIterable(ed.getEditorials()))
//                .collectList()
//                .subscribe(ed -> System.out.println("flatMap - editorials ed = " + ed));

    }


}
