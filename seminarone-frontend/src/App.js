import "./App.css";
import Header from "./components/Header";
import MainContent from "./components/MainContent";
import Sidebar from "./components/Sidebar";

function App() {
  return (
    <div className="App">
      <Sidebar />
      <Header />

      <MainContent />
    </div>
  );
}

export default App;
