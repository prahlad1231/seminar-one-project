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
  GridRowModesModel,
} from "@mui/x-data-grid";

import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";

interface EditToolbarProps {
  setInitialRows: React.Dispatch<React.SetStateAction<any[]>>;
  setRowModesModel: React.Dispatch<React.SetStateAction<GridRowModesModel>>;
  columnFields: string[];
  header: string;
  canAdd: boolean;
}

function EditToolbar(props: EditToolbarProps) {
  const { setInitialRows, setRowModesModel, columnFields, header, canAdd } =
    props;

  const handleClick = () => {
    if (!canAdd) {
      alert("Cannot Modify Items!");
      return;
    }
    const id = 0;
    // setRows((oldRows) => [...oldRows, { id, name: "", age: "", isNew: true }]);
    setInitialRows((oldRows) => [...oldRows, { id, isNew: true }]);
    setRowModesModel((oldModel) => ({
      ...oldModel,
      [id]: {
        mode: GridRowModes.Edit,
        fieldToFocus: columnFields[1],
      },
    }));
  };

  return (
    <GridToolbarContainer>
      <Button color="primary" startIcon={<AddIcon />} onClick={handleClick}>
        {header}
      </Button>
    </GridToolbarContainer>
  );
}

interface CustomDataGridProps {
  initialRows: any[];
  initialColumns: any[];
  setInitialRows: React.Dispatch<React.SetStateAction<any[]>>;
  columnFields: string[];
  header: string;
  updateData: (updatedRow: any, isNew: boolean) => void;
  deleteData: (id: number) => void;
  canAdd: boolean;
}

export default function CustomDataGrid({
  initialRows,
  initialColumns,
  setInitialRows,
  columnFields,
  header,
  updateData,
  deleteData,
  canAdd,
}: CustomDataGridProps) {
  // const [rows, setRows] = React.useState(initialRows);
  const [rowModesModel, setRowModesModel] = React.useState<GridRowModesModel>(
    {}
  );

  const [open, setOpen] = React.useState<boolean>(false);
  const [dialogType, setDialogType] = React.useState<"save" | "delete" | "">(
    ""
  );
  const [currentRow, setCurrentRow] = React.useState<any | null>(null);

  // React.useEffect(() => {
  //   if (initialRows?.length) {
  //     setInitialRows(initialRows);
  //   }
  // }, [initialRows]);

  const handleDialogOpen = (type: "save" | "delete", row: any) => {
    setDialogType(type);
    setCurrentRow(row);
    setOpen(true);
  };

  const handleDialogClose = () => {
    setOpen(false);
  };

  const handleRowEditStop = (params: any, event: any) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const handleEditClick = (id: number) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
  };

  const handleSaveClick = (id: number) => () => {
    const row = initialRows.find((row) => row.id === id);
    handleDialogOpen("save", row);
    // setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
  };

  const handleDeleteClick = (id: number) => () => {
    const row = initialRows.find((row) => row.id === id);
    handleDialogOpen("delete", row);
    // setRows(rows.filter((row) => row.id !== id));
  };

  const handleBookOnlineClick = (id: number) => {
    alert("Booking: " + id);
  };

  const handleDialogConfirm = async () => {
    switch (dialogType) {
      case "save":
        setRowModesModel({
          ...rowModesModel,
          [currentRow.id]: { mode: GridRowModes.View },
        });
        // const updatedRow = { ...currentRow, isNew: false };
        // setInitialRows(
        //   initialRows.map((row) =>
        //     row.id === currentRow.id ? updatedRow : row
        //   )
        // );

        break;

      case "delete":
        console.log("handleDialogConfirm: delete");
        // setRows(rows.filter((row) => row.id !== currentRow.id));
        deleteData(currentRow.id);
        // try {
        //   await deleteData(currentRow.id);
        //   setRows(rows.filter((row) => row.id !== currentRow.id));
        // } catch (error) {
        //   alert("Error");
        //   console.log(error);
        // }

        break;

      default:
        break;
    }
    handleDialogClose();
  };

  const handleDialogCancel = (id: number) => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = initialRows.find((row) => row.id === id);
    if (editedRow.isNew) {
      setInitialRows(initialRows.filter((row) => row.id !== id));
    }
  };

  const handleCancelClick = (id: number) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = initialRows.find((row) => row.id === id);
    if (editedRow.isNew) {
      setInitialRows(initialRows.filter((row) => row.id !== id));
    }
  };

  const processRowUpdate = (newRow: any) => {
    const updatedRow = { ...newRow, isNew: false };
    setInitialRows(
      initialRows.map((row) => (row.id === newRow.id ? updatedRow : row))
    );
    console.log(`processRowUpdate: ${JSON.stringify(updatedRow)}`);
    // todo: send updated row to backend
    updateData(updatedRow, currentRow.isNew);

    return updatedRow;
  };

  // const processRowUpdate = async (newRow) => {
  //   const updatedRow = { ...newRow, isNew: false };
  //   try {
  //     await updateData(updatedRow, currentRow.isNew);
  //     setInitialRows(rows.map((row) => (row.id === newRow.id ? updateData : row)));
  //     return updatedRow;
  //   } catch (error) {
  //     console.log(error);
  //     alert("error");
  //   }
  // };

  const handleRowModesModelChange = (newRowModesModel: GridRowModesModel) => {
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
      getActions: ({ id }: { id: number }) => {
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
        rows={initialRows}
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
          toolbar: {
            setInitialRows,
            setRowModesModel,
            header,
            columnFields,
            canAdd,
          },
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
