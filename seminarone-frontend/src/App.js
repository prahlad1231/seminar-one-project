import { Route, Routes } from "react-router-dom";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Settings from "./components/Settings";
import SeminarList from "./components/SeminarList";
import UserList from "./components/UserList";
import Sidebar from "./components/Sidebar";
import AddSeminar from "./components/AddSeminar";
import TopicList from "./components/TopicList";
import LocationList from "./components/LocationList";

function App() {
  return (
    <div className="app">
      <Sidebar>
        <Routes>
          <Route path="/" element=<Dashboard /> />
          <Route path="/dashboard" element=<Dashboard /> />
          <Route path="/settings" element=<Settings /> />
          <Route path="/seminarlist" element=<SeminarList /> />
          <Route path="/userlist" element=<UserList /> />
          <Route path="/addSeminar" element=<AddSeminar /> />
          <Route path="/topicList" element=<TopicList /> />
          <Route path="/locationList" element=<LocationList /> />
        </Routes>
      </Sidebar>
    </div>
  );
}

export default App;
