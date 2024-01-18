import { useState, useEffect } from "react";
import TaskList from "./TaskList";
import { tasksApi } from "../api/TasksApi";
import './Tasks.css'

const Tasks = () => {
    const [tasks, setTasks] = useState(null);
    const [isPending, setIsPending] = useState(true);
    const taskListTitle = 'All tasks';

    const handleDelete = (id) => {
        const newTasksList = tasks.filter(task => task.id !== id);
        setTasks(newTasksList);
    }

    // note - on strict mode react renders components twice, so it will run 2 times
    useEffect(() => {
        tasksApi.getAll()
            .then((response) => {
                setTimeout(() => {          //timeout for testing only
                    setTasks(response.data)
                    setIsPending(false);
                }, 4000);
            })
            .catch(error => console.error('Fetch error: ', error))  // u can output multiple objects in error
    }, []);

    return (
        <div classname="tasks">
            {isPending && <div className="loading"><p>Loading...</p></div>}
            {tasks && <TaskList tasks={tasks} title={taskListTitle} handleDelete={handleDelete} />}
        </div>
    );
}

export default Tasks;