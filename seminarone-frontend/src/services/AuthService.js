import axios from "axios";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

class AuthService {
  login(loginDetails) {
    return axios({
      method: "post",
      data: loginDetails,
      url: API_URL + `/authenticate`,
    });
  }
}

export { AuthService };
