import React from "react";

import { router } from "./routers";
import { RouterProvider } from "react-router-dom";
import "./styles/index.css";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return <RouterProvider router={router} />;
}

export default App;
