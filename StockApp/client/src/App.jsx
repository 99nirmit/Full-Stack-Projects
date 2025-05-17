import "./App.css";
import Register from "./pages/auth/Register";
import { Route, BrowserRouter, Routes } from "react-router-dom";
import OAuthCallback from "./pages/OAuthCallback";

function App() {
  return <>
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Register />} />
      <Route path="/oauth-callback" element={<OAuthCallback />}/>
    </Routes>
  </BrowserRouter>
  </>;
}

export default App;
