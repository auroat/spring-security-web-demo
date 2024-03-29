package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and()
	    .formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
	@SuppressWarnings("deprecation")
	UserDetails userDetails = User.withDefaultPasswordEncoder().username("aurel").password("1").roles("USER")
	    .build();

	return new InMemoryUserDetailsManager(userDetails);
    }
}
// Done with the theoretical investigation here.