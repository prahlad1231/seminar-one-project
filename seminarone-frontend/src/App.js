import { Route, Routes } from "react-router-dom";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Settings from "./components/Settings";
import SeminarList from "./components/SeminarList";
import UserList from "./components/UserList";
import Sidebar from "./components/Sidebar";

function App() {
  return (
    <div className="App">
      <Sidebar>
        <Routes>
          <Route path="/" element=<Dashboard /> />
          <Route path="/dashboard" element=<Dashboard /> />
          <Route path="/settings" element=<Settings /> />
          <Route path="/seminarlist" element=<SeminarList /> />
          <Route path="/userlist" element=<UserList /> />
        </Routes>
      </Sidebar>
    </div>
  );
}

export default App;
