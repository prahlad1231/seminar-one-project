import {
  Dashboard,
  LibraryAdd,
  ListAlt,
  LocationOn,
  Logout,
  Menu,
  RecentActors,
  Settings,
  Topic,
} from "@mui/icons-material";
import { NavLink, useNavigate } from "react-router-dom";

import "../styles/sidebar.css";
import { useState } from "react";
import { useAuth } from "./context/AuthContext";

function Sidebar({ children }) {
  const { isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();

  const menuItems = [
    {
      path: "/",
      name: "Dashboard",
      icon: <Dashboard />,
    },
    {
      path: "/addSeminar",
      name: "Add Seminar",
      icon: <LibraryAdd />,
    },
    {
      path: "/topicList",
      name: "Topic List",
      icon: <Topic />,
    },
    {
      path: "/locationList",
      name: "Location List",
      icon: <LocationOn />,
    },
    {
      path: "/seminarlist",
      name: "Seminar List",
      icon: <ListAlt />,
    },
    {
      path: "/userlist",
      name: "User List",
      icon: <RecentActors />,
    },
    {
      path: "/settings",
      name: "Settings",
      icon: <Settings />,
    },
  ];

  const [isOpen, setIsOpen] = useState(true);
  const toggle = () => {
    setIsOpen(!isOpen);
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return isAuthenticated ? (
    <div className="container">
      <div style={{ width: isOpen ? "300px" : "60px" }} className="sidebar">
        <div className="top_section">
          <h1 style={{ display: isOpen ? "block" : "none" }} className="logo">
            Seminar One
          </h1>
          <div className="hamburger_menu">
            <Menu style={{ fontSize: "2rem" }} onClick={toggle} />
          </div>
        </div>

        {menuItems.map((item, index) => (
          <NavLink to={item.path} key={index} className="link">
            <div className="icon">{item.icon}</div>
            <div
              style={{ display: isOpen ? "block" : "none" }}
              className="link_text"
            >
              {item.name}
            </div>
          </NavLink>
        ))}

        <div className="logout_section">
          <button className="icon" onClick={handleLogout}>
            <Logout /> Logout
          </button>
        </div>
      </div>
      <main>{children}</main>
    </div>
  ) : (
    navigate("/login")
  );
}

export default Sidebar;
