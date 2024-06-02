import axios from "axios";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

class AuthService {
  login(loginDetails) {
    return axios({
      method: "post",
      data: loginDetails,
      url: API_URL + `/authenticate`,
    }).then((response) => {
      if (response.data.object.jwtToken) {
        localStorage.setItem("user", JSON.stringify(response.data.object));
      }
      return response.data.object;
    });
  }

  logout() {
    localStorage.removeItem("user");
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
}

export { AuthService };
