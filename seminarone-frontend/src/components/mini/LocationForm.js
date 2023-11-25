import { FormControl, FormLabel, Paper, TextField } from "@mui/material";
import "../../styles/mini/popup-form.css";

const LocationForm = ({ venueName }) => {
  return (
    <div className="form-container">
      <Paper elevation={0} className="form-paper">
        <FormControl>
          <div className="miniform">
            <div className="form-label">
              <FormLabel>Venue Name</FormLabel>
              <FormLabel>Street Number</FormLabel>
              <FormLabel>Street Name</FormLabel>
              <FormLabel>State</FormLabel>
              <FormLabel>Website</FormLabel>
            </div>
            <div className="form-input">
              <TextField id="venueName" value={venueName} required />
              <TextField id="streetNumber" required type="number" />
              <TextField id="streetName" required />
              <TextField id="state" required />
              <TextField id="website" />
            </div>
          </div>
        </FormControl>
      </Paper>
    </div>
  );
};

export default LocationForm;
