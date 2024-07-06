import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router";
import Layout from "../components/layout";
import { getProductById, getProductByName } from "../controllers";
import { Row, Col, Button } from "react-bootstrap";
import { saveItemToCart } from "../controllers/purchase";

const ProductInfo = () => {
  const [product, setProduct] = useState("");
  const params = useParams();
  const navigate = useNavigate();

  const buyProduct = () => {
    saveItemToCart(product);
    navigate("/cart");
  };

  useEffect(() => {
    let data = getProductByName(params.name.toLocaleLowerCase());
    setProduct(data);
  }, []);

  return (
    <Layout className="product-list">
      <Row>
        <Col md={6}>
          <img
            src={product.image}
            alt={product.name}
            style={{ width: "100%", height: "50%" }}
          />
        </Col>
        <Col md={6}>
          <h1>{product.name}</h1>
          <p>Description: {product.description}</p>
          <p>Category: {product.category}</p>
          <p>Price: ${product.price}</p>
          <p>Manufacturer: {product.manufacturer}</p>
          <p>Condition: {product.condition}</p>
          <p>Discount: {product.discount}%</p>
          <Button
            style={{ width: "100%" }}
            onClick={buyProduct}
            variant="secondary"
          >
            Buy
          </Button>
        </Col>
      </Row>
    </Layout>
  );
};

export default ProductInfo;
