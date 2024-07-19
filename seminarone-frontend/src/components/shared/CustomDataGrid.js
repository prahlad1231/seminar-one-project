import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/DeleteOutlined";
import SaveIcon from "@mui/icons-material/Save";
import CancelIcon from "@mui/icons-material/Close";
import {
  GridRowModes,
  DataGrid,
  GridToolbarContainer,
  GridActionsCellItem,
  GridRowEditStopReasons,
} from "@mui/x-data-grid";
import { randomId } from "@mui/x-data-grid-generator";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";

function EditToolbar(props) {
  const { setRows, setRowModesModel } = props;

  const handleClick = () => {
    const id = randomId();
    // setRows((oldRows) => [...oldRows, { id, name: "", age: "", isNew: true }]);
    setRows((oldRows) => [...oldRows, { id, isNew: true }]);
    setRowModesModel((oldModel) => ({
      ...oldModel,
      [id]: {
        mode: GridRowModes.Edit,
        // fieldToFocus: props.columnFields,
      },
    }));
  };

  return (
    <GridToolbarContainer>
      <Button color="primary" startIcon={<AddIcon />} onClick={handleClick}>
        {props.header}
      </Button>
    </GridToolbarContainer>
  );
}

export default function CustomDataGrid({
  initialRows,
  initialColumns,
  columnFields,
  header,
  updateVenue,
}) {
  const [rows, setRows] = React.useState(initialRows);
  const [rowModesModel, setRowModesModel] = React.useState({});

  const [open, setOpen] = React.useState(false);
  const [dialogType, setDialogType] = React.useState("");
  const [currentRow, setCurrentRow] = React.useState(null);

  const handleDialogOpen = (type, row) => {
    setDialogType(type);
    setCurrentRow(row);
    setOpen(true);
  };

  const handleDialogClose = () => {
    setOpen(false);
  };

  const handleRowEditStop = (params, event) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const handleEditClick = (id) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
  };

  const handleSaveClick = (id) => () => {
    const row = rows.find((row) => row.id === id);
    handleDialogOpen("save", row);
    // setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
  };

  const handleDeleteClick = (id) => () => {
    const row = rows.find((row) => row.id === id);
    handleDialogOpen("delete", row);
    // setRows(rows.filter((row) => row.id !== id));
  };

  const handleDialogConfirm = () => {
    switch (dialogType) {
      case "save":
        setRowModesModel({
          ...rowModesModel,
          [currentRow.id]: { mode: GridRowModes.View },
        });
        const updatedRow = { ...currentRow, isNew: false };
        setRows(
          rows.map((row) => (row.id === currentRow.id ? updatedRow : row))
        );
        updateVenue(updatedRow);
        break;

      case "delete":
        setRows(rows.filter((row) => row.id !== currentRow.id));
        break;

      default:
        break;
    }
    handleDialogClose();
  };

  const handleDialogCancel = (id) => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = rows.find((row) => row.id === id);
    if (editedRow.isNew) {
      setRows(rows.filter((row) => row.id !== id));
    }
  };

  const handleCancelClick = (id) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = rows.find((row) => row.id === id);
    if (editedRow.isNew) {
      setRows(rows.filter((row) => row.id !== id));
    }
  };

  const processRowUpdate = (newRow) => {
    const updatedRow = { ...newRow, isNew: false };
    setRows(rows.map((row) => (row.id === newRow.id ? updatedRow : row)));
    // console.log(updatedRow);
    // todo: send updated row to backend
    // updateVenue(updatedRow);
    return updatedRow;
  };

  const handleRowModesModelChange = (newRowModesModel) => {
    setRowModesModel(newRowModesModel);
  };

  const columns = [
    ...initialColumns,
    {
      field: "actions",
      type: "actions",
      headerName: "Actions",
      width: 100,
      cellClassName: "actions",
      getActions: ({ id }) => {
        const isInEditMode = rowModesModel[id]?.mode === GridRowModes.Edit;

        if (isInEditMode) {
          return [
            <GridActionsCellItem
              icon={<SaveIcon />}
              label="Save"
              sx={{
                color: "primary.main",
              }}
              onClick={handleSaveClick(id)}
            />,
            <GridActionsCellItem
              icon={<CancelIcon />}
              label="Cancel"
              className="textPrimary"
              onClick={handleCancelClick(id)}
              color="inherit"
            />,
          ];
        }

        return [
          <GridActionsCellItem
            icon={<EditIcon />}
            label="Edit"
            className="textPrimary"
            onClick={handleEditClick(id)}
            color="inherit"
          />,
          <GridActionsCellItem
            icon={<DeleteIcon />}
            label="Delete"
            onClick={handleDeleteClick(id)}
            color="inherit"
          />,
        ];
      },
    },
  ];

  return (
    <Box
      sx={{
        height: 500,
        width: "100%",
        "& .actions": {
          color: "text.secondary",
        },
        "& .textPrimary": {
          color: "text.primary",
        },
      }}
    >
      <DataGrid
        rows={rows}
        columns={columns}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        processRowUpdate={processRowUpdate}
        slots={{
          toolbar: EditToolbar,
        }}
        slotProps={{
          toolbar: { setRows, setRowModesModel, header, columnFields },
        }}
      />

      <Dialog open={open} onClose={handleDialogClose}>
        <DialogTitle>
          {dialogType === "save" ? "Save Changes" : "Delete Row"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText>
            {dialogType === "save"
              ? "Do you want to save the changes?"
              : "Do you want to delete this row?"}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleDialogConfirm} color="primary">
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}
