import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Row, Col, Form, InputGroup } from "react-bootstrap";
import Carousel from "react-bootstrap/Carousel";
import { useParams } from "react-router";
import Image from "react-bootstrap/Image";

import Layout from "../components/layout";
import Product from "../components/Product";
import {
  getProductByName,
  getProductByCategory,
  getProducts,
  getProductByMaker,
} from "../controllers";

function ManufacturerScreen() {
  const [product, setProduct] = useState("");
  const { name } = useParams();

  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [searchQuery, setSearchQuery] = useState("amazon");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [maker, setMaker] = useState("");

  useEffect(() => {
    console.log(name);
    const data = getProductByMaker(name);
    setProducts(data);
  }, [name]);

  const listenClick = (type, name) => {
    // setSelectedCategory(name);
    console.log("man", type, name);
    if (type === "category") {
      navigate("/category/" + name);
    } else if (type === "maker") {
      setMaker(name);
      navigate("/manufacturer/" + name);
    } else {
      console.log(name);
      navigate("/productInfo/" + name);
    }
  };

  return (
    <Layout cb={(maker, name) => listenClick(maker, name)}>
      <div>
        {/* -------------------- */}
        <div className="d-flex justify-content-between">
          <h4>{maker}</h4>
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

export default ManufacturerScreen;
