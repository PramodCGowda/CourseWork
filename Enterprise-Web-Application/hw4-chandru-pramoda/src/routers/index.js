import * as React from "react";
import { createBrowserRouter } from "react-router-dom";

import HomeScreen from "../pages/home";
import CartScreen from "../pages/cart";
import OrderScreen from "../pages/order";
import ViewOrderScreen from "../pages/orderDetails";
import LoginScreen from "../pages/auth/login";
import SignupScreen from "../pages/auth/signup";
import CategoryScreen from "../pages/categories";
import ManufacturerScreen from "../pages/manufacture";
import ProductDetails from "../pages/productDetails";
import ProductInfo from "../pages/productInfo";
import AddProductScreen from "../pages/addproduct";
import DeleteProductScreen from "../pages/deleteproduct";
import UpdateProductScreen from "../pages/updateproduct";
import ProductAdd from "../pages/productadd";
import ProductUpdate from "../pages/productupdate";
import ProductDelete from "../pages/productdelete";

export const router = createBrowserRouter([
  { path: "/", element: <HomeScreen /> },
  { path: "/cart", element: <CartScreen /> },
  { path: "/login", element: <LoginScreen /> },
  { path: "/orders", element: <OrderScreen /> },
  { path: "/logout", element: <LoginScreen /> },
  { path: "/signup", element: <SignupScreen /> },
  { path: "/viewOrder", element: <ViewOrderScreen /> },
  { path: "/product/:id", element: <ProductDetails /> },
  { path: "/productInfo/:name", element: <ProductInfo /> },
  { path: "/category/:name", element: <CategoryScreen /> },
  { path: "/manufacturer/:name", element: <ManufacturerScreen /> },
  { path: "/addproduct", element: <AddProductScreen /> },
  { path: "/deleteProduct", element: <DeleteProductScreen /> },
  { path: "/updateProduct", element: <UpdateProductScreen /> },
  { path: "/productAdd", element: <ProductAdd /> },
  { path: "/productDelete", element: <ProductDelete /> },
  { path: "/productUpdate", element: <ProductUpdate /> },
]);
