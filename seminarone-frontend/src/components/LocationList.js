import { DataGrid } from "@mui/x-data-grid";
import { LocationService } from "../services/SeminarService";
import { useEffect, useState } from "react";

const LocationList = () => {
  const locationService = new LocationService();

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
        setLocationList(result.data.object);
      })
      .catch((err) => {
        if (err.response.data) {
          alert(err.response.data.message);
        }
        alert("Error loading venues!");
      });
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "venueName", headerName: "Venue Name", width: 150 },
    { field: "streetName", headerName: "Street Name", width: 150 },
    { field: "streetNumber", headerName: "Street Number", width: 130 },
    { field: "state", headerName: "State", width: 130 },
    { field: "website", headerName: "Website", width: 180 },
  ];
  return (
    <div style={{ height: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Venues</h2>
      <DataGrid
        rows={locationList}
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

export default LocationList;
