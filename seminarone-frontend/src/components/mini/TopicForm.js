import {
  Button,
  FormControl,
  FormLabel,
  Paper,
  TextField,
} from "@mui/material";
import "../../styles/mini/popup-form.css";

const TopicForm = () => {
  return (
    <div className="mini-form">
      <Paper elevation={3} className="form-paper">
        <h3
          style={{ width: "100%", textAlign: "center", marginBottom: "1rem" }}
        >
          Add new Topic
        </h3>
        <FormControl>
          <div className="miniform">
            <div className="form-label">
              <FormLabel>Topic Name</FormLabel>
            </div>
            <div className="form-input">
              <TextField id="topicName" required />
            </div>
          </div>
        </FormControl>
        <Button variant="contained" sx={{ width: "100%", marginTop: "2rem" }}>
          Add Topic
        </Button>
      </Paper>
    </div>
  );
};

export default TopicForm;
