import "./App.css";
import Register from "./pages/auth/Register";
import { Route, BrowserRouter, Routes } from "react-router-dom";
import OAuthCallback from "./pages/OAuthCallback";
import Login from "./pages/auth/Login";

function App() {
  return <>
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Register />} />
      <Route path="/oauth-callback" element={<OAuthCallback />}/>
      <Route path="/login" element={<Login />} />
    </Routes>
  </BrowserRouter>
  </>;
}

export default App;
