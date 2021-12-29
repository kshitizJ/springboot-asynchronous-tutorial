package springboot.asychronoustutorial;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import springboot.asychronoustutorial.model.MovieModel;
import springboot.asychronoustutorial.service.MovieService;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    private final MovieService movieService;

    public ApplicationRunner(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {

        // start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<MovieModel> page1 = movieService.lookforMovie("2baf70d1-42bb-4437-b551-e5fed5a87abe");
        CompletableFuture<MovieModel> page2 = movieService.lookforMovie("12cfb892-aac0-4c5b-94af-521852e46d6a");
        CompletableFuture<MovieModel> page3 = movieService.lookforMovie("58611129-2dbc-4a81-a72f-77ddfc1b1b49");

        // Join all the threads
        CompletableFuture.allOf(page1, page2, page3).join();

        // Printing result including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());

    }

}
