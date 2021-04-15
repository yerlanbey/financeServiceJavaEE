
package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import hw5.databean.CustomerInfo;
import hw5.databean.CustomerCredential;
import hw5.databean.CustomerInfo;
import hw5.databean.CustomerFunds;
import hw5.databean.Funds;
import hw5.formbean.BuyForm;
import hw5.formbean.CreateFundsForm;
import hw5.formbean.EmployeeLoginForm;
import hw5.model.Model;
import hw5.model.FundsDAO;
import hw5.model.CustomerInfoDAO;
import hw5.model.CustomerFundsDAO;

public class BuyAction extends Action 
{
	
    private FormBeanFactory<BuyForm> formBeanFactory = new FormBeanFactory<>(BuyForm.class);

    private FundsDAO FundDAO;
    private CustomerInfoDAO custInfoDAO;
    private CustomerFundsDAO custFundsDAO;

    public BuyAction(Model model) 
    {
    	FundDAO = model.getFundsDAO();
    	custInfoDAO = model.getCustomerInfoDAO();
    	custFundsDAO = model.getCustomerFundsDAO();
    }

    public String getName() 
    {
        return "buy.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
        	try {

                CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
                CustomerInfo[] customer_info = custInfoDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
                Funds[] funds = FundDAO.match();
                
            	request.setAttribute("customer_info", customer_info[0]);
                request.setAttribute("funds", funds);
                request.setAttribute("form",  new BuyForm());
                return ("buy.jsp");
            } catch (RollbackException e) {
                request.setAttribute("error",e.getMessage());
                return "error.jsp";
            	}

        }
		return "buy.do";
        
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
        	BuyForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            
            CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
            CustomerInfo[] customer_info = custInfoDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
            CustomerFunds[] customer_fund = custFundsDAO.match(MatchArg.max("id"));
            Funds[] fund_info = FundDAO.match(MatchArg.equals("fund_name", form.getFund_name()));
            CustomerInfo customer_read = custInfoDAO.read(customer.getCustomer_email());

            
            
        	Funds[] funds = FundDAO.match();
        	request.setAttribute("customer_info", customer_info[0]);
            request.setAttribute("funds", funds);
            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "buy.jsp";
            }
        	
            int id = Integer.parseInt(customer_fund[0].getId());
            
            String new_balance = Integer.toString(Integer.parseInt(customer_info[0].getBalance()) - Integer.parseInt(form.getNumber_of_shares()) * Integer.parseInt(fund_info[0].getCurrent_price()));
            
        	Funds fund = FundDAO.read(form.getFund_name());
            if (fund != null) {
                if (form.getAction().equals("Buy")) {
                	CustomerFunds newFund = new CustomerFunds();
                	newFund.setId(Integer.toString(id+1));
                	newFund.setFund_name(form.getFund_name());
                	newFund.setCustomer_email(customer_info[0].getCustomer_email());
                	newFund.setNumber_of_shares(form.getNumber_of_shares());
                	newFund.setTotal_value(Integer.toString(Integer.parseInt(form.getNumber_of_shares()) * Integer.parseInt(fund_info[0].getCurrent_price())));
                	
                	if(Integer.parseInt(form.getNumber_of_shares()) * Integer.parseInt(fund_info[0].getCurrent_price()) > Integer.parseInt(customer_info[0].getBalance()))
					{
                        form.addFormError("Not enough money. You have" + customer_info[0].getBalance() + "in hand," + "if you want buy you should get" 
					+ Integer.toString(Integer.parseInt(form.getNumber_of_shares()) * Integer.parseInt(fund_info[0].getCurrent_price()))+ "in your balance");
                        return "buy.jsp";
                	}
                	
                	
                    try {
                    	customer_read.setBalance(new_balance);
                    	custInfoDAO.update(customer_read);
                    	custFundsDAO.create(newFund);
                        return ("buy.do");
                    } catch (DuplicateKeyException e) {
                        form.addFormError("Error, please try again!");
                        return "buy.jsp";
                    }
                  
                }
            }else
            {
                form.addFormError("The fund name not exist");
                return "buy.jsp";
            }

       
        	return "buy.do";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
