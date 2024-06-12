import { Box, Button, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { UserService } from "../services/UserService";

const ChangePassword = () => {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const userService = new UserService();

  const clear = () => {
    setOldPassword("");
    setNewPassword("");
    setConfirmPassword("");
  };

  const handleChangePassword = () => {
    if (
      oldPassword.length === 0 ||
      newPassword.length === 0 ||
      confirmPassword.length === 0
    )
      alert("Please enter all required fields!");
    else if (newPassword === confirmPassword) {
      if (oldPassword === newPassword) {
        alert("New password should be different from old password!");
      } else {
        const changePasswordDto = {
          oldPassword: oldPassword,
          newPassword: confirmPassword,
        };
        userService
          .changePassword(changePasswordDto)
          .then((response) => {
            if (response && response.data) {
              alert(response.data.message);
            }
            console.log(response);
          })
          .catch((err) => {
            console.log(err);
            alert("Error changing password: " + err);
          });
        clear();
      }
    } else {
      alert("New Passwords do not match!");
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        marginTop: 4,
      }}
    >
      <Typography variant="h5" gutterBottom>
        Change Password
      </Typography>
      <TextField
        label="Old Password"
        type="password"
        value={oldPassword}
        onChange={(e) => setOldPassword(e.target.value)}
        variant="outlined"
        margin="normal"
        fullWidth
        required
      />
      <TextField
        label="New Password"
        type="password"
        value={newPassword}
        onChange={(e) => setNewPassword(e.target.value)}
        variant="outlined"
        margin="normal"
        fullWidth
        required
      />
      <TextField
        label="Confirm New Password"
        type="password"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
        variant="outlined"
        margin="normal"
        fullWidth
        required
      />
      {error && (
        <Typography color="error" variant="body2">
          {error}
        </Typography>
      )}
      {success && (
        <Typography color="primary" variant="body2">
          {success}
        </Typography>
      )}
      <Button
        variant="contained"
        color="primary"
        onClick={handleChangePassword}
        sx={{ marginTop: 2 }}
      >
        Change Password
      </Button>
    </Box>
  );
};

export default ChangePassword;
