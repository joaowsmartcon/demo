package com.exemplo.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.exemplo.config.Tenant;


public class SchemaCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver{

	@Override
	public String resolveCurrentTenantIdentifier() {
		if(Tenant.getIdentificador() == null) {
			return "";			
		}
		return Tenant.getIdentificador();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

}
