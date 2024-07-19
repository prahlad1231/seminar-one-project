import { DataGrid } from "@mui/x-data-grid";
import { LocationService } from "../services/SeminarService";
import { useEffect, useState } from "react";
import NoPermissionPage from "./NoPermissionPage";
import CustomDataGrid from "./shared/CustomDataGrid";

const LocationList = () => {
  const locationService = new LocationService();

  const [hasPermission, setHasPermission] = useState(false);

  const [locationList, setLocationList] = useState([
    {
      id: "",
      venueName: "",
      streetName: "",
      streetNumber: "",
      state: "",
      website: "",
    },
  ]);

  useEffect(() => {
    locationService
      .getAllVenues()
      .then((result) => {
        if (result && result.data) {
          setHasPermission(true);
          setLocationList(result.data.object);
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
          alert("Error loading venues!");
        }
      });
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    {
      field: "venueName",
      headerName: "Venue Name",
      width: 150,
      editable: true,
    },
    {
      field: "streetName",
      headerName: "Street Name",
      width: 150,
      editable: true,
    },
    {
      field: "streetNumber",
      headerName: "Street Number",
      width: 130,
      editable: true,
    },
    { field: "state", headerName: "State", width: 130, editable: true },
    { field: "website", headerName: "Website", width: 180, editable: true },
  ];

  const columnFields = ["id", "venueName", "streetName", "state", "website"];

  const updateVenue = (updatedData) => {
    console.log(`LocationList.js: ${JSON.stringify(updatedData)}`);
  };

  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Venues</h2>
      {/* <DataGrid
        rows={locationList}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
        checkboxSelection
      /> */}

      <CustomDataGrid
        initialRows={locationList}
        initialColumns={columns}
        columnFields={columnFields}
        header="Add Venue"
        updateVenue={updateVenue}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default LocationList;
