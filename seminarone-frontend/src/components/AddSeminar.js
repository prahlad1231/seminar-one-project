import FormHeading from "./shared/FormHeading";

import "../styles/addseminar.css";
import {
  Button,
  FormControl,
  FormLabel,
  Paper,
  TextField,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { useEffect, useState } from "react";

import LocationForm from "../components/mini/LocationForm";
import AutoCompleteTest from "./mini/AutocompleteTest";

const AddSeminar = () => {
  const [topicList, setTopicList] = useState([{ name: "" }]);
  const [venueNameList, setVenueNameList] = useState([]);
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    setTopicList([{ name: "Topic 1" }, { name: "Topic 2" }]);
    setVenueNameList([{ venueName: "Venue 1" }, { venueName: "Venue 2" }]);
  }, []);

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <div className="seminar-container">
        <FormHeading title="Add New Seminar" />
        <div className="seminar-form">
          <Paper
            elevation={2}
            square={false}
            sx={{ padding: "1rem" }}
            className="seminar-paper"
          >
            <div className="form-container">
              <Paper elevation={3} className="form-paper">
                <h3
                  style={{
                    width: "100%",
                    textAlign: "center",
                    marginBottom: "1rem",
                  }}
                >
                  Add new Seminar
                </h3>
                <FormControl>
                  <div className="miniform">
                    <div className="form-label">
                      <FormLabel>Title</FormLabel>
                      <FormLabel>Start Date</FormLabel>
                      <FormLabel>End Date</FormLabel>
                      <FormLabel>Price</FormLabel>
                      {/* todo: add autocomplete feature */}
                      <FormLabel>Topic</FormLabel>
                      <FormLabel>Location</FormLabel>
                    </div>
                    <div className="form-input">
                      <TextField id="title" required />
                      <DatePicker id="startDate" required />
                      <DatePicker id="endDate" required />
                      <TextField
                        id="price"
                        type="number"
                        inputProps={{
                          maxLength: 13,
                          step: "0.1",
                        }}
                        required
                      />
                      <TextField id="topic" />
                      <TextField id="location" />
                    </div>
                  </div>
                </FormControl>
                <Button
                  variant="contained"
                  sx={{ width: "100%", marginTop: "2rem" }}
                >
                  Add Venue
                </Button>
              </Paper>
            </div>
            <AutoCompleteTest />
          </Paper>
        </div>
      </div>
    </LocalizationProvider>
  );
};

export default AddSeminar;
