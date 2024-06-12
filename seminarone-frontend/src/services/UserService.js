import axios from "axios";
import { AuthService } from "./AuthService";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

const authService = new AuthService();

class UserService {
  changePassword(changePasswordDto) {
    return axios({
      method: "post",
      url: API_URL + "/user/changePassword",
      header: authService.getAuthHeader(),
      body: changePasswordDto,
    });
  }
}

export { UserService };
