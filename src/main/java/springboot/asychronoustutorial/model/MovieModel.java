package springboot.asychronoustutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @JsonIgnoreProperties: We have used this annotation so that if there are more
 *                        attributes in the response, they can be safely ignored
 *                        by Spring.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MovieModel {

    private String title;
    private String producer;

    // standard getters and setters

    @Override
    public String toString() {
        return String.format("MovieModel{title='%s', producer='%s'}", title, producer);
    }
}