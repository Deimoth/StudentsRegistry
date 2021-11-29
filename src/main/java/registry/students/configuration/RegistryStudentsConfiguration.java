package registry.students.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "registry.students.data.repository")
public class RegistryStudentsConfiguration {

}
