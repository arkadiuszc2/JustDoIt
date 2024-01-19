import { useParams } from "react-router-dom";
import useFetch from "../custom-hooks/useFetch";
import { tasksApi } from "../api/TasksApi";

const TaskDetails = () => {
    const { id } = useParams();
    const {data, error, isPending} = useFetch(tasksApi.getById, id);
    return (
    <div className="task-details">
        { data && (
        <article>
            <h2>{data.data[0].title}</h2>
            <p>{data.data[0].description}</p>
        </article>)}
    </div>
    );
}

export default TaskDetails;

