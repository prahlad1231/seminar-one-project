import FormHeading from "./shared/FormHeading";

import "../styles/addseminar.css";
import { Paper } from "@mui/material";

const AddSeminar = () => {
  return (
    <div className="seminar-container">
      <FormHeading title="Add New Seminar" />
      <div className="seminar-form">
        <Paper elevation={2} square={false} sx={{ padding: "1rem" }}>
          Paper
        </Paper>
      </div>
    </div>
  );
};

export default AddSeminar;
