import { useEffect, useState } from "react";
import { SeminarService } from "../services/SeminarService";
import { DataGrid } from "@mui/x-data-grid";
import NoPermissionPage from "./NoPermissionPage";
import CustomDataGrid from "./shared/CustomDataGrid";

const SeminarList = () => {
  const seminarService = new SeminarService();

  const [hasPermission, setHasPermission] = useState(false);

  const [seminarList, setSeminarList] = useState([
    {
      id: "",
      title: "",
      startDate: "",
      endDate: "",
      price: "",
      topicName: "",
      venueName: "",
    },
  ]);

  useEffect(() => {
    seminarService
      .getAllSeminars()
      .then((result) => {
        if (result && result.data) {
          setHasPermission(true);
          const formattedSeminarList = result.data.object.map((seminar) => ({
            ...seminar,
            startDate: formatDate(seminar.startDate),
            endDate: formatDate(seminar.endDate),
          }));
          setSeminarList(formattedSeminarList);
          console.log(JSON.stringify(formattedSeminarList));
        }
      })
      .catch((err) => {
        if (err.response && err.response.data) {
          alert(err.response.data.message);
        } else if (err.message === "Network Error") {
          alert(
            "Network error. Please check your internet connection or try again later."
          );
        } else {
          alert("Error loading seminars!");
        }
      });
  }, []);

  const columns = [
    // { field: "id", headerName: "ID", width: 70 },
    { field: "title", headerName: "Title", width: 150, editable: true },
    {
      field: "startDate",
      headerName: "Start Date",
      width: 150,
      editable: true,
    },
    { field: "endDate", headerName: "End Date", width: 150, editable: true },
    { field: "price", headerName: "Price", width: 150, editable: true },
    { field: "topicName", headerName: "Topic", width: 150, editable: true },
    { field: "venueName", headerName: "Venue", width: 150, editable: true },
  ];

  const columnFields = [
    "title",
    "startDate",
    "endDate",
    "price",
    "topicName",
    "venueName",
  ];

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  const updateData = (updatedData) => {};

  const deleteData = (id) => {};

  return hasPermission ? (
    <div style={{ height: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Seminars</h2>

      <CustomDataGrid
        initialRows={seminarList}
        initialColumns={columns}
        setInitialRows={setSeminarList}
        columnFields={columnFields}
        header="Add Seminar"
        updateData={updateData}
        deleteData={deleteData}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default SeminarList;
