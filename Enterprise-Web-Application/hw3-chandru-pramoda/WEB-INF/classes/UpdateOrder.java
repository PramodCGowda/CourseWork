import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);
        //check if the user is logged in
        if (!utility.isLoggedin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to View your Orders");
            response.sendRedirect("Login");
            return;
        }
        String username = utility.username();
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<form name ='UpdateOrder' action='UpdateOrder' method='get'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        String orderId = request.getParameter("orderId");
        String orderName = request.getParameter("orderId");
        if (request.getParameter("orderId") != null) {
//            int orderId = 0;
//            orderId = Integer.parseInt(request.getParameter("orderId"));
            ArrayList<OrderPayment> ListOrderPayment = new ArrayList<OrderPayment>();
            //get the order from file
            try {
                orderPayments = MySqlDataStoreUtilities.selectOrder();
            } catch (Exception e) {

            }

            for (OrderPayment oi : orderPayments.get(orderId)) {
                if (oi.getOrderName().equals(orderName)) {
                    LocalDate currentDate = LocalDate.now();
                    if (currentDate.isBefore(oi.getShipDate().minusDays(2))) {

                        pw.print("<h4 style='color:red'>Your Order is Updated</h4>");
                    } else {
                        pw.print("<h4 style='color:red'>Your Order cannot be updated</h4>");
                    }
                }
            }
            //remove all the orders from hashmap that exist in cancel list
            orderPayments.get(orderId).removeAll(ListOrderPayment);
            if (orderPayments.get(orderId).size() == 0) {
                orderPayments.remove(orderId);
            }
        } else {
            pw.print("<h4 style='color:red'>Please select any product</h4>");
        }


        pw.print("<table><tr></tr><tr></tr>");
        pw.print("<tr><td>");
        pw.print("First Name:</td>");
        pw.print("<td><input type='text' name='firstname'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Last Name:</td>");
        pw.print("<td><input type='text' name='lastname'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Address 1:</td>");
        pw.print("<td><input type='text' name='address1'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Address 2(optional):</td>");
        pw.print("<td><input type='text' name='address2'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("City:</td>");
        pw.print("<td><input type='text' name='city'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("State:</td>");
        pw.print("<td><input type='text' name='state'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Zip/Postal:</td>");
        pw.print("<td><input type='text' name='zipcode'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Phone Number:</td>");
        pw.print("<td><input type='text' name='phone'>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Please Select Delivery or Pickup:</td>");
        pw.print("<td>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("<input type='radio' id='delivery' name='order' value='Delivery'><label for='delivery'>Delivery</label></td>");
        pw.print("<td><input type='radio' id='pickup' name='order' value='Pickup'><label for='pickup'>Pickup</label>");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
        pw.print("Credit/AccountNo.:</td>");
        pw.print("<td><input type='text' name='creditCardNo'>");
        pw.print("</td></tr>");
        pw.print("<table><tr><td>");
        pw.print("</div>");
        pw.print("<p>For instore pickup, please select any one of the following store locations:</p> </td>");
        List<StoreDetails> list2 = new ArrayList<>();
        list2 = MySqlDataStoreUtilities.getAllStoreDetails();
        pw.print("<td><select name='storelocation' id='storelocation'>");
        for (StoreDetails storeDetails : list2) {
            pw.print("<option value='" + storeDetails.getId() + "'> " + storeDetails.getstreet() + " - " + storeDetails.getZipcode() + "</option>");
        }
        pw.print("</select>");
        pw.print("</td></tr></table>");
        pw.print("<tr><td colspan='2'>");
        pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }
}
