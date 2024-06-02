import { useEffect, useState } from "react";

import { TopicService } from "../services/SeminarService";
import { DataGrid } from "@mui/x-data-grid";
import NoPermissionPage from "./NoPermissionPage";

const TopicList = () => {
  const topicService = new TopicService();
  const [topicList, setTopicList] = useState([{ id: "", name: "" }]);
  const [hasPermission, setHasPermission] = useState(false);

  useEffect(() => {
    topicService
      .getAllTopics()
      .then((result) => {
        if (result && result.data) {
          setTopicList(result.data.object);
          setHasPermission(true);
          console.log(result.data.object);
        } else {
          alert("Server Error!");
        }
      })
      .catch((err) => {
        console.log(err);
        if (err.response && err.response.data) {
          alert(err.response.data.message);
        } else if (err.message === "Network Error") {
          alert(
            "Network error. Please check your internet connection or try again later."
          );
        } else {
          alert("Error loading topics!");
        }
      });
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "name", headerName: "Topic Name", width: 130 },
  ];

  return hasPermission ? (
    <div style={{ height: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Topics</h2>
      <DataGrid
        rows={topicList}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
        checkboxSelection
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default TopicList;
