import {
  Dashboard,
  ListAlt,
  Menu,
  RecentActors,
  Settings,
} from "@mui/icons-material";
import { NavLink } from "react-router-dom";

import "../styles/sidebar.css";

function Sidebar({ children }) {
  const menuItems = [
    {
      path: "/",
      name: "Dashboard",
      icon: <Dashboard />,
    },
    {
      path: "/seminarlist",
      name: "SeminarList",
      icon: <ListAlt />,
    },
    {
      path: "/userlist",
      name: "UserList",
      icon: <RecentActors />,
    },
    {
      path: "/settings",
      name: "Settings",
      icon: <Settings />,
    },
  ];

  return (
    <div className="container">
      <div className="sidebar">
        <div className="top_section">
          <h1 className="logo">Seminar One</h1>
          <div className="hamburger_menu">
            <Menu style={{ fontSize: "2.5rem" }} />
          </div>
        </div>
        {menuItems.map((item, index) => (
          <NavLink
            to={item.path}
            key={index}
            className="link"
            activeClassName="active"
          >
            <div className="icon">{item.icon}</div>
            <div className="link_text">{item.name}</div>
          </NavLink>
        ))}
      </div>
      <main>{children}</main>
    </div>
  );
}

export default Sidebar;
