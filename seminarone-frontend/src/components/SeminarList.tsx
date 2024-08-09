import { useEffect, useState } from "react";
import { BookingService, SeminarService } from "../services/SeminarService";
import NoPermissionPage from "./NoPermissionPage";
import CustomDataGrid from "./shared/CustomDataGrid";
import { AuthService } from "../services/AuthService";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@mui/material";

interface ISeminar {
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  price: number;
  topicName: string;
  venueName: string;
}

const SeminarList = () => {
  const seminarService = new SeminarService();
  const authService = new AuthService();
  const bookingService = new BookingService();

  const [hasPermission, setHasPermission] = useState<boolean>(false);

  const [seminarList, setSeminarList] = useState<ISeminar[]>([
    {
      id: -1,
      title: "",
      startDate: "",
      endDate: "",
      price: -1,
      topicName: "",
      venueName: "",
    },
  ]);

  const [open, setOpen] = useState<boolean>(false);
  const [bookingNotes, setBookingNotes] = useState<string>("");
  const [selectedSeminar, setSelectedSeminar] = useState<ISeminar | null>(null);

  useEffect(() => {
    seminarService
      .getAllSeminars()
      .then((result) => {
        if (result && result.data) {
          setHasPermission(true);
          const formattedSeminarList = result.data.object.map(
            (seminar: ISeminar) => ({
              ...seminar,
              startDate: formatDate(seminar.startDate),
              endDate: formatDate(seminar.endDate),
            })
          );
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

  interface ICustomColumn {
    field: string;
    headerName: string;
    width: number;
    editable: boolean;
    renderCell?: (params: any) => JSX.Element;
  }

  const columns: ICustomColumn[] = [
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

  const handleBookButton = (params: any) => {
    // console.log(params);
    const name = params.row.title;
    const id = params.row.id;
    setSelectedSeminar(params.row);
    // alert(`Booking, ID: ${id}, NAME: ${name}`);
    setOpen(true);
  };

  const renderBookingButton = (params: any) => {
    return (
      <strong>
        <Button
          variant="contained"
          color="primary"
          size="small"
          style={{ marginLeft: 10 }}
          onClick={() => handleBookButton(params)}
        >
          Book Now
        </Button>
      </strong>
    );
  };

  const userRole = authService.getCurrentUserRole();
  if (userRole === "USER") {
    columnFields.push("Booking");
    columns.push({
      field: "booking",
      headerName: "Booking",
      width: 150,
      editable: false,
      renderCell: renderBookingButton,
    });
  }

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  const updateData = (updatedData: ISeminar | null) => {
    alert("Feature coming soon!");
  };

  const deleteData = (id: number) => {
    alert("Feature coming soon!");
  };

  const handleDialogClose = () => {
    setOpen(false);
    setBookingNotes("");
  };

  const handleBookingSubmit = () => {
    // alert(
    //   `Booking for seminar : ${selectedSeminar?.title}, Notes: ${bookingNotes}`
    // );
    const bookingDetails = {
      seminarEntityId: selectedSeminar!.id,
      bookingNotes: bookingNotes,
    };
    console.log(bookingDetails);

    bookingService
      .save(bookingDetails)
      .then((response) => {
        if (response && response.data) {
          alert(response.data.message);
        }
      })
      .catch((err) => {
        if (err && err.data) {
          alert("Error: " + err.data.message);
        } else {
          alert("Error Booking Seminar!");
        }
      });

    handleDialogClose();
  };

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
        canAdd={true}
      />

      <Dialog open={open} onClose={handleDialogClose}>
        <DialogTitle>Book Seminar</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please enter your booking notes for the seminar:{" "}
            {selectedSeminar?.title}
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="bookingNotes"
            label="Booking Notes"
            type="text"
            fullWidth
            value={bookingNotes}
            onChange={(e) => setBookingNotes(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleBookingSubmit} color="primary">
            Book
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default SeminarList;
