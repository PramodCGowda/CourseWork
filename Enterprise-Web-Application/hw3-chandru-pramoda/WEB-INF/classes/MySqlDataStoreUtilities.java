import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;
    static String message;

    public static String getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthomes", "root", "root");
            message = "Successfull";
            return message;
        } catch (SQLException e) {
            message = "unsuccessful";
            return message;
        } catch (Exception e) {
            message = e.getMessage();
            return message;
        }
    }

    public static void Insertproducts() {
        try {
            getConnection();
            String truncatetableacc = "delete from Product_accessories;";
            PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
            pstt.executeUpdate();

            String truncatetableprod = "delete from  Productdetails;";
            PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
            psttprod.executeUpdate();

            String truncatetablestore = "delete from  Store;";
            PreparedStatement psttstore = conn.prepareStatement(truncatetablestore);
            psttstore.executeUpdate();

            String truncatetablecustomer = "delete from  Customers;";
            PreparedStatement psttcustomer = conn.prepareStatement(truncatetablecustomer);
            psttcustomer.executeUpdate();

            insertStoreDetails(); //insert store data

            insertCustomerDetails(); //insert store data

            String insertProductQurey = "INSERT INTO  Productdetails(ProductType,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productDescription,productQuantity)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";
            for (Map.Entry<String, Accessory> entry : SaxParserDataStore.accessories.entrySet()) {
                String name = "accessories";
                Accessory acc = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, acc.getId());
                pst.setString(3, acc.getName());
                pst.setDouble(4, acc.getPrice());
                pst.setString(5, acc.getImage());
                pst.setString(6, acc.getRetailer());
                pst.setString(7, acc.getCondition());
                pst.setDouble(8, acc.getDiscount());
                pst.setString(9, acc.getDescription());
                pst.setInt(10, acc.getQuantity());
                pst.executeUpdate();
            }

            for (Map.Entry<String, SmartDoorbell> entry : SaxParserDataStore.doorbells.entrySet()) {
                SmartDoorbell doorbell = entry.getValue();
                String name = "doorbells";


                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, doorbell.getId());
                pst.setString(3, doorbell.getName());
                pst.setDouble(4, doorbell.getPrice());
                pst.setString(5, doorbell.getImage());
                pst.setString(6, doorbell.getRetailer());
                pst.setString(7, doorbell.getCondition());
                pst.setDouble(8, doorbell.getDiscount());
                pst.setString(9, doorbell.getDescription());
                pst.setInt(10, doorbell.getQuantity());
                pst.executeUpdate();
                try {
                    HashMap<String, String> acc = doorbell.getAccessories();
                    String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    for (Map.Entry<String, String> accentry : acc.entrySet()) {
                        PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
                        pstacc.setString(1, doorbell.getId());
                        pstacc.setString(2, accentry.getValue());
                        pstacc.executeUpdate();
                    }
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
            for (Map.Entry<String, SmartDoorlock> entry : SaxParserDataStore.doorlocks.entrySet()) {
                String name = "doorlocks";
                SmartDoorlock doorlock = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, doorlock.getId());
                pst.setString(3, doorlock.getName());
                pst.setDouble(4, doorlock.getPrice());
                pst.setString(5, doorlock.getImage());
                pst.setString(6, doorlock.getRetailer());
                pst.setString(7, doorlock.getCondition());
                pst.setDouble(8, doorlock.getDiscount());
                pst.setString(9, doorlock.getDescription());
                pst.setInt(10, doorlock.getQuantity());
                pst.executeUpdate();


            }
            for (Map.Entry<String, SmartLighting> entry : SaxParserDataStore.lights.entrySet()) {
                String name = "lights";
                SmartLighting light = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, light.getId());
                pst.setString(3, light.getName());
                pst.setDouble(4, light.getPrice());
                pst.setString(5, light.getImage());
                pst.setString(6, light.getRetailer());
                pst.setString(7, light.getCondition());
                pst.setDouble(8, light.getDiscount());
                pst.setString(9, light.getDescription());
                pst.setInt(10, light.getQuantity());
                pst.executeUpdate();
            }

            for (Map.Entry<String, SmartSpeaker> entry : SaxParserDataStore.speakers.entrySet()) {
                String name = "speaker";
                SmartSpeaker speaker = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, speaker.getId());
                pst.setString(3, speaker.getName());
                pst.setDouble(4, speaker.getPrice());
                pst.setString(5, speaker.getImage());
                pst.setString(6, speaker.getRetailer());
                pst.setString(7, speaker.getCondition());
                pst.setDouble(8, speaker.getDiscount());
                pst.setString(9, speaker.getDescription());
                pst.setInt(10, speaker.getQuantity());
                pst.executeUpdate();
            }

            for (Map.Entry<String, SmartThermostat> entry : SaxParserDataStore.thermostats.entrySet()) {
                String name = "thermostat";
                SmartThermostat thermostat = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, thermostat.getId());
                pst.setString(3, thermostat.getName());
                pst.setDouble(4, thermostat.getPrice());
                pst.setString(5, thermostat.getImage());
                pst.setString(6, thermostat.getRetailer());
                pst.setString(7, thermostat.getCondition());
                pst.setDouble(8, thermostat.getDiscount());
                pst.setString(9, thermostat.getDescription());
                pst.setInt(10, thermostat.getQuantity());
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, SmartDoorbell> getDoorbells() {
        HashMap<String, SmartDoorbell> hm = new HashMap<String, SmartDoorbell>();
        try {
            getConnection();

            String selectDoorbell = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectDoorbell);
            pst.setString(1, "doorbells");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SmartDoorbell doorbell = new SmartDoorbell(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), doorbell);
                doorbell.setId(rs.getString("productId"));

                try {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1, rs.getString("productId"));
                    ResultSet rsacc = pstacc.executeQuery();

                    HashMap<String, String> acchashmap = new HashMap<String, String>();
                    while (rsacc.next()) {
                        if (rsacc.getString("accessoriesName") != null) {
                            acchashmap.put(rsacc.getString("accessoriesName"), rsacc.getString("accessoriesName"));
                            doorbell.setAccessories(acchashmap);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartDoorlock> getDoorlocks() {
        HashMap<String, SmartDoorlock> hm = new HashMap<String, SmartDoorlock>();
        try {
            getConnection();

            String selectDoorlock = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectDoorlock);
            pst.setString(1, "doorlocks");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SmartDoorlock doorlock = new SmartDoorlock(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), doorlock);
                doorlock.setId(rs.getString("productId"));

            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartLighting> getLights() {
        HashMap<String, SmartLighting> hm = new HashMap<String, SmartLighting>();
        try {
            getConnection();
            String selectLights = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectLights);
            pst.setString(1, "lights");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SmartLighting light = new SmartLighting(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), light);
                light.setId(rs.getString("productId"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartSpeaker> getSpeakers() {
        HashMap<String, SmartSpeaker> hm = new HashMap<String, SmartSpeaker>();
        try {
            getConnection();
            String selectSpeakers = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectSpeakers);
            pst.setString(1, "speaker");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SmartSpeaker speaker = new SmartSpeaker(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), speaker);
                speaker.setId(rs.getString("productId"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, SmartThermostat> getThermostats() {
        HashMap<String, SmartThermostat> hm = new HashMap<String, SmartThermostat>();
        try {
            getConnection();
            String selectThermostats = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectThermostats);
            pst.setString(1, "thermostat");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SmartThermostat thermostat = new SmartThermostat(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), thermostat);
                thermostat.setId(rs.getString("productId"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Accessory> getAccessories() {
        HashMap<String, Accessory> hm = new HashMap<String, Accessory>();
        try {
            getConnection();
            String selectAcc = "select * from  Productdetails where ProductType=? and productQuantity > 0";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            pst.setString(1, "accessories");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Accessory acc = new Accessory(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("productDescription"), rs.getInt("productQuantity"));
                hm.put(rs.getString("productId"), acc);
                acc.setId(rs.getString("productId"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static String addproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String prod, String productDescription, int productQuantity, String acc) {
        String msg = "Product is added successfully";
        try {
            getConnection();
            String addProductQurey = "INSERT INTO  Productdetails(ProductType,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productDescription,productQuantity)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1, producttype);
            pst.setString(2, productId);
            pst.setString(3, productName);
            pst.setDouble(4, productPrice);
            pst.setString(5, productImage);
            pst.setString(6, productManufacturer);
            pst.setString(7, productCondition);
            pst.setDouble(8, productDiscount);
            pst.setString(9, productDescription);
            pst.setInt(10, productQuantity);
            pst.executeUpdate();
            try {
                if (!acc.isEmpty()) {
                    String addaprodacc = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
                    pst1.setString(1, productId);
                    pst1.setString(2, acc);
                    pst1.executeUpdate();
                }
            } catch (Exception e) {
                msg = "Erro while adding the product";
                e.printStackTrace();
            }
        } catch (Exception e) {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }
        return msg;
    }

    public static String updateproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String productDescription, int productQuantity) {
        String msg = "Product is updated successfully";
        try {
            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?,productDescription=?, productQuantity=? where productId =?;";
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);
            pst.setString(1, productName);
            pst.setDouble(2, productPrice);
            pst.setString(3, productImage);
            pst.setString(4, productManufacturer);
            pst.setString(5, productCondition);
            pst.setDouble(6, productDiscount);
            pst.setString(7, productDescription);
            pst.setInt(8, productQuantity);
            pst.setString(9, productId);
            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();
        }
        return msg;
    }

    public static String deleteproducts(String productId) {
        String msg = "Product is deleted successfully";
        try {
            getConnection();
            String deleteproductsQuery = "Delete from Productdetails where productId=?";
            PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
            pst.setString(1, productId);

            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Proudct cannot be deleted";
        }
        return msg;
    }

    public static void deleteOrder(int orderId, String orderName) {
        try {
            getConnection();
            String deleteOrderQuery = "Delete from customerorders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.setString(2, orderName);
            pst.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        try {
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo) "
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            pst.execute();
        } catch (Exception e) {
        }
    }

    public static void insertOrder(int orderId, String user_id, String customer_name, String customer_address, String creditCardNo,
                                   String orderName, double orderPrice, LocalDate purchaseDate, LocalDate shipDate, String productId,
                                   String category, int quantity, double price, double shippingCost, double discount, double total_Sales, String storeID) {
        try {
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,user_id,customer_name,customer_address,creditCardNo," +
                    "orderName,orderPrice,userAddress,purchaseDate, shipDate, productId, category, quantity, price, shippingCost, discount, total_sales, Store_ID)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, user_id);
            pst.setString(3, customer_name);
            pst.setString(4, customer_address);
            pst.setString(5, creditCardNo);
            pst.setString(6, orderName);
            pst.setDouble(7, orderPrice);
            pst.setString(8, customer_address);
            pst.setDate(9, Date.valueOf(purchaseDate));
            pst.setDate(10, Date.valueOf(shipDate));
            pst.setString(11, productId);
            pst.setString(12, category);
            pst.setInt(13, quantity);
            pst.setDouble(14, price);
            pst.setDouble(15, shippingCost);
            pst.setDouble(16, discount);
            pst.setDouble(17, total_Sales);
            pst.setString(18, storeID);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String updateOrder(int orderId, double orderPrice, LocalDate shipDate) {
        String msg = "Product is updated successfully";
        try {
            getConnection();
            String updateProductQurey = "UPDATE customerOrders SET orderPrice=?,shipDate=? where orderId =?;";
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);
            pst.setDouble(1, orderPrice);
            pst.setDate(2, Date.valueOf(shipDate));
            pst.setInt(3, orderId);
            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();
        }
        return msg;
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try {
            getConnection();
            //select the table
            String selectOrderQuery = "select * from customerorders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));

                //add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("user_id"), rs.getString("customer_name"),
                        rs.getString("customer_address"),  rs.getString("creditCardNo"), rs.getString("orderName"),
                        rs.getDouble("orderPrice"), rs.getDate("purchaseDate").toLocalDate(), rs.getDate("shipDate").toLocalDate(),
                        rs.getString("productId"),  rs.getString("category"), rs.getInt("quantity"), rs.getDouble("price"),
                        rs.getDouble("shippingCost"), rs.getDouble("discount"), rs.getDouble("total_sales"), rs.getString("Store_ID"));
                listOrderPayment.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderPayments;
    }


    public static void insertUser(String username, String password, String repassword, String usertype) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, repassword);
            pst.setString(4, usertype);
            pst.execute();
        } catch (Exception e) {

        }
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static List<StoreDetails> getAllStoreDetails() {
        List<StoreDetails> list = new ArrayList<>();
        getConnection();
        String query = "SELECT s.* FROM Store s";
        try {
            PreparedStatement pm = conn.prepareStatement(query);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                StoreDetails StoreDetails = new StoreDetails(rs.getInt("storeID"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zipcode"));
                list.add(StoreDetails);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insertStoreDetails() {
        try {
            getConnection();
            String insertIntoStoreQuery = "INSERT INTO Store(StoreID,street,city,state,zipcode) "
                    + "VALUES (?,?,?,?,?);";
            List<StoreDetails> storeLocations = new ArrayList<>();
            // Create 10 store locations in Illinois
            storeLocations.add(new StoreDetails(1, "123 Main St", "Chicago", "Illinois", 60601));
            storeLocations.add(new StoreDetails(2, "456 Elm St", "Springfield", "Illinois", 62701));
            storeLocations.add(new StoreDetails(3, "789 Oak St", "Peoria", "Illinois", 61601));
            storeLocations.add(new StoreDetails(4, "101 Pine St", "Rockford", "Illinois", 61101));
            storeLocations.add(new StoreDetails(5, "222 Maple St", "Naperville", "Illinois", 60540));
            storeLocations.add(new StoreDetails(6, "333 Birch St", "Champaign", "Illinois", 61820));
            storeLocations.add(new StoreDetails(7, "444 Cedar St", "Bloomington", "Illinois", 61701));
            storeLocations.add(new StoreDetails(8, "555 Redwood St", "Decatur", "Illinois", 62521));
            storeLocations.add(new StoreDetails(9, "666 Walnut St", "Aurora", "Illinois", 60505));
            storeLocations.add(new StoreDetails(10, "777 Oakwood St", "Evanston", "Illinois", 60201));

            for (int i = 0; i < storeLocations.size(); i++) {
                StoreDetails sd = storeLocations.get(i);
                PreparedStatement pst = conn.prepareStatement(insertIntoStoreQuery);
                pst.setInt(1, sd.getId());
                pst.setString(2, sd.getstreet());
                pst.setString(3, sd.getCity());
                pst.setString(4, sd.getState());
                pst.setInt(5, sd.getZipcode());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertCustomerDetails() {
        try {
            getConnection();
            String insertIntoStoreQuery = "INSERT INTO Customers(customer_name,street,city,state,zipcode) "
                    + "VALUES (?,?,?,?,?);";
            List<CustomerDetails> customerDetails = new ArrayList<>();

            customerDetails.add(new CustomerDetails("Cus_1", "123 Main St", "Chicago", "Illinois", 60601));
            customerDetails.add(new CustomerDetails("Cus_2", "456 Elm St", "Springfield", "Illinois", 62701));
            customerDetails.add(new CustomerDetails("Cus_3", "789 Oak St", "Peoria", "Illinois", 61601));
            customerDetails.add(new CustomerDetails("Cus_4", "101 Pine St", "Rockford", "Illinois", 61101));
            customerDetails.add(new CustomerDetails("Cus_5", "222 Maple St", "Naperville", "Illinois", 60540));
            customerDetails.add(new CustomerDetails("Cus_6", "333 Birch St", "Champaign", "Illinois", 61820));
            customerDetails.add(new CustomerDetails("Cus_7", "444 Cedar St", "Bloomington", "Illinois", 61701));
            customerDetails.add(new CustomerDetails("Cus_8", "555 Redwood St", "Decatur", "Illinois", 62521));
            customerDetails.add(new CustomerDetails("Cus_9", "666 Walnut St", "Aurora", "Illinois", 60505));
            customerDetails.add(new CustomerDetails("Cus_10", "777 Oakwood St", "Evanston", "Illinois", 60201));

            for (int i = 0; i < customerDetails.size(); i++) {
                CustomerDetails cd = customerDetails.get(i);
                PreparedStatement pst = conn.prepareStatement(insertIntoStoreQuery);
                pst.setString(1, cd.getCustomerName());
                pst.setString(2, cd.getstreet());
                pst.setString(3, cd.getCity());
                pst.setString(4, cd.getState());
                pst.setInt(5, cd.getZipcode());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<Inventory>> getInventory() {
        HashMap<String, ArrayList<Inventory>> hm = new HashMap<String, ArrayList<Inventory>>();
        ArrayList<Inventory> inventory = new ArrayList<Inventory>();
        try {
            getConnection();
            String selectInventoryQuery = "select productId, productName, productPrice, productQuantity, ProductType  from Productdetails where ProductType != 'accessories'";
            PreparedStatement pst = conn.prepareStatement(selectInventoryQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                inventory.add(new Inventory(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inventory.size() == 0) {
            hm.put("error", null);
        } else {
            hm.put("all inventory", inventory);
        }
        return hm;
    }

    public HashMap<String, ArrayList<Inventory>> getOnSaleInventory() {
        HashMap<String, ArrayList<Inventory>> hm = new HashMap<String, ArrayList<Inventory>>();
        ArrayList<Inventory> inventory = new ArrayList<Inventory>();
        try {
            getConnection();
            String selectOnSaleInventory = "select productId, productName, productPrice, productQuantity, ProductType  from Productdetails where productQuantity >0";
            PreparedStatement pst = conn.prepareStatement(selectOnSaleInventory);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                inventory.add(new Inventory(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inventory.size() == 0) {
            hm.put("error", null);
        } else {
            hm.put("on sale", inventory);
        }
        return hm;
    }

    public HashMap<String, ArrayList<Inventory>> getOnDiscountInventory() {
        HashMap<String, ArrayList<Inventory>> hm = new HashMap<String, ArrayList<Inventory>>();
        ArrayList<Inventory> inventory = new ArrayList<Inventory>();
        try {
            getConnection();
            String selectOnDiscountInventory = "select productId, productName, productPrice, productQuantity, ProductType, productDiscount  from Productdetails where productQuantity >0 && productDiscount >0";
            PreparedStatement pst = conn.prepareStatement(selectOnDiscountInventory);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                inventory.add(new Inventory(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getDouble(6)));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inventory.size() == 0) {
            hm.put("error", null);
        } else {
            hm.put("On discount", inventory);
        }
        return hm;
    }

    public HashMap<String, ArrayList<Sales>> getAllSale() {
        HashMap<String, ArrayList<Sales>> hm = new HashMap<String, ArrayList<Sales>>();
        ArrayList<Sales> sales = new ArrayList<Sales>();
        try {
            getConnection();
            String query = "select c.orderName ,p.productName, p.productPrice,p.productPrice * sum(c.quantity) as totalsale, sum(c.quantity),p.ProductType from CustomerOrders as c Join Productdetails as p on c.orderName = p.productId group by c.orderName";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               sales.add(new Sales(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sales.size() == 0) {
            hm.put("error", null);
        } else {
            hm.put("sales", sales);
        }
        return hm;
    }

    public HashMap<String, ArrayList<DailySale>> getDailySale() {
        HashMap<String, ArrayList<DailySale>> hm = new HashMap<String, ArrayList<DailySale>>();
        ArrayList<DailySale> sale = new ArrayList<DailySale>();
        try {
            getConnection();
            String SQL = "select DATE(DATE_SUB(c.shipDate, Interval 15 day)),sum(c.quantity) , sum(c.quantity * p.productPrice) from CustomerOrders as c Join Productdetails as p on c.orderName = p.productId   group by DATE(DATE_SUB(c.shipDate, Interval 15 day)) order by DATE(DATE_SUB(c.shipDate, Interval 15 day)) ASC";
            PreparedStatement pst = conn.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sale.add(new DailySale(rs.getString(1), rs.getInt(2), rs.getDouble(3)));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sale.size() == 0) {
            hm.put("error", null);
        } else {
            hm.put("daily sales", sale);
        }
        return hm;
    }

    public static String updateQuantity(String productName,int productQuantity) {
        String msg = "Product is updated successfully";
        try {
            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productQuantity=? where productName =?;";
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);
            pst.setInt(1, productQuantity);
            pst.setString(2, productName);
            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();
        }
        return msg;
    }
}
