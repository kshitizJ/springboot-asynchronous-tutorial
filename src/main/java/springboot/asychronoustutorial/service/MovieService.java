package springboot.asychronoustutorial.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import springboot.asychronoustutorial.model.MovieModel;

@Service
@Slf4j
public class MovieService {

    private final RestTemplate template;

    public MovieService(RestTemplateBuilder templateBuilder) {
        this.template = templateBuilder.build();
    }

    /**
     * 
     * @param movieId
     * @return lookForMovie method’s return type is CompletableFuture which is a
     *         requirement for any asynchronous service
     * @throws InterruptedException
     */
    @Async
    public CompletableFuture<MovieModel> lookforMovie(String movieId) throws InterruptedException {

        // logging the movie Id
        log.info("Looking up movie ID: {}", movieId);

        // setting the url to get the movie
        String url = String.format("https://ghibliapi.herokuapp.com/films/%s", movieId);

        // getting the movie and saving it as a MovieModal object
        MovieModel results = template.getForObject(url, MovieModel.class);

        // Artificial delay for 1s
        Thread.sleep(1000L);

        // returning the
        return CompletableFuture.completedFuture(results);
    }

}
