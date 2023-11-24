import FormHeading from "./shared/FormHeading";

import "../styles/addseminar.css";
import {
  Autocomplete,
  Button,
  Dialog,
  DialogContent,
  DialogContentText,
  DialogTitle,
  FormControl,
  FormLabel,
  Paper,
  TextField,
  createFilterOptions,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { useEffect, useState } from "react";

import LocationForm from "../components/mini/LocationForm";
import TopicForm from "../components/mini/TopicForm";
import AutoCompleteTest from "./mini/AutocompleteTest";

const filter = createFilterOptions();

const AddSeminar = () => {
  const [topicValue, setTopicValue] = useState("");
  const [venueValue, setVenueValue] = useState("");
  const [topicList, setTopicList] = useState([{ name: "" }]);
  const [venueList, setVenueList] = useState([]);
  const [open, toggleOpen] = useState(false);
  const [dialogType, setDialogType] = useState("");

  const [topicDialogValue, setTopicDialogValue] = useState({
    name: "",
  });

  const [venueDialogValue, setVenueDialogValue] = useState({
    venueName: "",
    streetNumber: 0,
    streetName: "",
    state: "",
    website: "",
  });

  useEffect(() => {
    setTopicList([{ name: "Topic 1" }, { name: "Topic 2" }]);
    setVenueList([
      {
        venueName: "Venue 1",
        streetNumber: 7,
        streetName: "Sydney Ave",
        state: "ACT",
        website: "",
      },
      {
        venueName: "Venue 2",
        streetNumber: 8,
        streetName: "Melbourne Ave",
        state: "ACT",
        website: "",
      },
    ]);
  }, []);

  const handleClose = () => {
    if (dialogType === "topic") {
      setTopicDialogValue({
        name: "",
      });
      toggleOpen(false);
    } else if (dialogType === "venue") {
      setVenueDialogValue({
        venueName: "",
        streetNumber: 0,
        streetName: "",
        state: "",
        website: "",
      });
    }
  };

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
                      <FormLabel>Topic</FormLabel>
                      <FormLabel>Venue</FormLabel>
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
                      <Autocomplete
                        value={topicValue}
                        onChange={(event, newValue) => {
                          setDialogType("topic");
                          // if the user has input a new value, then give some time for the user to type instead
                          // of directly showing the dialog
                          if (typeof newValue === "string") {
                            setTimeout(() => {
                              toggleOpen(true);
                              setTopicDialogValue({
                                name: newValue,
                              });
                            });
                          } else if (newValue && newValue.inputValue) {
                            toggleOpen(true);
                            setTopicDialogValue({ name: newValue });
                          } else {
                            setTopicValue(newValue);
                          }
                        }}
                        filterOptions={(options, params) => {
                          const filtered = filter(options, params);
                          if (params.inputValue !== "") {
                            filtered.push({
                              inputValue: params.inputValue,
                              name: `Add "${params.inputValue}"`,
                            });
                          }
                          return filtered;
                        }}
                        id="topic"
                        options={topicList}
                        getOptionLabel={(option) => {
                          // value selected from given options
                          if (typeof option === "string") {
                            return option;
                          }
                          if (option.inputValue) {
                            return option.inputValue;
                          }
                          return option.name;
                        }}
                        selectOnFocus
                        clearOnBlur
                        handleHomeEndKeys
                        renderOption={(props, option) => (
                          <li {...props}>{option.name}</li>
                        )}
                        sx={{ width: 300 }}
                        freeSolo
                        renderInput={(params) => (
                          <TextField {...params} label="Enter Topic Name" />
                        )}
                      />

                      <Autocomplete
                        value={venueValue}
                        onChange={(event, newValue) => {
                          setDialogType("venue");
                          if (typeof newValue === "string") {
                            setTimeout(() => {
                              toggleOpen(true);
                              setVenueDialogValue({
                                venueName: newValue,
                              });
                            });
                          } else if (newValue && newValue.inputValue) {
                            toggleOpen(true);
                            setVenueDialogValue({
                              venueName: newValue.inputValue,
                            });
                          } else {
                            setVenueValue(newValue);
                          }
                        }}
                        filterOptions={(options, params) => {
                          const filtered = filter(options, params);
                          if (params.inputValue !== "") {
                            filtered.push({
                              inputValue: params.inputValue,
                              venueName: `Add "${params.inputValue}"`,
                            });
                          }

                          return filtered;
                        }}
                        id="venue"
                        options={venueList}
                        getOptionLabel={(option) => {
                          if (typeof option === "string") {
                            return option;
                          }
                          if (option.inputValue) {
                            return option.inputValue;
                          }
                          return option.venueName;
                        }}
                        selectOnFocus
                        clearOnBlur
                        handleHomeEndKeys
                        renderOption={(props, option) => (
                          <li {...props}>{option.venueName}</li>
                        )}
                        sx={{ width: 300 }}
                        freeSolo
                        renderInput={(params) => (
                          <TextField {...params} label="Enter Venue" />
                        )}
                      />

                      <Dialog open={open} onClose={handleClose}>
                        <DialogTitle>
                          {dialogType === "topic"
                            ? "Add new Topic"
                            : "Add new Venue"}
                        </DialogTitle>
                        <DialogContent>
                          <DialogContentText>
                            Oops! Missed something?
                          </DialogContentText>
                          {dialogType === "topic" ? (
                            <TopicForm />
                          ) : (
                            <LocationForm />
                          )}
                        </DialogContent>
                      </Dialog>
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
          </Paper>
        </div>
      </div>
    </LocalizationProvider>
  );
};

export default AddSeminar;
