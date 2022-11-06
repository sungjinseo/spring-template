package dev.greatseo.portfolio.config;

import dev.greatseo.portfolio.util.auth.point.CustomAccessDeniedPoint;
import dev.greatseo.portfolio.util.auth.point.CustomAuthenticationEntryPoint;
import dev.greatseo.portfolio.util.auth.AuthProvider;
import dev.greatseo.portfolio.util.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	private final AuthProvider authProvider;

	private final String PERMIT_SIGN_IN = "/api/v1/signup";
	private final String PERMIT_SIGN_UP = "/api/v1/signin/**";
	private final String PERMIT_EXCEPTION = "/exception/**";
	private final String PERMIT_BOARD = "/api/v1/board/**";
	private final String[] API_DOCS = {"/swagger-ui/**", "/v3/api-docs/**"};


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	} 

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	} 
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setMaxAge((long) 3600);
		configuration.setAllowCredentials(false);
		configuration.addExposedHeader("accessToken");
		configuration.addExposedHeader("content-disposition");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		//	You are asking Spring Security to ignore Ant [pattern='/static/**'].
		// This is not recommended -- please use permitAll via HttpSecurity#authorizeHttpRequests instead.
		web.ignoring().antMatchers(
				"/static/css/**",
				"/static/js/**",
				"/static/img/**",
				"/static/**"
				);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.httpBasic().disable()
				.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.antMatchers(PERMIT_SIGN_IN).permitAll()			// 회원가입
					.antMatchers(PERMIT_SIGN_UP).permitAll() 			// 로그인
					.antMatchers(PERMIT_EXCEPTION).permitAll() 			// 예외처리 포인트
					.antMatchers(API_DOCS).permitAll() 			// 예외처리 포인트

					// 게시글은 사람들이 볼 수 있게 처리해보자...
					.anyRequest().hasRole(Auth.ROLE_01.label())				// 이외 나머지는 USER 권한필요
				.and()
			.cors()
				.and()
			.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedPoint())
				.and()
			.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.and()
			.addFilterBefore(new CustomFilter(authProvider), UsernamePasswordAuthenticationFilter.class);
	}

	public enum Auth {
		ROLE_01("USER"),
		ROLE_02("ADMIN")
		;

		private final String label;

		Auth(String label) {
			this.label = label;
		}

		public String label() {
			return label;
		}
	}

}
