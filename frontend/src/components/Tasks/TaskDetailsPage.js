import TaskDetails from "./TaskDetails";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";
import { tasksApi } from "../../api/TasksApi";
import { useAuth } from "react-oidc-context";
import { useState, useEffect } from "react";

const TaskDetailsPage = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const { id } = useParams();
    const [ task, setTask ] = useState();

    useEffect(() => {
        tasksApi.getById(id, accessToken)
            .then((res) => {
                setTask(res.data[0]);
                //console.log(res.data[0])
            })
    }, [id, accessToken]);


    return (
        <div className="TaskDetailsPage">
            {task && <TaskDetails tasks={task} />}
        </div>);
}

export default TaskDetailsPage;