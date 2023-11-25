import { FormControl, FormLabel, Paper, TextField } from "@mui/material";
import "../../styles/mini/popup-form.css";

const TopicForm = ({ name }) => {
  return (
    <div className="mini-form">
      <Paper elevation={0} className="form-paper">
        <FormControl>
          <div className="miniform">
            <div className="form-label">
              <FormLabel>Topic Name</FormLabel>
            </div>
            <div className="form-input">
              <TextField id="topicName" value={name} required />
            </div>
          </div>
        </FormControl>
      </Paper>
    </div>
  );
};

export default TopicForm;
