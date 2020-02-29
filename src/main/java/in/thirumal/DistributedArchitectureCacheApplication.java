package in.thirumal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DistributedArchitectureCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedArchitectureCacheApplication.class, args);
	}

}
