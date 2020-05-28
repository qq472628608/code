/*package com.fjw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import lombok.Setter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Setter
	@Autowired
	private HrService hrService;
	@Setter
	@Autowired
	private MyFilterInvocationSecurityMetadataSource filterMetadataSource;
	@Setter
	@Autowired
	private MyAccessDecisionManager accessDecisionManager;
	@Setter
	@Autowired
	private MyAccessDeniedHandler deniedHandler;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(hrService);
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <T extends FilterSecurityInterceptor> T postProcess(T t) {
				t.setSecurityMetadataSource(filterMetadataSource);
				t.setAccessDecisionManager(accessDecisionManager);
				return t;
			}
		}).and().csrf().disable().exceptionHandling().accessDeniedHandler(deniedHandler);
	}

}
*/