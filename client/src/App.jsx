import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { LoginPage } from "./pages/Loginpage.jsx";
import { OAuthCallback } from "./pages/OAuthCallback.jsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        
        <Route path="/login" element={<LoginPage />} />
        <Route path="/oauth2/callback" element={<OAuthCallback />} />
        {/* Add a default dashboard route here later */}
        <Route path="/dashboard" element={<div>Welcome to Dashboard!</div>} />
      </Routes>
    </Router>
  );
}

export default App;