import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import { saveUser } from "../../controllers/auth";
import Layout from "../../components/layout";

function SignupPage() {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    //localStorage.setItem("userDetails", userName);
    window.location.href = "/login";
  };

  return (
    <Layout>
      <Container>
        <h4>User Signup: </h4>
        <Form
          style={{
            border: "1px solid #cacaca",
            padding: "24px",
            borderRadius: "12px",
          }}
          onSubmit={handleSubmit}
        >
          <Form.Group className="mb-3" controlId="formBasicUserName">
            <Form.Label>UserName</Form.Label>
            <Form.Control
              type="text"
              placeholder="userName"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicUsertype">
            <Form.Label>userType</Form.Label>
            <Form.Select
              value={userType}
              onChange={(e) => setUserType(e.target.value)}
              required
            >
              <option value="">usertype</option>
              <option value="customer">customer</option>
              <option value="retailer">retailer</option>
              <option value="salesman">salesman</option>
            </Form.Select>
          </Form.Group>
          <Button variant="primary" type="submit">
            Signup
          </Button>
        </Form>
      </Container>
    </Layout>
  );
}

export default SignupPage;
