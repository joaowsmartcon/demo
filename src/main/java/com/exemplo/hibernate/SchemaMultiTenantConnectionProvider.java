package com.exemplo.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.exemplo.config.Tenant;

@Component
public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectionProvider connectionProvider = null;

	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {

		Map<?, ?> settings = serviceRegistry.getService(ConfigurationService.class).getSettings();

		connectionProvider = new DriverManagerConnectionProviderImpl();
		((DriverManagerConnectionProviderImpl) connectionProvider).injectServices(serviceRegistry);

		((DriverManagerConnectionProviderImpl) connectionProvider).configure(settings);

	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		Connection con = connectionProvider.getConnection();
		String identifier = Tenant.getIdentificador();
		String searchPath = " SET search_path TO global ";
		if (StringUtils.hasText(identifier)) {
			searchPath = " SET search_path TO global ".concat(",").concat(identifier);
		}
		con.createStatement().execute(searchPath);
		return con;
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		tenantIdentifier = Tenant.getIdentificador();
		Connection connection = getAnyConnection();
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connectionProvider.closeConnection(connection);

	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);

	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}
