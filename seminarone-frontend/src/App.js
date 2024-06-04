import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Settings from "./components/Settings";
import SeminarList from "./components/SeminarList";
import UserList from "./components/UserList";
import Sidebar from "./components/Sidebar";
import AddSeminar from "./components/AddSeminar";
import TopicList from "./components/TopicList";
import LocationList from "./components/LocationList";
import LoginPage from "./components/LoginPage";
import { AuthProvider, useAuth } from "./components/context/AuthContext";
import { useEffect } from "react";

const RequireAuth = ({ children }) => {
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
    }
  }, [isAuthenticated, navigate]);

  return isAuthenticated ? children : null;
};

function App() {
  const navigate = useNavigate();

  // useEffect(() => {
  //   const user = localStorage.getItem("user");
  //   if (user) {
  //     navigate("/dashboard");
  //   } else {
  //     navigate("/login");
  //   }
  // }, [navigate]);

  return (
    <AuthProvider>
      <div className="app">
        <Routes>
          <Route path="/login" element=<LoginPage /> />
          <Route
            path="*"
            element={
              <RequireAuth>
                <Sidebar>
                  <Routes>
                    <Route path="/" element=<Navigate to="/dashboard" /> />
                    <Route path="/dashboard" element=<Dashboard /> />
                    <Route path="/settings" element=<Settings /> />
                    <Route path="/seminarlist" element=<SeminarList /> />
                    <Route path="/userlist" element=<UserList /> />
                    <Route path="/addSeminar" element=<AddSeminar /> />
                    <Route path="/topicList" element=<TopicList /> />
                    <Route path="/locationList" element=<LocationList /> />
                  </Routes>
                </Sidebar>
              </RequireAuth>
            }
          />
        </Routes>
      </div>
    </AuthProvider>
  );
}

export default App;
