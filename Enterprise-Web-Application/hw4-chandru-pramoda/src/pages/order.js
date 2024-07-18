import { useEffect, useState } from "react";
import { Button, Table, Form, Row, Col } from "react-bootstrap";

import Layout from "../components/layout";
import { generateNewOrder } from "../controllers/purchase";

function OrderScreen() {
  const [sum, setSum] = useState(0);
  const [cart, setCart] = useState([]);

  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");

  useEffect(() => {
    const initialSum = 0;
    const sumWithInitial = cart.reduce(
      (accumulator, currentValue) => accumulator + Number(currentValue.price),
      initialSum
    );
    setSum(sumWithInitial);
  }, [cart]);

  useEffect(() => {
    let cart = localStorage.getItem("cart");
    let userId = localStorage.getItem("userId");
    if (cart) {
      let cartObj = JSON.parse(cart);
      if (cartObj.hasOwnProperty(userId)) {
        setCart(cartObj[userId]);
      }
    }
  }, []);

  function createOrder() {
    let data = {
      products: cart,
      price: sum,
      deliveryDetails: {
        email,
        address,
      },
    };
    let statusObj = generateNewOrder(data);
    if (statusObj.status) {
      alert(`Order Successfull Generated with order Id: ${statusObj.id}`);
      window.location.href = "/";
    } else {
      alert("Something Went Wrong !");
    }
  }

  return (
    <Layout>
      <div className="d-flex justify-content-between">
        <h4>Cart</h4>
      </div>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Item</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {cart.map((item, index) => {
            return (
              <tr key={item.name + index}>
                <td>{index + 1}</td>
                <td>{item.name}</td>
                <td>{item.price}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
      <h3 className="text-success">Total: ${sum}</h3>

      {/* --------------------- */}
      <div
        style={{
          marginTop: "40px",
          border: "1px solid #cacaca",
          borderRadius: "12px",
          padding: "30px 48px",
        }}
      >
        <Form>
          <h3>Delivery Details </h3>
          <Row className="mb-3 mt-4">
            <Form.Group as={Col} controlId="formGridEmail">
              <Form.Label>Email</Form.Label>
              <Form.Control
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                type="email"
                placeholder="Enter email"
              />
            </Form.Group>
          </Row>

          <Form.Group className="mb-3" controlId="formGridAddress1">
            <Form.Label>Address</Form.Label>
            <Form.Control
              type="text"
              value={address}
              placeholder="1234 Main St"
              onChange={(e) => setAddress(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formGridAddress2">
            <Form.Label>Address 2</Form.Label>
            <Form.Control placeholder="Apartment, studio, or floor" />
          </Form.Group>

          <Row className="mb-3">
            <Form.Group as={Col} controlId="formGridCity">
              <Form.Label>City</Form.Label>
              <Form.Control />
            </Form.Group>

            <Form.Group as={Col} controlId="formGridState">
              <Form.Label>State</Form.Label>
              <Form.Select defaultValue="Choose...">
                <option>Choose...</option>
                <option value="alabama">Alabama</option>
                <option value="illinois">Illinois</option>
                <option value="arizona">Arizona</option>
                <option value="california">California</option>
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} controlId="formGridZip">
              <Form.Label>Zip</Form.Label>
              <Form.Control />
            </Form.Group>
          </Row>

          <div key={`inline-radio`} className="mb-3">
            <Form.Check
              inline
              label="Pickup"
              name="deliveryType"
              type={"radio"}
              id={`inline-radio-1`}
            />
            <Form.Check
              inline
              label="Delivery"
              name="deliveryType"
              type={"radio"}
              id={`inline-radio-2`}
            />
          </div>
          <div className="mb-3">
            <Form.Select aria-label="Default select example">
              <option>Open this select menu</option>
              <option value="1"> 123 Main St - 60601</option>
              <option value="2"> 777 Oakwood St - 60201</option>
              <option value="3"> 456 Elm St - 62701</option>
              <option value="4"> 789 Oak St - 61601</option>
              <option value="5"> 101 Pine St - 61101</option>
              <option value="6"> 222 Maple St - 60540</option>
              <option value="7"> 333 Birch St - 61820</option>
              <option value="8"> 444 Cedar St - 61701</option>
              <option value="9"> 555 Redwood St - 62521</option>
              <option value="10"> 666 Walnut St - 60505</option>
            </Form.Select>
          </div>

          <Button variant="primary" type="button" onClick={createOrder}>
            Submit
          </Button>
        </Form>
      </div>
    </Layout>
  );
}

export default OrderScreen;
