package demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<String> stringMono = Mono.just("abcd").log();
        stringMono.subscribe(System.out::println);

        Mono<?> exceptionMono = Mono.just("abcd")
                .then(Mono.error(new RuntimeException("Error")))
                .log();
        exceptionMono.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> just = Flux.just("one", "two", "three", "four", "five").log();
        just.subscribe(System.out::println);

        Flux<String> justTwo = Flux.just("one", "two", "three", "four", "five")
                .concatWithValues("ten")
                .log();
        justTwo.subscribe(System.out::println);

        Flux<String> justError = Flux.just("one", "two", "three", "four", "five")
                .concatWithValues("ten")
                .concatWith(Flux.error(new RuntimeException("Error")))
                .concatWithValues("won't be added")
                .log();
        justError.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
}

