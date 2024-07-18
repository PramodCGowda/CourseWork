

import java.io.IOException;
import java.io.PrintWriter;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        review(request, response);
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to view Review");
                response.sendRedirect("Login");
                return;
            }
            String productName = request.getParameter("name");
            HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
            String userName = "";
            String productType = "";
            String price;
            String StoreID = "";
            String StoreZip = "";
            String StoreCity = "";
            String StoreState = "";
            String ProductOnSale = "";
            String ManufacturerName = "";
            String ManufacturerRebate = "";
            String UserID = "";
            String UserAge = "";
            String UserGender = "";
            String UserOccupation = "";
            String reviewRating = "";
            String reviewDate = "";
            String reviewText = "";

            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");

            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(productName)) {
                    pw.println("<h2>There are no reviews for this product.</h2>");
                } else {
                    for (Review r : hm.get(productName)) {
                        pw.print("<table class='gridtable'>");
                        pw.print("<tr>");
                        pw.print("<td> Product Name: </td>");
                        productName = r.getProductName();
                        pw.print("<td>" + productName + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> userName: </td>");
                        userName = r.getUserName();
                        pw.print("<td>" + userName + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> productType: </td>");
                        productType = r.getProductType();
                        pw.print("<td>" + productType + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> price: </td>");
                        price = r.getProductprice();
                        pw.print("<td>" + price + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> StoreID: </td>");
                        StoreID = r.getStoreId();
                        pw.print("<td>" + StoreID + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> StoreZip: </td>");
                        StoreZip = r.getStoreZip();
                        pw.print("<td>" + StoreZip + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> StoreCity: </td>");
                        StoreCity = r.getStoreCity();
                        pw.print("<td>" + StoreCity + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> StoreState: </td>");
                        StoreState = r.getStoreState();
                        pw.print("<td>" + StoreState + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> ProductOnSale: </td>");
                        ProductOnSale = r.getProductOnSale();
                        pw.print("<td>" + ProductOnSale + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> ManufacturerName: </td>");
                        ManufacturerName = r.getManufacturerName();
                        pw.print("<td>" + ManufacturerName + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> ManufacturerRebate: </td>");
                        ManufacturerRebate = r.getManufacturerRebate();
                        pw.print("<td>" + ManufacturerRebate + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> UserID: </td>");
                        UserID = r.getUserId();
                        pw.print("<td>" + UserID + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> UserAge: </td>");
                        UserAge = r.getUserage();
                        pw.print("<td>" + UserAge + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> UserGender: </td>");
                        UserGender = r.getUserGender();
                        pw.print("<td>" + UserGender + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> UserOccupation: </td>");
                        UserOccupation = r.getUserOccupation();
                        pw.print("<td>" + UserOccupation + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> reviewRating: </td>");
                        reviewRating = r.getReviewrating();
                        pw.print("<td>" + reviewRating + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> Review Date: </td>");
                        reviewDate = r.getReviewdate().toString();
                        pw.print("<td>" + reviewDate + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<td> Review Text: </td>");
                        reviewText = r.getReviewtext();
                        pw.print("<td>" + reviewText + "</td>");
                        pw.print("</tr>");
                        pw.println("</table>");
                    }

                }
            }
            pw.print("</div></div></div>");
            utility.printHtml("Footer.html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
    }
}