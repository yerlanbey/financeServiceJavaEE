package hw5.model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import hw5.databean.CustomerCredential;

public class CustomerCredentialDAO extends GenericDAO<CustomerCredential> {
	public CustomerCredentialDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerCredential.class, tableName, cp);
	}
}
