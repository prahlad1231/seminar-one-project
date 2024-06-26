import axios from "axios";
import { AuthService } from "./AuthService";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

const authService = new AuthService();

class SeminarService {
  getAllSeminars() {
    return axios({
      method: "get",
      url: API_URL + "/seminar/findAll",
      headers: authService.getAuthHeader(),
    });
  }

  getSeminarById(seminarId) {
    return axios({
      method: "get",
      url: API_URL + `/seminar/find/${seminarId}`,
      headers: authService.getAuthHeader(),
    });
  }

  save(seminarDetails) {
    return axios({
      method: "post",
      data: seminarDetails,
      url: API_URL + `/seminar/add`,
      headers: authService.getAuthHeader(),
    });
  }
}

class LocationService {
  getAllVenues() {
    return axios({
      method: "get",
      url: API_URL + "/location/findAll",
      headers: authService.getAuthHeader(),
    });
  }

  getVenueById(venueId) {
    return axios({
      method: "get",
      url: API_URL + `/location/find/${venueId}`,
      headers: authService.getAuthHeader(),
    });
  }

  save(locationDetails) {
    return axios({
      method: "POST",
      url: API_URL + "/location/save",
      data: locationDetails,
      headers: authService.getAuthHeader(),
    });
  }
}

class TopicService {
  getAllTopics() {
    return axios({
      method: "get",
      url: API_URL + "/topic/findAll",
      headers: authService.getAuthHeader(),
    });
  }

  getTopicById(topicId) {
    return axios({
      method: "get",
      url: API_URL + `/topic/find/${topicId}`,
      headers: authService.getAuthHeader(),
    });
  }

  save(topicDetails) {
    return axios({
      method: "POST",
      url: API_URL + "/topic/save",
      data: topicDetails,
      headers: authService.getAuthHeader(),
    });
  }
}

export { SeminarService, LocationService, TopicService };
