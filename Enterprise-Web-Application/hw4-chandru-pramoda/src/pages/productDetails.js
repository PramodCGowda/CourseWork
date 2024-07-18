import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router";
import Layout from "../components/layout";
import { getAccessoriesById, getProductById } from "../controllers";
import { Row, Col, Button } from "react-bootstrap";
import { saveItemToCart } from "../controllers/purchase";
import { Card, ButtonGroup } from "react-bootstrap";
import Product from "../components/Product";

const ProductDetails = () => {
  const [product, setProduct] = useState([]);
  const [acc, setAcc] = useState([]);
  const params = useParams();
  const navigate = useNavigate();

  const buyProduct = () => {
    saveItemToCart(product);
    navigate("/cart");
  };

  useEffect(() => {
    let data = getProductById(params.id);
    setProduct(data);
    console.log("===>", data);
  }, []);

  useEffect(() => {
    console.log(params.id);
    let data = getProductById(params.id);
    data = getAccessoriesById(data.accessories);
    console.log(data);
    setAcc(data);
  }, []);

  return (
    <Layout className="product-list">
      <Row>
        <Col md={6}>
          <div
            style={{
              backgroundColor: "#fff",
              border: "1px solid #cacaca",
              padding: "16px",
              textAlign: "center",
            }}
          >
            <img
              style={{
                objectFit: "contain",
                objectPosition: "center",
                height: "300px",
                width: "auto",
              }}
              src={product.image}
              alt={product.name}
            />
          </div>
        </Col>
        <Col md={6}>
          <h1>{product.name}</h1>
          <p>Description: {product.description}</p>
          <p>Category: {product.category}</p>
          <p>Price: ${product.price}</p>
          <p>Manufacturer: {product.manufacturer}</p>
          <p>Condition: {product.condition}</p>
          <p>Discount: {product.discount}%</p>
          <Button style={{ width: "100%" }} onClick={buyProduct} variant="dark">
            Buy Now
          </Button>
        </Col>
      </Row>
      <div>
        <h2>Accessories Available</h2>
        <Row className="mt-3">
          {acc.map((product) => (
            <Col key={product.name + product.id} lg={4}>
              <Product product={product} />
            </Col>
          ))}
        </Row>
      </div>
    </Layout>
  );
};

export default ProductDetails;
