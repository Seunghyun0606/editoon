package com.nokk.editoon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.nokk.editoon.model.account.service.CustomAccountDetailService;
import com.nokk.editoon.security.CustomAccessDeniedHandler;
import com.nokk.editoon.security.CustomAuthenticationEntryPoint;
import com.nokk.editoon.security.CustomJwtRequestFilter;
import com.nokk.editoon.security.CustomLogoutHandler;
import com.nokk.editoon.security.CustomLogoutSuccessHandler;
import com.nokk.editoon.security.CustomUsernamePasswordAuthenticationFilter;
import com.nokk.editoon.util.JwtTokenUtil;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private CustomOAuth2UserService OAuth2UserService;

	@Autowired
	private CustomJwtRequestFilter jwtRequestFilter;

	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CustomAccountDetailService detailService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private CustomLogoutHandler customLogoutHandler;

	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
				.cors();

		http
			.csrf().disable();
//			.ignoringAntMatchers("/nonmember/**")
//			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/nonmember/**").permitAll()
				.antMatchers("/token/**").permitAll()
				.antMatchers("/test/**").permitAll()
				.antMatchers("/account/**").hasAnyRole("USER")
				.antMatchers("/editoon/**").hasAnyRole("USER")
//            	.antMatchers("/ws/**").permitAll() // ws로 들어오는 권한 모두 풀어줘야, 프론트에서 요청해도 401에러가 안뜸.
				.antMatchers("/nonmember/**").permitAll() 
				.anyRequest().authenticated();

		http.logout()
//	        	.addLogoutHandler(customLogoutHandler)
				.logoutUrl("/account/logout").logoutSuccessHandler(customLogoutSuccessHandler);
//        	
		http
			.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//		http
//        	.oauth2Login()
//            	.userInfoEndpoint()
//                	.userService(OAuth2UserService);

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/nonmember/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	protected CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(jwtTokenUtil,
				redisTemplate);

		try {
			filter.setAuthenticationManager(this.authenticationManagerBean());
			filter.setFilterProcessesUrl("/login");
			filter.setPostOnly(true);
			filter.setUsernameParameter("username");
			filter.setPasswordParameter("password");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filter;
	}

	@Bean
	public CookieCsrfTokenRepository getCookieCsrfTokenRepository() {
		CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
		cookieCsrfTokenRepository.setCookieDomain("localhost");
		cookieCsrfTokenRepository.setCookieHttpOnly(true);
		return cookieCsrfTokenRepository;
	}

}
