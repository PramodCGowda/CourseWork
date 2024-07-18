import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Layout from "../../components/layout";
import Form from "react-bootstrap/Form";
import { checkCredentials } from "../../controllers/auth";
import Navbar from "react-bootstrap/Navbar";

function LoginScreen() {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [usertype, setUserType] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    let data = checkCredentials(username, password, usertype);

    if (data) {
      localStorage.setItem("userDetails", JSON.stringify(data));
      localStorage.setItem("userId", data.id);
      window.location.href = "/";
    } else {
      alert("User Not Found !");
      setUserName("");
      setPassword("");
      setUserType("");
    }
  };

  const handleSignup = (event) => {
    window.location.href = "/signup";
  };

  return (
    <Layout>
      <Container>
        <h4>User Login: </h4>
        <Form
          style={{
            border: "1px solid #cacaca",
            padding: "24px",
            borderRadius: "12px",
          }}
        >
          <Form.Group className="mb-3" controlId="formBasicUsername">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              placeholder="username"
              value={username}
              onChange={(e) => setUserName(e.target.value)}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicUsertype">
            <Form.Label>usertype</Form.Label>
            <Form.Select
              value={usertype}
              onChange={(e) => setUserType(e.target.value)}
            >
              <option value="">usertype</option>
              <option value="customer">customer</option>
              <option value="retailer">retailer</option>
              <option value="salesman">salesman</option>
            </Form.Select>
          </Form.Group>
          <Button variant="primary" type="button" onClick={handleSubmit}>
            Login
          </Button>
          <Button variant="primary" type="button" onClick={handleSignup}>
            Signup
          </Button>
        </Form>
      </Container>
    </Layout>
  );
}

export default LoginScreen;
