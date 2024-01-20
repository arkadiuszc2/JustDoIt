import TaskDetails from "./TaskDetails";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";
import { tasksApi } from "../../api/TasksApi";


const TaskDetailsPage = () => {
    const { id } = useParams();
    const {data: tasks} = useFetch(tasksApi.getById, id);
    return (
    <div className="TaskDetailsPage">
        {tasks && <TaskDetails tasks={tasks} />}
    </div>  );
}
 
export default TaskDetailsPage;