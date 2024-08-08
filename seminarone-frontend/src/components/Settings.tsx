import { Paper } from "@mui/material";
import ChangePassword from "./ChangePassword";

const Settings = () => {
  return (
    <div>
      <h2>Settings</h2>
      <div className="settings">
        <Paper elevation={3} className="settings-paper">
          <ChangePassword />
        </Paper>
      </div>
    </div>
  );
};

export default Settings;
