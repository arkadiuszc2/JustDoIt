import TaskList from "./TaskList";
import { tasksApi } from "../api/TasksApi";
import './Tasks.css'
import useFetch from "../custom-hooks/useFetch";

const Tasks = () => {
    const taskListTitle = 'All tasks';
    const {data: tasks, isPending, error} = useFetch(tasksApi.getAll);

    const handleDelete = (id) => {
        //const newTasksList = tasks.filter(task => task.id !== id);
        //setTasks(newTasksList);       this is old version of delete and now its not working
    }


    return (
        <div className="tasks">
            {error && <div className="taskError">{error.message}</div>}
            {isPending && <div className="loading"><p>Loading...</p></div>}
            {tasks && <TaskList tasks={tasks} title={taskListTitle} handleDelete={handleDelete} />}
        </div>
    );
    
}

export default Tasks;