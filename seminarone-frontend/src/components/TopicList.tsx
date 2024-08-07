import { useEffect, useState } from "react";

import { TopicService } from "../services/SeminarService";
import NoPermissionPage from "./NoPermissionPage";
import CustomDataGrid from "./shared/CustomDataGrid";

interface ITopic {
  id: number;
  name: string;
}

const TopicList = () => {
  const topicService = new TopicService();

  const [topicList, setTopicList] = useState<ITopic[]>([{ id: -1, name: "" }]);
  const [refetchTopic, setRefetchTopic] = useState<boolean>(false);
  const [hasPermission, setHasPermission] = useState<boolean>(false);

  useEffect(() => {
    topicService
      .getAllTopics()
      .then((result) => {
        if (result && result.data) {
          setTopicList(result.data.object);
          setHasPermission(true);
          console.log(result.data.object);
        } else {
          alert("Server Error!");
        }
      })
      .catch((err) => {
        console.log(err);
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
  }, [refetchTopic]);

  const updateData = (updatedData: ITopic, isNew: boolean) => {
    isNew
      ? topicService
          .save(updatedData)
          .then((response) => {
            if (response && response.data) {
              setRefetchTopic(!refetchTopic);
              alert(response.data.message);
            }
          })
          .catch((err) => {
            if (err.response && err.response.data) {
              alert(err.response.data.message);
            } else {
              setRefetchTopic(!refetchTopic); // find a way not to trigger API call
              alert("Error saving new topic!");
            }
          })
      : topicService
          .update(updatedData)
          .then((response) => {
            if (response && response.data) {
              setRefetchTopic(!refetchTopic);
              alert(response.data.message);
            }
          })
          .catch((err) => {
            if (err.response && err.response.data) {
              alert(err.response.data.message);
            } else {
              alert("Error updating topic!");
            }
          });
  };

  const deleteData = (id: number) => {
    topicService
      .delete(id)
      .then((response) => {
        if (response && response.data) {
          setRefetchTopic(!refetchTopic);
          alert(response.data.message);
          // const updatedList = topicList.filter((topic) => topic.id !== id);
          // setTopicList(updatedList);
        }
      })
      .catch((err) => {
        if (err.response && err.response.data) {
          alert(err.response.data.message);
        } else {
          alert("Error deleting topic!");
        }
      });
  };

  const columns = [
    // { field: "id", headerName: "ID", width: 70 },
    { field: "name", headerName: "Topic Name", width: 250, editable: true },
  ];

  const columnFields = ["id", "name"];

  return hasPermission ? (
    <div style={{ minHeight: 400, width: "100%" }}>
      <h2 style={{ marginBottom: "1.5rem" }}>List of Topics</h2>
      <CustomDataGrid
        initialRows={topicList}
        setInitialRows={setTopicList}
        initialColumns={columns}
        columnFields={columnFields}
        header="Add Topic"
        updateData={updateData}
        deleteData={deleteData}
        canAdd={true}
      />
    </div>
  ) : (
    <NoPermissionPage />
  );
};

export default TopicList;
