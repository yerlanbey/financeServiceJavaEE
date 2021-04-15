
package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import hw5.databean.CustomerInfo;
import hw5.databean.Funds;

import hw5.formbean.CreateFundsForm;

import hw5.model.Model;

import hw5.model.FundsDAO;

public class CreateFundsAction extends Action {
    private FormBeanFactory<CreateFundsForm> formBeanFactory = new FormBeanFactory<>(CreateFundsForm.class);


    private FundsDAO fundsDAO;


    public CreateFundsAction(Model model) {

    	fundsDAO = model.getFundsDAO();
    }

    public String getName() {
        return "fund.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // Otherwise, just display the login page.
        request.setAttribute("form", new CreateFundsForm());
        return "fund.jsp";
        
        
    }

    public String performPost(HttpServletRequest request) {
        try {
        	CreateFundsForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "fund.jsp";
            }

            // Look up the user
            Funds funds = fundsDAO.read(form.getFund_name());
            System.out.print(form.getFund_name());

            if (funds == null) {
                if (form.getAction().equals("Fund")) {
                	Funds newFund = new Funds();
                	newFund.setFund_name(form.getFund_name());
                	newFund.setCurrent_price(form.getCurrent_price());
                	fundsDAO.create(newFund);
                    return ("fund.do");
                  
                }
            }else {
                form.addFieldError("fund_name", "The fund name already exist");
                return "fund.jsp";
            }
            return "fund.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
