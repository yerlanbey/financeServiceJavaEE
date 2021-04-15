package hw5.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import hw5.databean.EmployeeCredential;

public class EmployeeCredentialDAO extends GenericDAO<EmployeeCredential> {
	public EmployeeCredentialDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeCredential.class, tableName, cp);
	}
}
