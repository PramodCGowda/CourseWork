import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/RemoveFromCart")


public class RemoveFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String orderItemName = request.getParameter("orderItemName");

        /* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

        Utilities utility = new Utilities(request, pw);
        ArrayList<OrderItem> orderItems = utility.getCustomerOrders();
        for(OrderItem oi : orderItems){
            if(oi.getName().equalsIgnoreCase(orderItemName)){
                orderItems.remove(oi);
                displayCart(request,response);
                break;
            }
        }
        response.sendRedirect("Cart");
    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
        Carousel carousel = new Carousel();
        if(!utility.isLoggedin()){
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
        pw.print("</h2><div class='entry'>");
        // pw.print("<form id='Cart' name ='Cart' action='CheckOut' method='post'>");
        if(utility.CartCount()>0)
        {
            int i = 1;
            double total = 0;
            pw.print("<table class='gridable'>");
            for (OrderItem oi : utility.getCustomerOrders())
            {
                pw.print("<form name ='CartItem' action='RemoveFromCart' method='post'>"+
                        "<tr><td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>"+
                        "<input type='hidden' name='orderItemName' value='"+oi.getName()+"'>"+
                        "<td><input type='submit' name='RemoveFromCart' value='RemoveItem'></td></tr></form>");
                total = total +oi.getPrice();
                i++;
            }
            pw.print("</table>");
            pw.print("<tr><th></th><th>Total:   </th><th>"+total+"</th>");
            pw.print("<form name ='ChkoutBtn' action='CheckOut' method='post'><tr><td></td><td></td><td></td>"+
                    "<td><input type='hidden' name='orderTotal' value='"+total+"'>"+
                    "<input type='submit' name='CheckOut' value='CheckOut'></td></tr></table></form>");
            /* This code is calling Carousel.java code to implement carousel feature*/
            pw.print(carousel.carouselfeature(utility));
        }
        else
        {
            pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
        }
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }
}