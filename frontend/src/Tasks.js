import { useState, useEffect } from "react";
import TaskList from "./TaskList";
import { tasksApi } from "./tasksApi";

const Tasks = () => {
    const [tasks, setTasks] = useState([]);
    const taskListTitle = 'All tasks';

    const handleDelete = (id) => {
        const newTasksList = tasks.filter(task => task.id !== id);
        setTasks(newTasksList);
    }

    // note - on strict mode react renders components twice, so it will run 2 times
    useEffect(() => {
        tasksApi.getAll()
        .then((response) => {
            setTasks(response.data)
        })
        .catch(error => console.error('Fetch error: ', error))  // u can output multiple objects in error
    }, []);

    return ( 
        <TaskList tasks={tasks} title={taskListTitle} handleDelete={handleDelete}/>
     );
}
 
export default Tasks;