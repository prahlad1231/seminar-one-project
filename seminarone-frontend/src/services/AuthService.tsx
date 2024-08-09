import axios from "axios";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

class AuthService {
  login(loginDetails: any) {
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

  validateToken(jwtToken: any) {
    return axios({
      method: "get",
      url: API_URL + `/expired?jwtToken=` + jwtToken,
    }).then((response) => {
      if (response.data) {
        return response.data;
      }
      return false;
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user") || "null");
  }

  getCurrentUserRole() {
    return this.getCurrentUser()["role"];
  }

  getCurrentUserId() {
    return this.getCurrentUser()["id"];
  }

  getAuthHeader() {
    const user = JSON.parse(localStorage.getItem("user") || "null");
    if (user && user.jwtToken) {
      return {
        Authorization: `Bearer ${user.jwtToken}`,
      };
    } else {
      return {};
    }
  }

  getJwtToken() {
    const user = JSON.parse(localStorage.getItem("user") || "null");
    if (user && user.jwtToken) {
      return user.jwtToken;
    } else {
      return "";
    }
  }
}

export { AuthService };
