import {
  Button,
  FormControl,
  FormLabel,
  Paper,
  TextField,
} from "@mui/material";
import "../../styles/mini/popup-form.css";
import { useRef } from "react";
import { LocationService } from "../../services/SeminarService";

const LocationForm = ({ venueName, cancel, onAdd }) => {
  const locationService = new LocationService();

  const venueNameRef = useRef("");
  const streetNumberRef = useRef("");
  const streetNameRef = useRef("");
  const stateRef = useRef("");
  const websiteRef = useRef("");

  const addVenue = () => {
    const venueName = venueNameRef.current.value;
    const streetNumber = streetNumberRef.current.value;
    const streetName = streetNameRef.current.value;
    const state = stateRef.current.value;
    const website = websiteRef.current.value;

    const location = {
      venueName: venueName,
      streetNumber: streetNumber,
      streetName: streetName,
      state: state,
      website: website,
    };

    console.log(location);

    locationService
      .save(location)
      .then((result) => {
        alert(result.data.message);
        onAdd(result.data.object);
      })
      .catch((err) => {
        alert("Error");
      });
  };

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
              <TextField
                id="venueName"
                value={venueName}
                inputRef={venueNameRef}
                required
              />
              <TextField
                id="streetNumber"
                inputRef={streetNumberRef}
                required
                type="number"
              />
              <TextField id="streetName" inputRef={streetNameRef} required />
              <TextField id="state" inputRef={stateRef} required />
              <TextField id="website" inputRef={websiteRef} />
            </div>
          </div>
          <div className="buttons">
            <Button
              sx={{ marginTop: "1rem" }}
              id="addBtn"
              type="submit"
              onClick={addVenue}
            >
              Add
            </Button>
            <Button sx={{ marginTop: "1rem" }} id="cancelBtn" onClick={cancel}>
              Cancel
            </Button>
          </div>
        </FormControl>
      </Paper>
    </div>
  );
};

export default LocationForm;
