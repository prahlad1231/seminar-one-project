import { DataGrid } from "@mui/x-data-grid";
import { LocationService } from "../services/SeminarService";
import { useEffect, useState } from "react";
import NoPermissionPage from "./NoPermissionPage";
import CustomDataGrid from "./shared/CustomDataGrid";

interface ILocation {
  id: number;
  venueName: string;
  streetName: string;
  streetNumber: number | null;
  state: string;
  website: string | null;
}

const LocationList = () => {
  const locationService = new LocationService();

  const [hasPermission, setHasPermission] = useState(false);

  const [refetchLocationService, setRefetchLocationService] = useState(false);

  const [locationList, setLocationList] = useState<ILocation[]>([
    {
      id: -1,
      venueName: "",
      streetName: "",
      streetNumber: null,
      state: "",
      website: null,
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
  }, [refetchLocationService]);

  const columns = [
    // { field: "id", headerName: "ID", width: 70 },
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

  const updateData = (updatedData: ILocation, isNew: boolean) => {
    console.log(
      `LocationList.js: ${JSON.stringify(updatedData)}, isNew: ${isNew}`
    );
    isNew
      ? locationService
          .save(updatedData)
          .then((response) => {
            if (response && response.data) {
              setRefetchLocationService(!refetchLocationService);
              alert(response.data.message);
            }
          })
          .catch((err) => {
            if (err.response && err.response.data) {
              alert(err.response.data.message);
            } else {
              alert("Error saving new venue!");
            }
          })
      : locationService
          .update(updatedData)
          .then((response) => {
            if (response && response.data) {
              setRefetchLocationService(!refetchLocationService);
              alert(response.data.message);
              console.log(response.data.object);
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
              alert("Error updating venue!");
            }
          });
  };

  const deleteData = (id: number) => {
    console.log("Deleting: " + id);
    locationService
      .delete(id)
      .then((response) => {
        if (response && response.data) {
          setRefetchLocationService(!refetchLocationService);
          alert(response.data.message);
        }
      })
      .catch((err) => {
        if (err.response && err.response.data) {
          alert(err.response.data.message);
        } else {
          alert("Error deleting venue!");
        }
      });
  };

  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Venues</h2>

      <CustomDataGrid
        initialRows={locationList}
        initialColumns={columns}
        setInitialRows={setLocationList}
        columnFields={columnFields}
        header="Add Venue"
        updateData={updateData}
        deleteData={deleteData}
        canAdd={true}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default LocationList;
