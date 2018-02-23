package com.bsb.learning.servlet;


import com.bsb.learning.data.Product;
import com.bsb.learning.data.ShoppingItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "ShoppingCartServlet",
        urlPatterns = {"/products", "/viewProductDetails", "/addToCart", "/viewCart"})
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = -20L;
    private static final String CART_ATTRIBUTE = "cart";

    private List<Product> products = new ArrayList<>();
    //transform to US currency
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public void init() throws ServletException {
        products.add(new Product(1, "Nike", "sports", 200.5F));
        products.add(new Product(2, "Adidas", "sports", 150));
        products.add(new Product(3, "NewB", "sports", 120));
        products.add(new Product(4, "YiShion", "dress", 100));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith("/products")) {
            sendProductList(resp);
        } else if (uri.endsWith("/viewProductDetails")) {
            sendProductDetails(req, resp);
        } else if (uri.endsWith("viewCart")) {
            showCart(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add to the cart
        int productId = 0;
        int quantity = 0;

        try {
            productId = Integer.parseInt(req.getParameter("id"));
            quantity = Integer.parseInt(req.getParameter("quantity"));
        } catch (NumberFormatException ex) {}

        Product product = getProduct(productId);
        if (product != null && quantity >= 0) {
            ShoppingItem shoppingItem = new ShoppingItem(product, quantity);
            HttpSession session = req.getSession();
            List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute(CART_ATTRIBUTE, cart);
            }
            cart.add(shoppingItem);
        }
        sendProductList(resp);
    }




    private void sendProductList(HttpServletResponse resp) {
        try (PrintWriter printWriter = resp.getWriter()) {
            resp.setContentType("text/html");
            printWriter.println("<html lang=\"en\" dir=\"ltr\">\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <title>Products</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h2>Products</h2>\n" +
                    "    <ul>");
            for (Product product : products) {
                printWriter.println("<li>" + product.getName() + "(" + currencyFormat.format(product.getPrice()) +
                        ") (" + "<a href='viewProductDetails?id=" + product.getId() + "'>Details</a>)");
            }
            printWriter.println("</ul><a href='viewCart'>View Cart</a></body></html>");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void sendProductDetails(HttpServletRequest req, HttpServletResponse resp) {


        try (PrintWriter printWriter = resp.getWriter()) {
            resp.setContentType("text/html");
            int productId = 0;
            productId = Integer.parseInt(req.getParameter("id"));

            Product product = getProduct(productId);
            if (product != null) {
                printWriter.println("<html lang=\"en\" dir=\"ltr\">\n" +
                        "  <head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>Product Details</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <h2>Product Details</h2>\n" +
                        "    <form action=\"addToCart\" method=\"post\">\n" +
                        "      <input type=\"hidden\" name=\"id\" value=\"" + productId + "\">");
                printWriter.println("<table>\n" +
                        "        <tr>\n" +
                        "          <td>Name:</td>\n" +
                        "          <td>" + product.getName() + "</td>\n" +
                        "        </tr>");
                printWriter.println("<tr>\n" +
                        "          <td>Description:</td>\n" +
                        "          <td>" + product.getDescription() + "</td>\n" +
                        "        </tr>");
                printWriter.println("<tr>\n" +
                        "          <tr>\n" +
                        "            <td> <input name = \"quantity\"></td>\n" +
                        "            <td> <input type = \"submit\" value = \"Buy\"></td>\n" +
                        "          </tr>\n" +
                        "        </tr>\n" +
                        "      </table>\n" +
                        "    </form>\n" +
                        "  </body>\n" +
                        "</html>");
            } else {
                printWriter.println("No product found");
            }
        } catch (NumberFormatException ex) {

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
        try (PrintWriter printWriter = resp.getWriter()) {
            resp.setContentType("text/html");
            printWriter.println("<html lang=\"en\" dir=\"ltr\">\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <title>Shopping Cart</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <a href=\"products\">\"Product List\"</a>\n" +
                    "    <table>\n" +
                    "      <tr>\n" +
                    "        <td style=\"width:150px\">Quantity</td>\n" +
                    "        <td style=\"width:150px\">Price</td>\n" +
                    "        <td>Amount</td>\n" +
                    "      </tr>");
            double total = 0.0;
            for (ShoppingItem shoppingItem : cart) {
                Product product = shoppingItem.getProduct();
                int quantity = shoppingItem.getQuantity();
                if (quantity != 0) {
                    float price = product.getPrice();
                    printWriter.println("<tr>\n" +
                            "        <td>" + quantity + "</td>");
                    printWriter.println("<td>" + product.getName() + "</td>");
                    printWriter.println("<td>" + currencyFormat.format(price) + "</td>");
                    double subtotal = price * quantity;

                    printWriter.println("<td>" + currencyFormat.format(subtotal) + "</td>");
                    total += subtotal;
                    printWriter.println("</tr>");
                }
            }
            printWriter.println("<tr><td colspan=\"4\" style=\"text-align:right\">Total:" +
                    currencyFormat.format(total) + "</td></tr>");
            printWriter.println("</table></body></html>");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private Product getProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
