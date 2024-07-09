import "../styles/dashboard.css";
import DemoDataGrid from "./demo/DemoDataGrid";
import CustomDataGrid from "./shared/CustomDataGrid";

const Dashboard = () => {
  return (
    <div>
      <h1>Dashboard</h1>
      <CustomDataGrid />
      <DemoDataGrid />
    </div>
  );
};

export default Dashboard;
