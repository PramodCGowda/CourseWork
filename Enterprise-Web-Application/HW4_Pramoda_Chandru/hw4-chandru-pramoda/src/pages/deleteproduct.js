import { useEffect, useState } from "react";
import { Row, Col, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Layout from "../components/layout";
import { deleteItemFromList } from "../controllers/delete";

const DeleteProductScreen = () => {
  const navigate = useNavigate();

  const [productName, setProductName] = useState("");

  const handleChange = (e) => {
    setProductName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(productName);
  };

  const deleteProduct = () => {
    deleteItemFromList(productName);
    navigate("/productDelete");
  };

  return (
    <Layout className="product-list">
      <h1>Delete Product</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group as={Col} controlId="formGridProductName">
          <Form.Label>Product Name</Form.Label>
          <Form.Control
            type="text"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
          />
        </Form.Group>
      </Form>
      <Button
        className="mt-3"
        variant="primary"
        type="submit"
        onClick={deleteProduct}
      >
        Delete Product
      </Button>
    </Layout>
  );
};

export default DeleteProductScreen;
