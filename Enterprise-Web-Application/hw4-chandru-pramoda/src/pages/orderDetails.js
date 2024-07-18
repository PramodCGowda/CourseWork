import { useState, useEffect } from "react";
import { deleteItemFromOrders, getOrderById } from "../controllers/purchase";
import Layout from "../components/layout";
import { Button, Form, InputGroup, Image, Table } from "react-bootstrap";

const OrderDetails = () => {
  const [searchId, setSearchId] = useState("");
  const [order, setOrder] = useState({});

  function fetchOrder() {
    if (searchId) {
      let temp = getOrderById(searchId);
      setOrder(temp);
    } else {
      alert("Enter Valid Order Id");
    }
  }

  const deleteItem = async () => {
    let status = await deleteItemFromOrders(searchId);
    if (status) {
      alert("Order Cancelled !");
      window.location.reload();
    } else {
      alert("Unable to delete item at the moment!");
    }
  };

  return (
    <Layout>
      <InputGroup className="mb-3">
        <Form.Control
          type="search"
          placeholder="Search"
          className="me-2"
          aria-label="Search"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
        />

        <Button onClick={fetchOrder} variant="outline-success">
          Search
        </Button>
      </InputGroup>

      {order && order.userId ? (
        <div>
          <div
            style={{
              backgroundColor: "#eee",
              borderRadius: "12px",
              padding: "24px",
              marginBottom: "40px",
            }}
          >
            <h5>Total Price: {order.price}</h5>
            <h5>User Id: {order.userId}</h5>
            <h5>Address: {order.deliveryDetails.address}</h5>
            <Button size="lg" variant="danger" onClick={deleteItem}>
              Cancel Order
            </Button>
          </div>
          <h4>Order Details: </h4>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>User Price</th>
              </tr>
            </thead>
            <tbody>
              {order.products.map((obj, index) => {
                return (
                  <tr key={obj.name + obj.id}>
                    <td>{index + 1}</td>
                    <td>{obj.name}</td>
                    <td>{obj.category}</td>
                    <td>{obj.price}</td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>
      ) : (
        <div
          style={{
            height: "50vh",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Image src="/images/search.svg" height={200} width={360} />
          <h4 className="mt-5">Enter Order Id to begin search !</h4>
        </div>
      )}
      <div className="mt-5"></div>
    </Layout>
  );
};

export default OrderDetails;
