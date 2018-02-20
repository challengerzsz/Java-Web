package com.bsb.learning.servlet;

import com.bsb.learning.data.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer", "/editCustomer", "/updateCustomer"})
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = -20L;
    private List<Customer> customers = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("zsz");
        customer1.setCity("BaoJi");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("jtt");
        customer2.setCity("AnQing");

        customers.add(customer1);
        customers.add(customer2);
    }

    private void sendCustomerList(HttpServletResponse resp) throws IOException {

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <title>Customers</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h2>Customers</h2>\n" +
                "    <ul>");
        for (Customer customer : customers) {
            printWriter.println("<li>" + customer.getName() + "(" + customer.getCity() + ")  (" +
            "<a href='editCustomer?id=" + customer.getId() + "'>edit</a>)");
        }
        printWriter.println("</ul>");
        printWriter.println("</body></html>");

    }
    private void sendEditCustomerForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        int customerId = 0;
        try {
            customerId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ex) { }

        Customer customer = getCustomer(customerId);
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <title>Edit Customer</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h2>Edit Customer</h2>\n" +
                "    <form method=\"post\" action=\"updateCustomer\">");
        printWriter.println("  <input type=\"hidden\" name=\"id\" value=\"" + customerId + "\">");
        printWriter.println("<table>\n" +
                "        <tr>\n" +
                "          <td>Name:</td>\n" +
                "          <td><input name=\"name\" value=\"" +
                customer.getName().replaceAll("'", "&#39;") + "\"></td>\n" +
                "        </tr>");
        printWriter.println("  <tr>\n" +
                "          <td>City:</td>\n" +
                "          <td><input name=\"city\" value=\"" +
                customer.getCity().replaceAll("'", "&#39;") +"\"/></td>\n" +
                "        </tr>");
        printWriter.println("<tr>\n" +
                "          <td colspan=\"2\" style=\"text-align:right\">\n" +
                "            <input type=\"submit\" value=\"Update\">\n" +
                "          </td>\n" +
                "        </tr>  \n" +
                "        <tr>\n" +
                "          <td colspan=\"2\">\n" +
                "            <a href=\"customer\">Customer List</a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </form>\n" +
                "  </body>\n" +
                "</html>");
    }

    private Customer getCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith("/customer")) {
            sendCustomerList(resp);
        } else if (uri.endsWith("/editCustomer")) {
            sendEditCustomerForm(req, resp);
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //update customer
        int customerId = 0;
        try {
            customerId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ex) { }

        Customer customer = getCustomer(customerId);
        if (customer != null) {
            customer.setName(req.getParameter("name"));
            customer.setCity(req.getParameter("city"));
        }
        sendCustomerList(resp);
    }




}
