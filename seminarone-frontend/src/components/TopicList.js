import { useEffect, useState } from "react";

import { TopicService } from "../services/SeminarService";
import { DataGrid } from "@mui/x-data-grid";

const TopicList = () => {
  const topicService = new TopicService();
  const [topicList, setTopicList] = useState([{ id: "", name: "" }]);

  useEffect(() => {
    topicService
      .getAllTopics()
      .then((result) => {
        setTopicList(result.data.object);
      })
      .catch((err) => {
        console.log(err);
        if (err.response.data) {
          alert(err.response.data.message);
        }
        alert("Error loading topics!");
      });
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "name", headerName: "Topic Name", width: 130 },
  ];

  return (
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
  );
};

export default TopicList;
