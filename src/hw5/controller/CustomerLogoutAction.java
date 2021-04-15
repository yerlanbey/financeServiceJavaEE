package hw5.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hw5.formbean.CustomerLoginForm;
import hw5.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class CustomerLogoutAction extends Action {

    public CustomerLogoutAction(Model model) {
    }

    public String getName() {
        return "signout.do";
    }
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("customer", null);

        request.setAttribute("form", new CustomerLoginForm());
        return "customer.jsp";
    }
}
