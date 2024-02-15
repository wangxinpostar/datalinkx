package com.datalinkx.dataserver.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.datalinkx.dataclient.client.DatalinkXClientUtils;
import com.datalinkx.dataserver.client.xxljob.XxlJobClient;
import com.datalinkx.dataserver.client.xxljob.XxlLoginClient;
import com.datalinkx.dataserver.client.xxljob.interceptor.LoginInterceptor;
import com.datalinkx.dataserver.controller.formatter.UserGenericConverter;
import com.google.common.base.CaseFormat;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {


	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new UserGenericConverter());
		WebMvcConfigurer.super.addFormatters(registry);
	}

	/**
	 * 将snake case（lower undersocre）风格的参数转换为camel风格
	 */
	@Bean
	public Filter snakeConverter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
				final Map<String, String[]> formattedParams = new HashMap<>();

				for (String param : request.getParameterMap().keySet()) {
					String[] paramValues = request.getParameterValues(param);
					formattedParams.put(param, paramValues);

					String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
					formattedParams.put(formattedParam, paramValues);
				}

				filterChain.doFilter(new HttpServletRequestWrapper(request) {
					@Override
					public String getParameter(String name) {
						return formattedParams.containsKey(name) ? formattedParams.get(name)[0] : null;
					}

					@Override
					public Enumeration<String> getParameterNames() {
						return Collections.enumeration(formattedParams.keySet());
					}

					@Override
					public String[] getParameterValues(String name) {
						return formattedParams.get(name);
					}

					@Override
					public Map<String, String[]> getParameterMap() {
						return formattedParams;
					}
				}, response);
			}
		};
	}


	@Bean
	public XxlLoginClient xxlLoginClient(XxlClientProperties xxlClientProperties) {
		return DatalinkXClientUtils.createClient("xxljoblogin", xxlClientProperties.getClient(), XxlLoginClient.class);
	}
	@Bean
	public XxlJobClient xxlJobClient(XxlClientProperties xxlClientProperties, LoginInterceptor loginInterceptor) {
		return DatalinkXClientUtils.createClient("xxljob", xxlClientProperties.getClient(),
				XxlJobClient.class, loginInterceptor);
	}
}
