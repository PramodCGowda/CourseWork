export const saveItemToCart = (product) => {
  let userId = localStorage.getItem("userId");
  let cart = localStorage.getItem("cart");
  if (!userId) window.location.href = "/login";

  let data = {
    userId: userId,
    ...product,
  };

  if (cart) {
    let cartObj = JSON.parse(cart);
    let tempObj = { ...cartObj };
    if (tempObj.hasOwnProperty(userId)) {
      tempObj[userId] = [...tempObj[userId], data];
    } else {
      tempObj[userId] = [data];
    }
    localStorage.setItem("cart", JSON.stringify(tempObj));
  } else {
    localStorage.setItem("cart", JSON.stringify({ [userId]: [data] }));
  }
};

export const deleteItemFromCart = (productId) => {
  let userId = localStorage.getItem("userId");
  if (!userId) window.location.href = "/login";
  let cart = localStorage.getItem("cart");

  if (cart) {
    try {
      let cartObj = JSON.parse(cart);
      let tempObj = { ...cartObj };
      console.log("Temp", tempObj);
      let dump = [];
      tempObj[userId].forEach((obj) => {
        if (obj.id != productId) dump.push(obj);
      });

      localStorage.setItem(
        "cart",
        JSON.stringify({ ...tempObj, [userId]: dump })
      );

      return true;
    } catch (err) {
      return false;
    }
  }
};

export const generateNewOrder = (data) => {
  try {
    let userId = localStorage.getItem("userId");
    let orders = localStorage.getItem("orders");
    let cart = localStorage.getItem("cart");
    if (!userId) window.location.href = "/login";

    let dataObj = { ...data, userId };
    var newOrderId = 1;
    if (orders) {
      let ordersObj = JSON.parse(orders);
      if (Object.keys(ordersObj).length) {
        newOrderId = Object.keys(ordersObj).length + 1;
      }
      localStorage.setItem(
        "orders",
        JSON.stringify({ ...ordersObj, [newOrderId]: dataObj })
      );
    } else {
      localStorage.setItem("orders", JSON.stringify({ [newOrderId]: dataObj }));
    }

    let cartObj = JSON.parse(cart);
    let tempCart = { ...cartObj };

    delete tempCart[userId];
    localStorage.setItem("cart", JSON.stringify(tempCart));
    return { status: true, id: newOrderId };
  } catch (err) {
    return { status: false, id: null };
  }
};

export const getOrderById = (id) => {
  let userId = localStorage.getItem("userId");
  let orders = localStorage.getItem("orders");
  if (!userId) window.location.href = "/login";

  if (orders) {
    let ordersObj = JSON.parse(orders);
    if (ordersObj && ordersObj[id]) {
      return ordersObj[id];
    } else {
      alert("No order found for id: " + id);
      return {};
    }
  }
};

export const deleteItemFromOrders = (searchId) => {
  let userId = localStorage.getItem("userId");
  if (!userId) window.location.href = "/login";
  let orders = localStorage.getItem("orders");

  if (orders) {
    try {
      let ordersObj = JSON.parse(orders);
      let temp = { ...ordersObj };
      delete temp[searchId];
      localStorage.setItem("orders", JSON.stringify(temp));
      return true;
    } catch (err) {
      return false;
    }
  }
};
