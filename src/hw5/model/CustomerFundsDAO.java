package hw5.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import hw5.databean.CustomerFunds;

public class CustomerFundsDAO extends GenericDAO<CustomerFunds> {
	public CustomerFundsDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerFunds.class, tableName, cp);
	}
}
