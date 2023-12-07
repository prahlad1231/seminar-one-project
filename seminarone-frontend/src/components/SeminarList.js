import { useEffect, useState } from "react";
import { SeminarService } from "../services/SeminarService";
import { DataGrid } from "@mui/x-data-grid";

const SeminarList = () => {
  const seminarService = new SeminarService();

  const [seminarList, setSeminarList] = useState([
    {
      id: "",
      title: "",
      startDate: "",
      endDate: "",
      price: "",
      topicEntityId: "",
      locationEntityId: "",
    },
  ]);

  useEffect(() => {
    seminarService
      .getAllSeminars()
      .then((result) => {
        setSeminarList(result.data.object);
        console.log(JSON.stringify(seminarList));
      })
      .catch((err) => {
        if (err.response.data) {
          alert(err.response.data.message);
        }
        alert("Error loading seminars!");
      });
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "title", headerName: "Title", width: 150 },
    { field: "startDate", headerName: "Start Date", width: 150 },
    { field: "endDate", headerName: "End Date", width: 150 },
    { field: "price", headerName: "Price", width: 150 },
    { field: "topicEntityId", headerName: "Topic", width: 150 },
    { field: "locationEntityId", headerName: "Venue", width: 150 },
  ];

  return (
    <div style={{ height: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Topics</h2>
      <DataGrid
        rows={seminarList}
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

export default SeminarList;
