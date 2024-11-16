import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/";
const BEARER_TOKEN = "";

class SeminarService {
    getAllSeminars() {
        return axios({
            method: "get",
            url: API_URL + "/seminar/findAll",
            headers: 
        })
    }

}
