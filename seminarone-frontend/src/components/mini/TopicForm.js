import {
  Button,
  FormControl,
  FormLabel,
  Paper,
  TextField,
} from "@mui/material";
import "../../styles/mini/popup-form.css";
import { useState } from "react";
import { TopicService } from "../../services/SeminarService";

const TopicForm = ({ name, cancel, onAdd }) => {
  const topicService = new TopicService();

  const [topicDetails, setTopicDetails] = useState({
    name: name,
  });

  const handleChange = (event) => {
    setTopicDetails({
      ...topicDetails,
      name: event.target.value,
    });
  };

  const addTopic = () => {
    console.log(topicDetails);
    topicService
      .save(topicDetails)
      .then((result) => {
        if (result.data) {
          console.log(result.data);
          alert(result.data.message);
          onAdd(result.data.object);
        }
      })
      .catch((err) => {
        console.log(err);
        alert("Error saving!");
      });
  };

  return (
    <div className="mini-form">
      <Paper elevation={0} className="form-paper">
        <FormControl>
          <div className="miniform">
            <div className="form-label">
              <FormLabel>Topic Name</FormLabel>
            </div>
            <div className="form-input">
              <TextField
                id="topicName"
                value={topicDetails.name}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="buttons">
            <Button
              sx={{ marginTop: "1rem" }}
              id="addBtn"
              type="submit"
              onClick={addTopic}
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

export default TopicForm;
