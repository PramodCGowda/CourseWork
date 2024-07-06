import { useEffect, useState } from "react";
import { Button, Table, Image, Form, Row, Col } from "react-bootstrap";

import Layout from "../components/layout";
import { useNavigate } from "react-router-dom";
import { deleteItemFromCart } from "../controllers/purchase";

function CartScreen() {
  const navigate = useNavigate();
  const [sum, setSum] = useState(0);
  const [cart, setCart] = useState([]);
  const [cartUpdated, setCartUpdated] = useState(0);

  const deleteItem = async (id) => {
    let status = await deleteItemFromCart(id);
    if (status) {
      setCartUpdated(cartUpdated + 1);
    } else {
      alert("Unable to delete item at the moment!");
    }
  };

  useEffect(() => {
    if (cart.length) {
      const initialSum = 0;
      const sumWithInitial = cart.reduce(
        (accumulator, currentValue) => accumulator + Number(currentValue.price),
        initialSum
      );
      setSum(sumWithInitial);
    }
  }, [cart]);

  useEffect(() => {
    let details = localStorage.getItem("userDetails");
    let user = { name: "", id: "" };
    if (!details) {
      window.location.href = "/login";
    }
  }, []);
  const [inputValue, setInputValue] = useState("");

  const handleApplyClick = () => {
    // Handle the apply button click here
    console.log("Input Value:", inputValue);
  };

  useEffect(() => {
    let cart = localStorage.getItem("cart");
    let userId = localStorage.getItem("userId");
    if (cart) {
      let cartObj = JSON.parse(cart);
      if (cartObj.hasOwnProperty(userId)) {
        setCart(cartObj[userId]);
      }
    }
  }, [cartUpdated]);

  if (!cart.length) {
    return (
      <Layout>
        <div
          style={{
            height: "50vh",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Image src="/images/empty.png" height={200} width={360} />
          <h4 className="mt-5">Cart Empty !</h4>
        </div>
      </Layout>
    );
  }

  return (
    <Layout>
      <div className="d-flex justify-content-between">
        <h4>Cart</h4>
        <h6>Total Products: {cart.length}</h6>
      </div>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Item</th>
            <th>Price</th>
            <td>Discount</td>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {cart.map((item, index) => {
            return (
              <tr key={item.name + index}>
                <td>{index + 1}</td>
                <td>{item.name}</td>
                <td>{item.price}</td>
                <td>{item.discount}%</td>
                <td>
                  <Button
                    onClick={() => deleteItem(item.id)}
                    variant="danger"
                    size="sm"
                  >
                    Remove
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
      <div>
        <label htmlFor="myInput">Warranty:</label>
        <input
          type="text"
          id="myInput"
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
        />
        <button onClick={handleApplyClick}>Apply</button>
      </div>
      <div className="mt-4 d-flex justify-content-between align-items-center">
        <h3 style={{ fontWeight: "700" }} className="text-success text-bold">
          $ {sum.toFixed(2)}
        </h3>
        <Button onClick={() => navigate("/orders")} variant="warning">
          Checkout
        </Button>
      </div>
    </Layout>
  );
}

export default CartScreen;
