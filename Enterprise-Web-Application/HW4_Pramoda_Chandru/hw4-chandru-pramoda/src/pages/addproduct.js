import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router";
import Layout from "../components/layout";
import { getProductById, getProductByName } from "../controllers";
import { Row, Col, Button, Form } from "react-bootstrap";
import { saveItemToList } from "../controllers/add";

const AddProductScreen = () => {
  const navigate = useNavigate();

  const [productName, setProductName] = useState("");
  const handleChange = (e) => {
    setProductName(e.target.value);
  };
  const addProduct = () => {
    saveItemToList(productName);
    navigate("/productAdd");
  };

  return (
    <Layout className="product-list">
      <h1>Add Product</h1>
      <Form>
        <Form.Group as={Col} controlId="formGridName">
          <Form.Label>Product Name</Form.Label>
          <Form.Control />
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
            <option value="">choose category</option>
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
          Add Product
        </Button>
      </Form>
    </Layout>
  );
};

export default AddProductScreen;
