create database smarthomes;

use smarthomes;

create table Registration(username varchar(100),password varchar(100),repassword varchar(100),
usertype varchar(100));

Create table Store
(
StoreID varchar(20),
street varchar(100),
city varchar(20),
state varchar(10),
zipcode integer(5),
Primary key(StoreID)
);

Create table CustomerOrders
(
OrderId integer,
user_id varchar(100),
customer_name varchar(100),
customer_address varchar(100),
creditCardNo varchar(100),
orderName varchar(40),
orderPrice double,
userAddress varchar(40),
purchaseDate Date,
shipDate Date,
productId varchar(100),
category varchar(100),
quantity integer,
price double,
shippingCost double,
discount double,
total_sales double,
Store_ID varchar(20),
Primary key(OrderId,customer_name,orderName),
FOREIGN KEY (Store_ID) REFERENCES Store(StoreID) ON DELETE SET NULL
);

Create table Productdetails
(
ProductType varchar(100),
productId varchar(100),
productName varchar(100),
productPrice double,
productImage varchar(100),
productManufacturer varchar(100),
productCondition varchar(100),
productDiscount double,
productDescription varchar(250),
productQuantity integer,
Primary key(productId)
);

CREATE TABLE Product_accessories (
    productName varchar(100),
    accessoriesName  varchar(100),


    FOREIGN KEY (productName) REFERENCES Productdetails(productId) ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (accessoriesName) REFERENCES Productdetails(productId) ON DELETE SET NULL
        ON UPDATE CASCADE
);



Create table Customers
(
customer_name varchar(20),
street varchar(100),
city varchar(20),
state varchar(20),
zipcode integer(5),
Primary key(customer_name)
);
