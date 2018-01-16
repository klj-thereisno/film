package fun.thereisno.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter{
	
	/**
	 * default username is Administrator
	 */
	@Value("${username2}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(username)
			.password(password)
			.roles("AD");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers()
				.frameOptions().sameOrigin()
				.and()
			.authorizeRequests()
				/*.antMatchers("/", "/static/**").permitAll()
				// .regexMatchers("/(film|site)/[a-z0-9=\\?&]+(/.+)?").permitAll()
				.regexMatchers("/(film|site)/.+").permitAll()*/
				.regexMatchers("/(?:(((film|site)/[^/]+)|static/.+)?)").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/admin").permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login").permitAll();
	}

}
