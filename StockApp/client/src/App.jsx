import "./App.css";
import Register from "./pages/auth/Register";
import { Route, Router, Routes } from "react-router-dom";
import OAuthCallback from "./pages/OAuthCallback";

function App() {
  return <>
  <Router>
    <Routes>
      <Route path="/" element={<Register />} />
      <Route path="/oauth-callback" element={<OAuthCallback />}/>
    </Routes>
  </Router>
  </>;
}

export default App;
