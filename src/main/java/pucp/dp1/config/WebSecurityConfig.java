package pucp.dp1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import pucp.dp1.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		/*
//		 * SIN SESSION EN EL BACK
//		 * */
//		http.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
	
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//		//usuarios hardcodeados
//        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//        UserDetails user = userBuilder
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//
//        UserDetails admin = userBuilder
//            .username("admin")
//            .password("password")
//            .roles("USER","ADMIN")
//            .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//	}
	
	
}