package hw5.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import hw5.databean.Funds;

public class FundsDAO extends GenericDAO<Funds> {
	public FundsDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Funds.class, tableName, cp);
	}
}
