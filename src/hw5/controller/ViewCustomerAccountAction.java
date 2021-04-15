package hw5.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import hw5.model.CustomerInfoDAO;
import hw5.model.FundsDAO;
import hw5.model.CustomerFundsDAO;
import hw5.model.Model;
import hw5.databean.CustomerCredential;
import hw5.databean.CustomerFunds;
import hw5.databean.CustomerInfo;
import hw5.databean.Funds;
public class ViewCustomerAccountAction extends Action {
    private CustomerInfoDAO customerDAO;
    private FundsDAO fundsDAO;
    private CustomerFundsDAO customerFundsDAO;

    public ViewCustomerAccountAction(Model model) 
    {
    	customerDAO = model.getCustomerInfoDAO();
    	fundsDAO = model.getFundsDAO();
    	customerFundsDAO = model.getCustomerFundsDAO();
    }

    public String getName() {
        return "welcome.do";
    }
    
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        try {
        	HttpSession session = request.getSession();
            CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
            CustomerInfo[] customer_info = customerDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
            CustomerFunds[] customer_funds = customerFundsDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
            Funds[] funds = fundsDAO.match();

            request.setAttribute("customer_info", customer_info[0]);
            request.setAttribute("customer_funds", customer_funds);
            return ("welcome.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
