package cz.itnetwork.insurance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringInsuranceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringInsuranceApplication.class,args);
    }
}
