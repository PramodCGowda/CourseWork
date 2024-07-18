import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";
import { Card, ButtonGroup } from "react-bootstrap";
import { saveItemToCart } from "../controllers/purchase";

const HomePageCard = ({ product }) => {
  const navigate = useNavigate();

  const buyProduct = () => {
    saveItemToCart(product);
    navigate("/cart");
  };

  return (
    <div className="product-list">
      <div key={product.id}>
        <Card style={{ width: "18rem" }}>
          <Card.Img
            style={{ height: "240px", objectFit: "contain", padding: "16px" }}
            variant="top"
            src={product.image}
          />
          <Card.Body>
            <Card.Title>{product.name}</Card.Title>
            <Card.Text>{product.description}</Card.Text>

            <ButtonGroup style={{ width: "100%" }} aria-label="Basic example">
              <Button
                style={{ width: "100%" }}
                onClick={() => navigate(`/product/${product.id}`)}
                variant="primary"
              >
                View
              </Button>
              <Button
                style={{ width: "100%" }}
                onClick={buyProduct}
                variant="secondary"
              >
                Buy
              </Button>
            </ButtonGroup>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
};

export default HomePageCard;
