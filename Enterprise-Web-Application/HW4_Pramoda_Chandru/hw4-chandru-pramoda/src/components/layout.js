import React, { useEffect, useState } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import {
  Button,
  Navbar,
  Nav,
  InputGroup,
  Form,
  Row,
  Col,
  ListGroup,
} from "react-bootstrap";

import Header from "../components/header";
import Footer from "../components/footer";
import { Container } from "react-bootstrap";
import {
  getProductByName,
  getProductByCategory,
  getProducts,
} from "../controllers";

function Layout({ children, cb = () => {} }) {
  const [selectedCategory, setSelectedCategory] = useState("");
  const [products, setProducts] = useState([]);
  const [searchValue, setSearchValue] = useState("");

  useEffect(() => {
    if (selectedCategory) {
      const data = getProductByCategory(selectedCategory);
      setProducts(data);
    }
  }, [selectedCategory]); // Category

  const handleSearchChange = (e) => {
    setSearchValue(e.target.value);
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    console.log(searchValue);
    cb("search", searchValue);
  };

  const navigate = useNavigate();
  return (
    <>
      <Header />
      {/* .........Body Begins........... */}
      <Container>
        <Navbar expand="lg" className="">
          <Container fluid>
            <Navbar.Toggle aria-controls="navbarScroll" />
            <Navbar.Collapse id="navbarScroll">
              <Nav
                className="me-auto my-2 my-lg-0"
                style={{ maxHeight: "100px" }}
                navbarScroll
              >
                <Nav.Link as={NavLink} to="/">
                  Home
                </Nav.Link>
                <Nav.Link onClick={() => cb("category", "all")}>All</Nav.Link>
                <Nav.Link onClick={() => cb("category", "doorbell")}>
                  SmartDoorbell
                </Nav.Link>
                <Nav.Link onClick={() => cb("category", "doorlock")}>
                  SmartDoorlock
                </Nav.Link>
                <Nav.Link onClick={() => cb("category", "light")}>
                  SmartLighting
                </Nav.Link>
                <Nav.Link onClick={() => cb("category", "speaker")}>
                  SmartSpeaker
                </Nav.Link>
                <Nav.Link onClick={() => cb("category", "thermostat")}>
                  SmartThermostat
                </Nav.Link>
              </Nav>
              <Form.Control
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                value={searchValue}
                onChange={handleSearchChange}
              />
              <Button onClick={handleSearchSubmit} variant="outline-success">
                Search
              </Button>
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <Row className="mt-4">
          <Col lg="3">
            <ListGroup className="mb-3">
              <ListGroup.Item active disabled>
                SmartDoorbell
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Eufy")}>
                Eufy Security
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Google")}>
                Google
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Ring")}>
                Ring
              </ListGroup.Item>
            </ListGroup>
            <ListGroup>
              <ListGroup.Item active disabled>
                SmartDoorlock
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Eufy")}>
                Eufy
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Yale")}>
                Yale
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Lockly")}>
                Lockly
              </ListGroup.Item>
            </ListGroup>
            <ListGroup className="mb-3">
              <ListGroup.Item active disabled>
                SmartSpeaker
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Amazon")}>
                Amazon
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Bose")}>
                Bose
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Apple")}>
                Apple
              </ListGroup.Item>
            </ListGroup>
            <ListGroup>
              <ListGroup.Item active disabled>
                SmartLighting
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Govee")}>
                Govee
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Ring")}>
                Ring
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Philips")}>
                Philips
              </ListGroup.Item>
            </ListGroup>
            <ListGroup className="mb-3">
              <ListGroup.Item active disabled>
                SmartThermostat
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "ecobee")}>
                ecobee
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Honeywell")}>
                Honeywell
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Emerson")}>
                Emerson
              </ListGroup.Item>
              <ListGroup.Item onClick={() => cb("maker", "Google")}>
                Google
              </ListGroup.Item>
            </ListGroup>
          </Col>
          <Col>
            <div style={{ minHeight: "calc(100vh - 130px)" }}>{children}</div>
          </Col>
        </Row>
      </Container>
      <Footer />
    </>
  );
}

export default Layout;
