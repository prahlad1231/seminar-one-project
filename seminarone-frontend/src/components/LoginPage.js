import { useNavigate } from "react-router-dom";
import { useAuth } from "./context/AuthContext";
import { AuthService } from "../services/AuthService";
import { useState } from "react";

import "../styles/loginpage.css";
import {
  Button,
  Container,
  Grid,
  Paper,
  TextField,
  Typography,
} from "@mui/material";

const LoginPage = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const authService = new AuthService();

  const handleLogin = () => {
    const userDetails = { username: username, password: password };
    const response = authService.login(userDetails);
    response
      .then((response) => {
        console.log(response);
        // alert("Welcome " + response.username + "!");
        login(); // from auth context
        navigate("/dashboard");
      })
      .catch((err) => {
        if (err.response && err.response.status == "401") {
          alert("Incorrect Username / Password!");
        } else {
          console.log(err);
          alert("Error");
        }
      });
    console.log(response);
  };

  return (
    <Container component="main" maxWidth="xs">
      <Paper elevation={3} style={{ padding: "20px", marginTop: "50px" }}>
        <Typography component="h1" variant="h5" style={{ marginBottom: 15 }}>
          Login
        </Typography>
        <form noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Grid>
            <Grid item xs={12}>
              <Button
                type="button"
                fullWidth
                variant="contained"
                color="primary"
                onClick={handleLogin}
              >
                Log In
              </Button>
            </Grid>
          </Grid>
        </form>
      </Paper>
    </Container>
  );
};

export default LoginPage;
