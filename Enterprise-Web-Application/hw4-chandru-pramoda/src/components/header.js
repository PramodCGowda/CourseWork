import Container from "react-bootstrap/Container";
import { Navbar, Nav, NavDropdown, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { userLogout } from "../controllers/auth";

function Header() {
  let details = localStorage.getItem("userDetails");
  let user = { name: "John Doe", id: "" };
  if (details) {
    user = JSON.parse(details);
  }
  return (
    <Navbar collapseOnSelect expand="lg" bg="primary" data-bs-theme="dark">
      <Container>
        <Navbar.Brand as={Link} to="/">
          Smart Homes
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto"></Nav>
          <Nav>
            {user.id && (
              <>
                <Nav.Link
                  style={{
                    fontWeight: "700",
                    color: "#fff",
                    fontSize: "18px",
                  }}
                  disabled
                >
                  Welcome, {user.name}
                </Nav.Link>
                {user.type === "customer" && (
                  <>
                    <Nav.Link as={Link} to="/viewOrder">
                      ViewOrder
                    </Nav.Link>
                    <Nav.Link as={Link} to="/cart">
                      Cart
                    </Nav.Link>
                    <Button onClick={userLogout} variant="dark" size="sm">
                      Logout
                    </Button>
                  </>
                )}
                {user.type === "retailer" && (
                  <>
                    <NavDropdown title="SM Menu" id="basic-nav-dropdown">
                      <NavDropdown.Item as={Link} to="/addProduct">
                        Add Product
                      </NavDropdown.Item>
                      <NavDropdown.Item as={Link} to="/deleteProduct">
                        Delete Product
                      </NavDropdown.Item>
                      <NavDropdown.Item as={Link} to="/updateProduct">
                        Update Product
                      </NavDropdown.Item>
                    </NavDropdown>
                    <Button onClick={userLogout} variant="dark" size="sm">
                      Logout
                    </Button>
                  </>
                )}
                {user.type === "salesman" && (
                  <>
                    <Nav.Link as={Link} to="/signup">
                      CreateAccount
                    </Nav.Link>
                    <Nav.Link as={Link} to="/viewOrder">
                      ViewOrder
                    </Nav.Link>
                    <Button onClick={userLogout} variant="dark" size="sm">
                      Logout
                    </Button>
                  </>
                )}
              </>
            )}
            {!user.id && (
              <>
                <Nav.Link as={Link} to="/login" style={{ fontWeight: "bold" }}>
                  Login
                </Nav.Link>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;
