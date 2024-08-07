import { useEffect, useState } from "react";
import { AuthService } from "../services/AuthService";
import { BookingService } from "../services/SeminarService";
import CustomDataGrid from "./shared/CustomDataGrid";
import NoPermissionPage from "./NoPermissionPage";

interface IBooking {
  bookingId: number;
  seminarName: string;
  startDate: string;
  endDate: string;
  price: number;
  topicName: string;
  venueName: string;
  bookingNotes: string | null;
}

const BookingList = () => {
  const bookingService = new BookingService();
  const authService = new AuthService();

  const [hasPermission, setHasPermission] = useState<boolean>(false);

  const [bookingList, setBookingList] = useState<IBooking[]>([
    {
      bookingId: -1,
      seminarName: "",
      startDate: "",
      endDate: "",
      price: -1,
      topicName: "",
      venueName: "",
      bookingNotes: "",
    },
  ]);
  const [refetchBooking, setRefetchBooking] = useState<boolean>(false);

  const [open, setOpen] = useState<boolean>(false);

  useEffect(() => {
    bookingService
      .getAllBookings(authService.getCurrentUserId())
      .then((result) => {
        if (result && result.data) {
          const formattedBookingList = result.data.object.map(
            (booking: IBooking) => ({
              ...booking,
              startDate: formatDate(booking.startDate),
              endDate: formatDate(booking.endDate),
            })
          );
          setHasPermission(true);
          setBookingList(formattedBookingList);
        } else {
          alert("Server error!");
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
          alert("Error loading topics!");
        }
      });
  }, [refetchBooking]);

  const columns = [
    {
      field: "seminarName",
      headerName: "Seminar Name",
      width: 150,
      editable: false,
    },
    {
      field: "startDate",
      headerName: "Start Date",
      width: 150,
      editable: false,
    },
    {
      field: "endDate",
      headerName: "End Date",
      width: 150,
      editable: false,
    },
    {
      field: "price",
      headerName: "Price",
      width: 150,
      editable: false,
    },
    {
      field: "topicName",
      headerName: "Topic Name",
      width: 150,
      editable: false,
    },
    {
      field: "venueName",
      headerName: "Venue Name",
      width: 150,
      editable: false,
    },
    {
      field: "bookingNotes",
      headerName: "Booking Notes",
      width: 250,
      editable: false,
    },
  ];

  const columnFields = [
    "id",
    "seminarName",
    "startDate",
    "endDate",
    "price",
    "topicName",
    "venueName",
    "bookingNotes",
  ];

  const deleteData = (id: number) => {
    bookingService
      .delete(id)
      .then((response) => {
        if (response && response.data) {
          setRefetchBooking(!refetchBooking);
          alert(response.data.message);
          // const updatedList = bookingList.filter((booking) => booking.id !== id);
          // setBookingList(updatedList);
        } else {
          alert("Error deleting");
        }
      })
      .catch((err) => {
        if (err && err.data) {
          alert("Error: " + err.data.message);
        } else {
          console.log(err);
          alert("Error deleting data");
        }
      });
  };

  const updateData = (updatedData: IBooking) => {
    alert("Feature coming soon...");
  };

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>My Bookings</h2>
      <CustomDataGrid
        initialRows={bookingList}
        setInitialRows={setBookingList}
        initialColumns={columns}
        columnFields={columnFields}
        header="Bookings"
        updateData={updateData}
        deleteData={deleteData}
        canAdd={false}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default BookingList;
