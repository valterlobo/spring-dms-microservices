package process.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ProcessResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/swagge‌​r-ui.html", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                         "/configuration/security", "/webjars/**", "/swagger-resources/configuration/ui",
                         "/swagger-resources/configuration/security").permitAll();
//            .and().authorizeRequests().
//                    antMatchers("/**").access("hasRole('ROLE_USER')");
    }

}
