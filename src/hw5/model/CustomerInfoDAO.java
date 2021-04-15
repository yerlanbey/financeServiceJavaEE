
package hw5.model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import hw5.databean.CustomerInfo;

public class CustomerInfoDAO extends GenericDAO<CustomerInfo> {
	public CustomerInfoDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerInfo.class, tableName, cp);
	}
}
