import { useEffect, useState } from "react";
import NoPermissionPage from "./NoPermissionPage";
import { UserService } from "../services/UserService";
import CustomDataGrid from "./shared/CustomDataGrid";

export interface IUser {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  username: string;
  active: boolean | null;
}

const UserList = () => {
  const userService = new UserService();

  const [hasPermission, setHasPermission] = useState<boolean>(false);

  const [refetchUserService, setRefetchUserService] = useState<boolean>(false);

  const [userList, setUserList] = useState<IUser[]>([
    {
      id: -1,
      email: "",
      firstName: "",
      lastName: "",
      username: "",
      active: null,
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
  }, [refetchUserService]);

  const columns = [
    // { field: "id", headerName: "ID", width: 70 },
    { field: "email", headerName: "Email", width: 150, editable: true },
    {
      field: "firstName",
      headerName: "First Name",
      width: 150,
      editable: true,
    },
    { field: "lastName", headerName: "Last Name", width: 130, editable: true },
    { field: "username", headerName: "Username", width: 130, editable: true },
    { field: "active", headerName: "Is Active", width: 180, editable: true },
  ];

  const columnFields = [
    "id",
    "email",
    "firstName",
    "lastName",
    "username",
    "active",
  ];

  const updateData = (updatedData: IUser) => {
    console.log(updatedData);
    userService
      .updateUser(updatedData)
      .then((response) => {
        if (response && response.data) {
          setRefetchUserService(!refetchUserService);
          alert(response.data.message);
        }
      })
      .catch((err) => {
        if (err.response && err.response.data) {
          alert(err.response.data.message);
        } else {
          setRefetchUserService(!refetchUserService); // todo: find a way not to trigger API call
          alert("Error saving user data");
        }
      });
  };

  const deleteData = (id: number) => {
    alert("Coming soon...");
  };

  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Users</h2>
      {/* <DataGrid
        rows={userList}
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
        initialRows={userList}
        initialColumns={columns}
        setInitialRows={setUserList}
        columnFields={columnFields}
        header="Add User"
        updateData={updateData}
        deleteData={deleteData}
        canAdd={true}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default UserList;
