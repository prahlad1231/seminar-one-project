import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import NoPermissionPage from "./NoPermissionPage";
import { UserService } from "../services/UserService";

const UserList = () => {
  const userService = new UserService();

  const [hasPermission, setHasPermission] = useState(false);

  const [userList, setUserList] = useState([
    {
      id: "",
      email: "",
      firstName: "",
      lastName: "",
      username: "",
      active: "",
    },
  ]);

  useEffect(() => {
    userService
      .getAllUsers()
      .then((result) => {
        if (result && result.data) {
          setHasPermission(true);
          setUserList(result.data.object);
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
          alert("Error loading users!");
        }
      });
  }, [userService]);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "email", headerName: "Email", width: 150 },
    { field: "firstName", headerName: "First Name", width: 150 },
    { field: "lastName", headerName: "Last Name", width: 130 },
    { field: "username", headerName: "Username", width: 130 },
    { field: "active", headerName: "Is Active", width: 180 },
  ];
  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Users</h2>
      <DataGrid
        rows={userList}
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

export default UserList;
