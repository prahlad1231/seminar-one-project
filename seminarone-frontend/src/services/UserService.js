import axios from "axios";
import { AuthService } from "./AuthService";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

const authService = new AuthService();

class UserService {
  getAllUsers() {
    return axios({
      method: "get",
      url: API_URL + "/user/findAll",
      headers: authService.getAuthHeader(),
    });
  }

  updateUser(userDto) {
    return axios({
      method: "PUT",
      url: API_URL + "/user/update",
      headers: authService.getAuthHeader(),
      data: userDto,
    });
  }

  changePassword(changePasswordDto) {
    return axios({
      method: "post",
      url: API_URL + "/user/changePassword",
      headers: authService.getAuthHeader(),
      data: changePasswordDto,
    });
  }
}

export { UserService };
