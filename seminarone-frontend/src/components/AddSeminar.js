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
import { useEffect, useRef, useState } from "react";

import LocationForm from "../components/mini/LocationForm";
import TopicForm from "../components/mini/TopicForm";
import {
  LocationService,
  SeminarService,
  TopicService,
} from "../services/SeminarService";

const filter = createFilterOptions();

const AddSeminar = () => {
  const topicService = new TopicService();
  const locationService = new LocationService();
  const seminarService = new SeminarService();

  const [topicValue, setTopicValue] = useState("");
  const [venueValue, setVenueValue] = useState("");
  const [topicList, setTopicList] = useState([{ id: "", name: "" }]);
  const [venueList, setVenueList] = useState([
    {
      id: "",
      venueName: "",
      streetName: "",
      streetNumber: "",
      state: "",
      website: "",
    },
  ]);
  const [open, toggleOpen] = useState(false);
  const [dialogType, setDialogType] = useState("");

  const [topicDialogValue, setTopicDialogValue] = useState({
    name: "",
  });

  const [venueDialogValue, setVenueDialogValue] = useState({
    id: 0,
    venueName: "",
    streetNumber: 0,
    streetName: "",
    state: "",
    website: "",
  });

  const titleRef = useRef();
  const startDateRef = useRef();
  const endDateRef = useRef();
  const priceRef = useRef();
  const topicRef = useRef();
  const venueRef = useRef();

  useEffect(() => {
    topicService
      .getAllTopics()
      .then((result) => {
        if (result.data) {
          console.log(result.data);
          setTopicList(result.data.object);
        }
      })
      .catch((err) => {
        console.log(err);
        if (err.response.data) {
          alert(err.response.data.message);
        }
        alert("Error loading topics!");
      });

    // setTopicList([{ name: "Topic 1" }, { name: "Topic 2" }]);

    locationService
      .getAllVenues()
      .then((result) => {
        if (result.data) {
          console.log(result.data);
          setVenueList(result.data.object);
        }
      })
      .catch((err) => {
        console.log(err);
        if (err.response.data) {
          alert(err.response.data.message);
        }
        alert("Error loading venues!");
      });

    // setVenueList([
    //   {
    //     id: 1,
    //     venueName: "Venue 1",
    //     streetNumber: 7,
    //     streetName: "Sydney Ave",
    //     state: "ACT",
    //     website: "",
    //   },
    //   {
    //     id: 2,
    //     venueName: "Venue 2",
    //     streetNumber: 8,
    //     streetName: "Melbourne Ave",
    //     state: "ACT",
    //     website: "",
    //   },
    // ]);
  }, []);

  const handleClose = () => {
    if (dialogType === "topic") {
      setTopicDialogValue({
        name: "",
      });
    } else if (dialogType === "venue") {
      setVenueDialogValue({
        venueName: "",
        streetNumber: 0,
        streetName: "",
        state: "",
        website: "",
      });
    }
    toggleOpen(false);
  };

  // const handleAdd = (data) => {
  //   if (dialogType === "topic") {
  //     console.log("Received: " + data);
  //   } else if (dialogType === "venue") {
  //     alert("Venue adding....");
  //   }
  // };

  const clearForm = () => {
    titleRef.current.value = "";
    startDateRef.current.value = "";
    endDateRef.current.value = "";
    priceRef.current.value = "";
    setTopicValue("");
    setVenueValue("");
    topicRef.current.value = "";
    venueRef.current.value = "";
  };

  const addTopic = (topic) => {
    setTopicList(...topicList, topic);
  };

  const addVenue = (venue) => {
    setVenueList(...venueList, venue);
  };

  const addSeminar = () => {
    const title = titleRef.current.value;
    var startDate = startDateRef.current.value;
    startDate = new Date(startDate).toLocaleDateString("fr-CA");
    var endDate = endDateRef.current.value;
    endDate = new Date(endDate).toLocaleDateString("fr-CA");
    const price = priceRef.current.value;

    if (
      title === "" ||
      startDate === "" ||
      endDate === "" ||
      price === "" ||
      topicValue === "" ||
      venueValue === ""
    ) {
      alert("Please fill all the details!");
      return;
    }

    console.log(
      `title: ${title}, start date: ${startDate}, end date: ${endDate}, price: ${price}, topic: ${JSON.stringify(
        topicValue
      )}, venue: ${JSON.stringify(venueValue)}, topicId: ${
        topicValue.id
      }, venueId: ${venueValue.id}`
    );

    const seminarDetails = {
      title: title,
      startDate: startDate,
      endDate: endDate,
      price: price,
      topicEntityId: topicValue.id,
      locationEntityId: venueValue.id,
    };
    console.log(`Seminar Details: ${JSON.stringify(seminarDetails)}`);

    seminarService
      .save(seminarDetails)
      .then((result) => {
        if (result && result.data) {
          alert(result.data.message);
        }
        clearForm();
      })
      .catch((err) => {
        if (err.response) {
          alert(err.response.data.message);
        }
        alert("ERROR!");
      });
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
                      <TextField id="title" inputRef={titleRef} required />
                      <DatePicker
                        id="startDate"
                        inputRef={startDateRef}
                        required
                      />
                      <DatePicker id="endDate" inputRef={endDateRef} required />
                      <TextField
                        id="price"
                        inputRef={priceRef}
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
                              // setTopicValue(newValue);
                              setTopicDialogValue({
                                name: newValue,
                              });
                            });
                          } else if (newValue && newValue.inputValue) {
                            toggleOpen(true);
                            setTopicDialogValue({ name: newValue.inputValue });
                            // setTopicValue(newValue);
                          } else {
                            setTopicValue(newValue);
                            console.log(newValue);
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
                          <TextField
                            {...params}
                            label="Enter Topic Name"
                            inputRef={topicRef}
                            required
                          />
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
                            console.log(newValue);
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
                          <TextField
                            {...params}
                            label="Enter Venue"
                            inputRef={venueRef}
                            required
                          />
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
                            <TopicForm
                              name={topicDialogValue.name}
                              onAdd={addTopic}
                              cancel={handleClose}
                            />
                          ) : (
                            <LocationForm
                              venueName={venueDialogValue.venueName}
                              onAdd={addVenue}
                              cancel={handleClose}
                            />
                          )}
                        </DialogContent>
                        {/* <DialogActions>
                          <Button onClick={handleClose}>Cancel</Button>
                          <Button type="submit" onClick={handleAdd}>
                            Add
                          </Button>
                        </DialogActions> */}
                      </Dialog>
                    </div>
                  </div>
                </FormControl>
                <Button
                  type="submit"
                  variant="contained"
                  sx={{ width: "100%", marginTop: "2rem" }}
                  onClick={addSeminar}
                >
                  Add Seminar
                </Button>
                <Button
                  variant="contained"
                  sx={{ width: "100%", marginTop: "2rem" }}
                  onClick={clearForm}
                >
                  Cancel
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
