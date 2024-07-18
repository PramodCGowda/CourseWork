import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SmartDoorlockList")

public class SmartDoorlockList extends HttpServlet {
    /* Trending Page Displays all the doorlocks and their Information in Smart Homes */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		HashMap<String, SmartDoorlock> smartDoorlocks = new HashMap<String, SmartDoorlock>();
		try {
			smartDoorlocks = MySqlDataStoreUtilities.getDoorlocks();
		} catch(Exception e) {
		}

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, SmartDoorlock> hm = new HashMap<String, SmartDoorlock>();

        if (CategoryName == null) {
            hm.putAll(smartDoorlocks);
            name = "";
        } else {
            if (CategoryName.equals("Yale")) {
                for (Map.Entry<String, SmartDoorlock> entry : smartDoorlocks.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Yale")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Yale";
            } else if (CategoryName.equals("Eufy")) {
                for (Map.Entry<String, SmartDoorlock> entry : smartDoorlocks.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Eufy")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Eufy";
            } else if (CategoryName.equals("Lockly")) {
                for (Map.Entry<String, SmartDoorlock> entry : smartDoorlocks.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Lockly")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Lockly";
            }
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + "SmartDoorlock's</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, SmartDoorlock> entry : hm.entrySet()) {
            SmartDoorlock doorlock = entry.getValue();
            if (i % 3 == 1) pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + doorlock.getName() + "</h3>");
            pw.print("<strong>$" + doorlock.getPrice() + "</strong><ul>");
            pw.print("<li id='item'><img src='images/SmartDoorlocks/" + doorlock.getImage() + "' alt='' /></li>");
            pw.print("<h5>" + doorlock.getDescription() + "</h5></li>");

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='doorlocks'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='doorlocks'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+doorlock.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='doorlocks'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size)
                pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");
    }
}
