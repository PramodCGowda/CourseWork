import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet {
    HttpServletRequest req;
    PrintWriter pw;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest req, PrintWriter pw) {
        this.req = req;
        this.pw = pw;
        this.url = this.getFullURL();
        this.session = req.getSession(true);
    }

	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

    public void printHtml(String file) {
        String result = HtmlToString(file);
        //to print the right navigation in header of username cart and logout etc
        if (file == "Header.html") {
            result = result + "<div id='menu' style='float: right;'><ul>";
            if (session.getAttribute("username") != null) {
                String username = session.getAttribute("username").toString();
                username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
                if (session.getAttribute("usertype").equals("manager")) {
                    result = result
                            + "<div class='dropdown'>" +
                            "  <button class='dropbtn' style = 'background-color: transparent'>SM Menu</button>" +
                            "  <div class='dropdown-content'>" +
                            "    <a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a>" +
                            "    <a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a>" +
                            "    <a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a>" +
                            "    <a href='DataVisualization'><span class='glyphicon'>Trending</span></a>" +
                            "    <a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a>" +
                            "    <a href='InventoryReport'><span class='glyphicon'>InventoryReport</span></a>" +
                            "    <a href='SalesReport'><span class='glyphicon'>SalesReport</span></a>" +
                            "  </div>" +
                            "</div>"
                            + "<li><a><span class='glyphicon'>Hello," + username + "</span></a></li>"
                            + "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";

                } else if (session.getAttribute("usertype").equals("retailer")) {
                    result = result + "<li><a href='Registration'><span class='glyphicon'>CreateCustomer</span></a></li>"
                            + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
                            + "<li><a><span class='glyphicon'>Hello," + username + "</span></a></li>"
                            + "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
                } else {
                    result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
                            + "<li><a><span class='glyphicon'>Hello," + username + "</span></a></li>"
                            + "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
                            + "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
                }
            } else
                result = result + "<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>" + "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
            result = result + "<li><a href='Cart'><span class='glyphicon'>Cart(" + CartCount() + ")</span></a></li></ul></div></div><div id='page'>";
            pw.print(result);
        } else
            pw.print(result);
    }


    /*  getFullURL Function - Reconstructs the URL user request  */

    public String getFullURL() {
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    /*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
    public String HtmlToString(String file) {
        String result = null;
        try {
            String webPage = url + file;
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            result = sb.toString();
        } catch (Exception e) {
        }
        return result;
    }

    /*  logout Function removes the username , usertype attributes from the session variable*/

    public void logout() {
        session.removeAttribute("username");
        session.removeAttribute("usertype");
    }

    /*  logout Function checks whether the user is loggedIn or Not*/

    public boolean isLoggedin() {
        if (session.getAttribute("username") == null)
            return false;
        return true;
    }

    /*  username Function returns the username from the session variable.*/

    public String username() {
        if (session.getAttribute("username") != null)
            return session.getAttribute("username").toString();
        return null;
    }

    /*  usertype Function returns the usertype from the session variable.*/
    public String usertype() {
        if (session.getAttribute("usertype") != null)
            return session.getAttribute("usertype").toString();
        return null;
    }

    /*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
    public User getUser() {
        String usertype = usertype();
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            hm = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e) {
        }
        User user = hm.get(username());
        return user;
    }

    /*  getCustomerOrders Function gets  the Orders for the user*/
    public ArrayList<OrderItem> getCustomerOrders() {
        ArrayList<OrderItem> order = new ArrayList<OrderItem>();
        if (OrdersHashMap.orders.containsKey(username()))
            order = OrdersHashMap.orders.get(username());
        return order;
    }

    /*  getOrdersPaymentSize Function gets  the size of OrderPayment */
    public int getOrderPaymentSize() {

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        int size = 0;
        try {
            orderPayments = MySqlDataStoreUtilities.selectOrder();

        } catch (Exception e) {

        }
        for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
            size = entry.getKey();
        }

        return size;
    }

    /*  CartCount Function gets  the size of User Orders*/
    public int CartCount() {
        if (isLoggedin())
            return getCustomerOrders().size();
        return 0;
    }

    /* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

    public void storeProduct(String name, String type, String maker, String acc) {
        if (!OrdersHashMap.orders.containsKey(username())) {
            ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
            OrdersHashMap.orders.put(username(), arr);
        }
        ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
        HashMap<String, SmartDoorbell> allDoorbells = new HashMap<String, SmartDoorbell>();
        HashMap<String, SmartLighting> allLights = new HashMap<String, SmartLighting>();
        HashMap<String, SmartDoorlock> allDoorlocks = new HashMap<String, SmartDoorlock>();
        HashMap<String, SmartSpeaker> allSpeakers = new HashMap<String, SmartSpeaker>();
        HashMap<String, SmartThermostat> allThermostat = new HashMap<String, SmartThermostat>();
        HashMap<String, Accessory> allaccessories = new HashMap<String, Accessory>();
        if (type.equals("doorbells")) {
            SmartDoorbell doorbell;
            try {
                allDoorbells = MySqlDataStoreUtilities.getDoorbells();

            } catch (Exception e) {

            }
            doorbell = allDoorbells.get(name);
            OrderItem orderitem = new OrderItem(doorbell.getName(), doorbell.getPrice(), doorbell.getImage(), doorbell.getRetailer(), type);
            orderItems.add(orderitem);
        }
        if (type.equals("doorlocks")) {
            SmartDoorlock doorlock = null;
            try {
                allDoorlocks = MySqlDataStoreUtilities.getDoorlocks();
            } catch (Exception e) {

            }
            doorlock = allDoorlocks.get(name);
            OrderItem orderitem = new OrderItem(doorlock.getName(), doorlock.getPrice(), doorlock.getImage(), doorlock.getRetailer(), type);
            orderItems.add(orderitem);
        }
        if (type.equals("lights")) {
            SmartLighting light = null;
            try {
                allLights = MySqlDataStoreUtilities.getLights();
            } catch (Exception e) {

            }
            light = allLights.get(name);
            OrderItem orderitem = new OrderItem(light.getName(), light.getPrice(), light.getImage(), light.getRetailer(), type);
            orderItems.add(orderitem);
        }
        if (type.equals("speakers")) {
            SmartSpeaker speaker = null;
            try {
                allSpeakers = MySqlDataStoreUtilities.getSpeakers();
            } catch (Exception e) {

            }
            speaker = allSpeakers.get(name);
            OrderItem orderitem = new OrderItem(speaker.getName(), speaker.getPrice(), speaker.getImage(), speaker.getRetailer(), type);
            orderItems.add(orderitem);
        }
        if (type.equals("thermostats")) {
            SmartThermostat thermostat = null;
            try {
                allThermostat = MySqlDataStoreUtilities.getThermostats();
            } catch (Exception e) {

            }
            thermostat = allThermostat.get(name);
            OrderItem orderitem = new OrderItem(thermostat.getName(), thermostat.getPrice(), thermostat.getImage(), thermostat.getRetailer(), type);
            orderItems.add(orderitem);
        }
        if (type.equals("accessories")) {
            try {
                allaccessories = MySqlDataStoreUtilities.getAccessories();
            } catch (Exception e) {

            }
            Accessory accessory = allaccessories.get(name);
            OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(), type);
            orderItems.add(orderitem);
        }

    }

    // store the payment details for orders
    public void storePayment(int orderId,
                             String orderName, double orderPrice, String userAddress, String creditCardNo, String customer) {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        // get the payment details file
        try {
            orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception e) {

        }
        if (orderPayments == null) {
            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        }
        // if there exist order id already add it into same list for order id or create a new record with order id

        if (!orderPayments.containsKey(orderId)) {
            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
            orderPayments.put(orderId, arr);
        }
        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);

        OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
        listOrderPayment.add(orderpayment);

        // add order details into database
        try {
            if (session.getAttribute("usertype").equals("retailer")) {
                MySqlDataStoreUtilities.insertOrder(orderId, customer, orderName, orderPrice, userAddress, creditCardNo);
            } else {
                MySqlDataStoreUtilities.insertOrder(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
            }
        } catch (Exception e) {
            System.out.println("inside exception file not written properly");
        }
    }

    public String storeReview(String productname, String producttype, String productmaker, String reviewRating, String reviewdate, String reviewText, String reatilerpin, String price, String city) {
        String message = MongoDBDataStoreUtilities.insertReview(productname, username(), producttype, productmaker, reviewRating, reviewdate, reviewText, reatilerpin, price, city);
        if (!message.equals("Successfull")) {
            return "UnSuccessfull";
        } else {
            HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
            try {
                reviews = MongoDBDataStoreUtilities.selectReview();
            } catch (Exception e) {

            }
            if (reviews == null) {
                reviews = new HashMap<String, ArrayList<Review>>();
            }
            // if there exist product review already add it into same list for productname or create a new record with product name

            if (!reviews.containsKey(productname)) {
                ArrayList<Review> arr = new ArrayList<Review>();
                reviews.put(productname, arr);
            }
            ArrayList<Review> listReview = reviews.get(productname);
            Review review = new Review(productname, username(), producttype, productmaker, reviewRating, reviewdate, reviewText, reatilerpin, price, city);
            listReview.add(review);

            // add Reviews into database

            return "Successfull";
        }
    }

    public String storeReview(String productname, String producttype, String price, String StoreID, String StoreZip, String StoreCity, String StoreState,
                              String ProductOnSale, String ManufacturerName, String ManufacturerRebate, String UserID, String UserAge, String UserGender,
                              String UserOccupation, String reviewRating, String reviewdate, String reviewText) {
        String message = MongoDBDataStoreUtilities.insertReview(username(), productname, producttype, price, StoreID, StoreZip, StoreCity, StoreState, ProductOnSale,
               ManufacturerName, ManufacturerRebate, UserID, UserAge, UserGender, UserOccupation, reviewRating, reviewdate, reviewText);
        if (!message.equals("Successfull")) {
            return "UnSuccessfull";
        } else {
            HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
            try {
                reviews = MongoDBDataStoreUtilities.selectReview();
            } catch (Exception e) {

            }
            if (reviews == null) {
                reviews = new HashMap<String, ArrayList<Review>>();
            }
            // if there exist product review already add it into same list for productname or create a new record with product name

            if (!reviews.containsKey(productname)) {
                ArrayList<Review> arr = new ArrayList<Review>();
                reviews.put(productname, arr);
            }
            ArrayList<Review> listReview = reviews.get(productname);
            Review review = new Review(username(), productname, producttype, price, StoreID, StoreZip, StoreCity, StoreState, ProductOnSale,
                    ManufacturerName, ManufacturerRebate, UserID, UserAge, UserGender, UserOccupation, reviewRating, reviewdate, reviewText);
            listReview.add(review);

            // add Reviews into database

            return "Successfull";
        }
    }

    /* getConsoles Functions returns the Hashmap with all consoles in the store.*/

    public HashMap<String, SmartDoorbell> getDoorbells() {
        HashMap<String, SmartDoorbell> hm = new HashMap<String, SmartDoorbell>();
        hm.putAll(SaxParserDataStore.doorbells);
        return hm;
    }

    /* getGames Functions returns the  Hashmap with all Games in the store.*/

    public HashMap<String, SmartDoorlock> getDoorlocks() {
        HashMap<String, SmartDoorlock> hm = new HashMap<String, SmartDoorlock>();
        hm.putAll(SaxParserDataStore.doorlocks);
        return hm;
    }

    /* getTablets Functions returns the Hashmap with all Tablet in the store.*/

    public HashMap<String, SmartLighting> getLights() {
        HashMap<String, SmartLighting> hm = new HashMap<String, SmartLighting>();
        hm.putAll(SaxParserDataStore.lights);
        return hm;
    }

    public HashMap<String, SmartSpeaker> getSpeakers() {
        HashMap<String, SmartSpeaker> hm = new HashMap<String, SmartSpeaker>();
        hm.putAll(SaxParserDataStore.speakers);
        return hm;
    }

    public HashMap<String, SmartThermostat> getThermostats() {
        HashMap<String, SmartThermostat> hm = new HashMap<String, SmartThermostat>();
        hm.putAll(SaxParserDataStore.thermostats);
        return hm;
    }

    /* getProducts Functions returns the Arraylist of consoles in the store.*/

    public ArrayList<String> getProducts() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartDoorlock> entry : getDoorlocks().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    /* getProducts Functions returns the Arraylist of games in the store.*/

    public ArrayList<String> getProductsLocks() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartDoorlock> entry : getDoorlocks().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    /* getProducts Functions returns the Arraylist of Tablets in the store.*/

    public ArrayList<String> getProductsLights() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartLighting> entry : getLights().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getProductsSpeakers() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartSpeaker> entry : getSpeakers().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getProductsThermostats() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartThermostat> entry : getThermostats().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    private static LocalDate getCurrentDate() throws ParseException {
        // Get the current date and time
        Date currentDate = new Date();

        // Define a date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Format the current date as a string
        String formattedDate = dateFormat.format(currentDate);

        // Parse the formatted date into a LocalDate
        return LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public void storePayment(int orderId, String userId, String customername, String customeraddress, String creditCardNo, String orderName,
                             double orderTotal, double productPrice1, double shippingCost, String productType, String deliveryPickup, String storelocation, int quantity) {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        // get the payment details file
        try {
            orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception e) {

        }
        if (orderPayments == null) {
            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        }
        // if there exist order id already add it into same list for order id or create a new record with order id

        if (!orderPayments.containsKey(orderId)) {
            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
            orderPayments.put(orderId, arr);
        }
        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
        LocalDate purchaseDate = null, shipDate = null;
        try {
            purchaseDate = getCurrentDate();
            shipDate = purchaseDate.plusDays(15);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map pDetails = getProductDetails(productType, orderName);
        double productPrice = Utilities.roundDecimal((Double)(pDetails.get("price")));
        double productDiscount = Utilities.roundDecimal((Double)(pDetails.get("discount")));
        shippingCost = Utilities.roundDecimal(shippingCost);
        productDiscount = Utilities.roundDecimal(productDiscount);
        orderTotal = Utilities.roundDecimal(orderTotal);
        String storeId = deliveryPickup.equals("Pickup") ? storelocation : "1";
        quantity = 1;
        OrderPayment orderpayment = new OrderPayment(orderId, userId, customername, customeraddress, creditCardNo, orderName, orderTotal, purchaseDate, shipDate,
                (String) pDetails.get("id"), productType, quantity, productPrice, shippingCost, productDiscount, 123, storeId);
        listOrderPayment.add(orderpayment);
        if(productType == "accessories") {
            orderId = orderId + 1;
        }
         //add order details into database
        try {
            if (session.getAttribute("usertype").equals("retailer")) {
                MySqlDataStoreUtilities.insertOrder(orderId, userId, customername, customeraddress, creditCardNo, orderName, orderTotal, purchaseDate, shipDate,
                        (String) pDetails.get("id"), productType, quantity,  (Double) pDetails.get("price"), shippingCost, (Double) pDetails.get("discount"), 123, storeId);
            } else {
                MySqlDataStoreUtilities.insertOrder(orderId, userId, username(), customeraddress, creditCardNo, orderName, orderTotal, purchaseDate, shipDate,
                        (String) pDetails.get("id"), productType, quantity, (Double) pDetails.get("price"), shippingCost, (Double) pDetails.get("discount"), 123, storeId);
            }
        } catch (Exception e) {
            System.out.println("inside exception file not written properly");
        }
    }

    private Map getProductDetails(String productType, String orderName) {
        Map map = new HashMap();
        switch(productType) {
            case "doorbells":
                HashMap<String, SmartDoorbell> hm = MySqlDataStoreUtilities.getDoorbells();
                SmartDoorbell doorbell = hm.get(orderName);
                map.put("id", doorbell.getId());
                map.put("name", doorbell.getName());
                map.put("price", doorbell.getPrice());
                map.put("discount", doorbell.getDiscount());
                break;
            case "doorlocks":
                HashMap<String, SmartDoorlock> hm1 = MySqlDataStoreUtilities.getDoorlocks();
                SmartDoorlock doorlock = hm1.get(orderName);
                map.put("id", doorlock.getId());
                map.put("name", doorlock.getName());
                map.put("price", doorlock.getPrice());
                map.put("discount", doorlock.getDiscount());
                break;
            case "lights":
                HashMap<String, SmartLighting> hm2 = MySqlDataStoreUtilities.getLights();
                SmartLighting light = hm2.get(orderName);
                map.put("id", light.getId());
                map.put("name", light.getName());
                map.put("price", light.getPrice());
                map.put("discount", light.getDiscount());
                break;
            case "speakers":
                HashMap<String, SmartSpeaker> hm3 = MySqlDataStoreUtilities.getSpeakers();
                SmartSpeaker speaker = hm3.get(orderName);
                map.put("id", speaker.getId());
                map.put("name", speaker.getName());
                map.put("price", speaker.getPrice());
                map.put("discount", speaker.getDiscount());
                break;
            case "thermostats":
                HashMap<String, SmartThermostat> hm4 = MySqlDataStoreUtilities.getThermostats();
                SmartThermostat thermostat = hm4.get(orderName);
                map.put("id", thermostat.getId());
                map.put("name", thermostat.getName());
                map.put("price", thermostat.getPrice());
                map.put("discount", thermostat.getDiscount());
                break;
            case "accessories":
                HashMap<String, Accessory> hm5 = MySqlDataStoreUtilities.getAccessories();
                Accessory acc = hm5.get(orderName);
                map.put("id", acc.getId());
                map.put("name", acc.getName());
                map.put("price", acc.getPrice());
                map.put("discount", acc.getDiscount());
                break;
        }
        return map;
    }

    public static double roundDecimal(double value) {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(value));
    }

    public static void updateQuanity(String productType, String productName) {
        switch (productType) {
            case "lights":
                SaxParserDataStore.lights.get(productName).setQuantity(SaxParserDataStore.lights.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.lights.get(productName).getQuantity());
                break;
            case "doorlocks":
                SaxParserDataStore.doorlocks.get(productName).setQuantity(SaxParserDataStore.doorlocks.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.doorlocks.get(productName).getQuantity());
                break;
            case "doorbells":
                SaxParserDataStore.doorbells.get(productName).setQuantity(SaxParserDataStore.doorbells.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.doorbells.get(productName).getQuantity());
                break;
            case "speaker":
                SaxParserDataStore.speakers.get(productName).setQuantity(SaxParserDataStore.speakers.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.speakers.get(productName).getQuantity());
                break;
            case "thermostat":
                SaxParserDataStore.thermostats.get(productName).setQuantity(SaxParserDataStore.thermostats.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.thermostats.get(productName).getQuantity());
                break;
            case "accessories":
                SaxParserDataStore.accessories.get(productName).setQuantity(SaxParserDataStore.accessories.get(productName).getQuantity() - 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.accessories.get(productName).getQuantity());
                break;
        }
    }

    public static void updateQuanity_increment(String productType, String productName) {
        switch (productType) {
            case "lights":
                SaxParserDataStore.lights.get(productName).setQuantity(SaxParserDataStore.lights.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.lights.get(productName).getQuantity());
                break;
            case "doorlocks":
                SaxParserDataStore.doorlocks.get(productName).setQuantity(SaxParserDataStore.doorlocks.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.doorlocks.get(productName).getQuantity());
                break;
            case "doorbells":
                SaxParserDataStore.doorbells.get(productName).setQuantity(SaxParserDataStore.doorbells.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.doorbells.get(productName).getQuantity());
                break;
            case "speaker":
                SaxParserDataStore.speakers.get(productName).setQuantity(SaxParserDataStore.speakers.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.speakers.get(productName).getQuantity());
                break;
            case "thermostat":
                SaxParserDataStore.thermostats.get(productName).setQuantity(SaxParserDataStore.thermostats.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.thermostats.get(productName).getQuantity());
                break;
            case "accessories":
                SaxParserDataStore.accessories.get(productName).setQuantity(SaxParserDataStore.accessories.get(productName).getQuantity() + 1);
                MySqlDataStoreUtilities.updateQuantity(productName, SaxParserDataStore.accessories.get(productName).getQuantity());
                break;
        }
    }

    public static void addProduct(String productType, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String productDescription, int productQuantity, String acc) {
        switch (productType) {
            case "lights":
                SaxParserDataStore.lights.put(productId, new SmartLighting(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity));
                break;
            case "doorlocks":
                SaxParserDataStore.doorlocks.put(productId, new SmartDoorlock(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity));
                break;
            case "doorbells":
                SaxParserDataStore.doorbells.put(productId, new SmartDoorbell(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity, acc));
                break;
            case "speaker":
                SaxParserDataStore.speakers.put(productId, new SmartSpeaker(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity));
                break;
            case "thermostat":
                SaxParserDataStore.thermostats.put(productId, new SmartThermostat(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity));
                break;
            case "accessories":
                SaxParserDataStore.accessories.put(productId, new Accessory(productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productDescription, productQuantity));
                break;
        }
    }

    public static void removeProduct(String productType, String productId) {
        switch (productType) {
            case "lights":
                SaxParserDataStore.lights.remove(productId);
                break;
            case "doorlocks":
                SaxParserDataStore.doorlocks.remove(productId);
                break;
            case "doorbells":
                SaxParserDataStore.doorbells.remove(productId);
                break;
            case "speaker":
                SaxParserDataStore.speakers.remove(productId);
                break;
            case "thermostat":
                SaxParserDataStore.thermostats.remove(productId);
                break;
            case "accessories":
                SaxParserDataStore.accessories.remove(productId);
                break;
        }
    }
}