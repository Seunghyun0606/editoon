package com.nokk.editoon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nokk.editoon.interceptor.VerifyAccountInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private VerifyAccountInterceptor verifyAccountInterceptor;
	
//	@Autowired
//	private VerifyTokenInterceptor verifyTokenInterceptor;
	
//	@Autowired
//	private VerifyBandMemberInterceptor verifyBandMemberInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(verifyAccountInterceptor)
//			.addPathPatterns("/account/v1/*")
//			.addPathPatterns("/editoon/v1/*")
//			.order(0);
		
//		registry.addInterceptor(verifyBandMemberInterceptor)
//			.addPathPatterns("/note/v2/*")
//			.order(1);
	}
}