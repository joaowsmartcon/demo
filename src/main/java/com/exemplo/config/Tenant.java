package com.exemplo.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Tenant implements CurrentTenantIdentifierResolver {

	private static ThreadLocal<String> tl = new ThreadLocal<>();

	public static void setIdentificador(String tenandId) {
		tl.set(tenandId);
	}

	public static String getIdentificador() {
		return tl.get();
	}

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (!StringUtils.hasText(tl.get())) {
			return "GLOBAL";
		}
		return tl.get();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}