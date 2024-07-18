import { USERS } from "../data/user";

export const saveUser = (userData) => {
  let dataObj = {
    id: (USERS.length + 1).toString(),
    name: userData.userName,
    password: userData.password,
    user_type: userData.userType,
  };

  USERS.push(dataObj);
};

export const checkCredentials = (name, pass, type) => {
  const user = USERS.filter(
    (user) =>
      user.name === name && user.user_type === type && user.password === pass
  );
  if (user.length) {
    let data = {
      id: user[0].id,
      name: user[0].name,
      type: user[0].user_type,
    };
    return data;
  } else {
    return null;
  }
};

export const userLogout = () => {
  localStorage.removeItem("userDetails");
  localStorage.removeItem("userId");
  window.location.href = "/login";
};
