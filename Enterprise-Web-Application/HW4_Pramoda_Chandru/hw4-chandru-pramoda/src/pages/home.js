import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Row, Col, Form, InputGroup } from "react-bootstrap";
import Carousel from "react-bootstrap/Carousel";
import Image from "react-bootstrap/Image";

import Layout from "../components/layout";
import Product from "../components/Product";
import {
  getProductByName,
  getProductByCategory,
  getProducts,
  getBestProducts,
} from "../controllers";

function FluidExample1() {
  return <Image style={{}} src="/images/Accessories/amazon_echo.jpg" />;
}

function FluidExample2() {
  return (
    <Image style={{}} src="/images/SmartDoorbells/google_doorbell.jpg" fluid />
  );
}

function FluidExample3() {
  return <Image style={{}} src="/images/SmartDoorlocks/eufy.jpg" fluid />;
}

function HomeScreen() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [title, setTitle] = useState("BestSellers");

  useEffect(() => {
    const products = getBestProducts();
    setProducts(products);
  }, []);

  const listenClick = (type, name) => {
    console.log("click", type, name);
    if (type === "category") {
      navigate("/category/" + name);
    } else if (type === "maker") {
      navigate("/manufacturer/" + name);
    } else {
      navigate("/");
      console.log("test", getProductByName(name.toLocaleLowerCase()));
      setProducts(getProductByName(name.toLocaleLowerCase()));
      setTitle("Search result for " + name);
    }
  };

  return (
    <Layout cb={(type, name) => listenClick(type, name)}>
      <div style={{ marginTop: "40px" }}>
        <Carousel
          style={{ borderBottom: "2px solid #cacaca", paddingBlock: "24px" }}
        >
          <Carousel.Item style={{ textAlign: "center" }}>
            <FluidExample1 text="First slide" />
          </Carousel.Item>
          <Carousel.Item style={{ textAlign: "center" }}>
            <FluidExample2 text="First slide" />
          </Carousel.Item>
          <Carousel.Item style={{ textAlign: "center" }}>
            <FluidExample3 text="First slide" />
          </Carousel.Item>
        </Carousel>

        {/* <Carousel style={{ backgroundColor: "#eee", marginBottom: "80px" }}>
          <Carousel.Item interval={1000}>
            <FluidExample1 text="First slide" />
            <Carousel.Caption>
              <h3>First slide label</h3>
              <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
            </Carousel.Caption>
          </Carousel.Item>
          <Carousel.Item interval={500}>
            <FluidExample2 text="Second slide" />
            <Carousel.Caption>
              <h3>Second slide label</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </Carousel.Caption>
          </Carousel.Item>
          <Carousel.Item>
            <FluidExample3 text="Third slide" />
            <Carousel.Caption>
              <h3>Third slide label</h3>
              <p>
                Praesent commodo cursus magna, vel scelerisque nisl consectetur.
              </p>
            </Carousel.Caption>
          </Carousel.Item>
        </Carousel> */}

        {/* -------------------- */}

        <div className="d-flex justify-content-between mt-5">
          <h4>{title}</h4>
          <h6>Showing {products.length} results</h6>
        </div>
        <Row className="mt-3">
          {products.map((product) => (
            <Col key={product.name + product.id} lg={4}>
              <Product product={product} />
            </Col>
          ))}
        </Row>
      </div>
    </Layout>
  );
}

export default HomeScreen;
