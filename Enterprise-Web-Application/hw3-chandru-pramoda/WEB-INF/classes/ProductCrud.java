import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");

        String msg = "good";
        String producttype = "", productId = "", productName = "", productImage = "", productManufacturer = "", productCondition = "", prod = "", productDescription = "", productAcc = "";
        double productPrice = 0.0, productDiscount = 0.0;
        int productQuantity = 0;
        HashMap<String, SmartDoorbell> allDoorbells = new HashMap<String, SmartDoorbell>();
        HashMap<String, SmartDoorlock> allDoorlocks = new HashMap<String, SmartDoorlock>();
        HashMap<String, SmartSpeaker> allSpeakers = new HashMap<String, SmartSpeaker>();
        HashMap<String, SmartLighting> allLights = new HashMap<String, SmartLighting>();
        HashMap<String, SmartThermostat> allThermostats = new HashMap<String, SmartThermostat>();
        HashMap<String, Accessory> allaccessories = new HashMap<String, Accessory>();
        if (action.equals("add") || action.equals("update")) {
            producttype = request.getParameter("producttype");
            productId = request.getParameter("productId");
            productName = request.getParameter("productName");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productImage = request.getParameter("productImage");
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
            productDescription = request.getParameter("productDescription");
            productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            productAcc = request.getParameter("product");
        } else {
            productId = request.getParameter("productId");
        }
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if (action.equals("add")) {
            if (producttype.equals("doorbells")) {
                allDoorbells = MySqlDataStoreUtilities.getDoorbells();
                if (allDoorbells.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("doorlocks")) {
                allDoorlocks = MySqlDataStoreUtilities.getDoorlocks();
                if (allDoorlocks.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("lights")) {
                allLights = MySqlDataStoreUtilities.getLights();
                if (allLights.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("speakers")) {
                allSpeakers = MySqlDataStoreUtilities.getSpeakers();
                if (allSpeakers.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("thermostats")) {
                allThermostats = MySqlDataStoreUtilities.getThermostats();
                if (allThermostats.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("accessories")) {
                if (!request.getParameter("product").isEmpty()) {
                    prod = request.getParameter("product");
                    allDoorbells = MySqlDataStoreUtilities.getDoorbells();
                    if (allDoorbells.containsKey(prod)) {
                        allaccessories = MySqlDataStoreUtilities.getAccessories();
                        if (allaccessories.containsKey(productId)) {
                            msg = "Product already available";
                        }
                    } else {
                        msg = "The product related to accessories is not available";
                    }


                } else {
                    msg = "Please add the product name";
                }

            }
            if (msg.equals("good")) {
                try {
                    Utilities.addProduct(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription,productQuantity, productAcc);
                    msg = MySqlDataStoreUtilities.addproducts(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, prod, productDescription,productQuantity, productAcc);
                } catch (Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        } else if (action.equals("update")) {
            if (producttype.equals("doorbells")) {
                allDoorbells = MySqlDataStoreUtilities.getDoorbells();
                if (!allDoorbells.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("doorlocks")) {
                allDoorlocks = MySqlDataStoreUtilities.getDoorlocks();
                if (!allDoorlocks.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("lights")) {
                allLights = MySqlDataStoreUtilities.getLights();
                if (!allLights.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("speakers")) {
                allSpeakers = MySqlDataStoreUtilities.getSpeakers();
                if (!allSpeakers.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("thermostats")) {
                allThermostats = MySqlDataStoreUtilities.getThermostats();
                if (!allThermostats.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("accessories")) {
                allaccessories = MySqlDataStoreUtilities.getAccessories();
                if (!allaccessories.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            if (msg.equals("good")) {

                try {
                    Utilities.addProduct(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription,productQuantity, productAcc);
                    msg = MySqlDataStoreUtilities.updateproducts(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription,productQuantity);
                } catch (Exception e) {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }
        } else if (action.equals("delete")) {
            msg = "bad";
            allDoorbells = MySqlDataStoreUtilities.getDoorbells();
            if (allDoorbells.containsKey(productId)) {
                msg = "good";

            }
            allDoorlocks = MySqlDataStoreUtilities.getDoorlocks();
            if (allDoorlocks.containsKey(productId)) {
                msg = "good";
            }

            allLights = MySqlDataStoreUtilities.getLights();
            if (allLights.containsKey(productId)) {
                msg = "good";
            }
            allSpeakers = MySqlDataStoreUtilities.getSpeakers();
            if (allSpeakers.containsKey(productId)) {
                msg = "good";
            }
            allThermostats = MySqlDataStoreUtilities.getThermostats();
            if (allThermostats.containsKey(productId)) {
                msg = "good";
            }

            allaccessories = MySqlDataStoreUtilities.getAccessories();
            if (allaccessories.containsKey(productId)) {
                msg = "good";
            }

            if (msg.equals("good")) {

                try {
                    Utilities.removeProduct(producttype, productId);
                    msg = MySqlDataStoreUtilities.deleteproducts(productId);
                } catch (Exception e) {
                    msg = "Product cannot be deleted";
                }
                msg = "Product has been successfully deleted";
            } else {
                msg = "Product not available";
            }
        }

        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>" + msg + "</h4>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }
}