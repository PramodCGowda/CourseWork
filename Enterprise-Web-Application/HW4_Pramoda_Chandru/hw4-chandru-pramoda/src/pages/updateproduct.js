import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router";
import Layout from "../components/layout";
import { getProductById, getProductByName } from "../controllers";
import { Row, Col, Button, Form } from "react-bootstrap";
import { updateItemToList } from "../controllers/update";

const AddProductScreen = () => {
  const navigate = useNavigate();

  const [productName, setProductName] = useState("");
  const handleChange = (e) => {
    setProductName(e.target.value);
  };
  const addProduct = () => {
    updateItemToList(productName);
    navigate("/productUpdate");
  };

  return (
    <Layout className="product-list">
      <h1>Update Product</h1>
      <Form>
        <Form.Group as={Col} controlId="formGridName">
          <Form.Label>Product Name</Form.Label>
          <Form.Control required />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridDescription">
          <Form.Label>Product Description</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridImage">
          <Form.Label>Product Image</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridManufacturer">
          <Form.Label>Product Manufacturer</Form.Label>
          <Form.Control />
        </Form.Group>
        <Form.Group as={Col} controlId="formGridPrice">
          <Form.Label>Product Price</Form.Label>
          <Form.Control />
        </Form.Group>
        <Form.Group as={Col} controlId="formGridCondition">
          <Form.Label>Product Condition</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridDiscount">
          <Form.Label>Product Discount</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridState">
          <Form.Label>State</Form.Label>
          <Form.Select defaultValue="Choose Type">
            <option value="">Choose Category</option>
            <option value="doorbell">Doorbell</option>
            <option value="doorlock">Doorlock</option>
            <option value="speaker">Speaker</option>
            <option value="light">Light</option>
            <option value="thermostat">Thermostat</option>
          </Form.Select>
        </Form.Group>
        <Button
          variant="primary"
          type="submit"
          onClick={() => {
            addProduct();
          }}
        >
          Submit
        </Button>
      </Form>
    </Layout>
  );
};

export default AddProductScreen;
