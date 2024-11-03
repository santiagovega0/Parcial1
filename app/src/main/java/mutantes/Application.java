package mutantes;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
@EntityScan(basePackages = "mutantes.model") 
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
