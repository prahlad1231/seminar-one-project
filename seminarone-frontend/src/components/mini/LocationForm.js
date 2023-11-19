import {
  Button,
  FormControl,
  FormLabel,
  Paper,
  TextField,
} from "@mui/material";
import "../../styles/mini/popup-form.css";

const LocationForm = () => {
  return (
    <div className="form-container">
      <Paper elevation={3} className="form-paper">
        <h3
          style={{ width: "100%", textAlign: "center", marginBottom: "1rem" }}
        >
          Add new Venue
        </h3>
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
              <TextField id="venueName" required />
              <TextField id="streetNumber" required type="number" />
              <TextField id="streetName" required />
              <TextField id="state" required />
              <TextField id="website" />
            </div>
          </div>
        </FormControl>
        <Button variant="contained" sx={{ width: "100%", marginTop: "2rem" }}>
          Add Venue
        </Button>
      </Paper>
    </div>
  );
};

export default LocationForm;
