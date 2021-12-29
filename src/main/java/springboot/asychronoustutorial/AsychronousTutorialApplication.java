package springboot.asychronoustutorial;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class AsychronousTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsychronousTutorialApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		// Is the minimum number of threads that remain active at any given point of
		// time. If you don’t provide a value explicitly then it will have default value
		// as 1
		executor.setCorePoolSize(2);

		// Is the maximum number of threads that can be created. The maxPoolSize relies
		// on queueCapacity because ThreadPoolTaskExecutor creates a new thread only if
		// the number of items in the queue exceeds queue capacity.
		executor.setMaxPoolSize(2);

		executor.setQueueCapacity(500);

		// Setting the prefix name of the threads. Eg 'Async - 1', 'Async - 2', 'Async -
		// 3' and so on will be the name of the thread
		executor.setThreadNamePrefix("Async - ");

		// Initializing the executor
		executor.initialize();

		return executor;
	}

}
