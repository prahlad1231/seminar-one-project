import axios from "axios";

const API_URL = process.env.REACT_APP_SEMINAR_ONE_BASE_API;

class SeminarService {
  getAllSeminars() {
    return axios({
      method: "get",
      url: API_URL + "seminar/findAll",
    });
  }

  getSeminarById(seminarId) {
    return axios({
      method: "get",
      url: API_URL + `seminar/find/${seminarId}`,
    });
  }

  save(seminarDetails) {
    return axios({
      method: "post",
      data: seminarDetails,
      url: API_URL + `seminar/add`,
    });
  }
}

class LocationService {
  getAllVenues() {
    return axios({
      method: "get",
      url: API_URL + "location/findAll",
    });
  }

  getVenueById(venueId) {
    return axios({
      method: "get",
      url: API_URL + `location/find/${venueId}`,
    });
  }

  save(locationDetails) {
    return axios({
      method: "POST",
      url: API_URL + "location/save",
      data: locationDetails,
    });
  }
}

class TopicService {
  getAllTopics() {
    return axios({
      method: "get",
      url: API_URL + "topic/findAll",
    });
  }

  getTopicById(topicId) {
    return axios({
      method: "get",
      url: API_URL + `topic/find/${topicId}`,
    });
  }

  save(topicDetails) {
    return axios({
      method: "POST",
      url: API_URL + "topic/save",
      data: topicDetails,
    });
  }
}

export { SeminarService, LocationService, TopicService };
