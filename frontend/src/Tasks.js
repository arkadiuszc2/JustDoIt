import { useState } from "react";
import TaskList from "./TaskList";

const Tasks = () => {
    const [tasks, setTasks] = useState([
        { title: 'Workouts', content: 'Pushups 10x3, Pullups 10x3, Squats 10x3', category: 'Sport', id: 1},
        { title: 'Create new endpoint', content: 'Finish the project', category: 'Work', id: 2},
        { title: 'Do homework', content: 'Do 5 math exercises', category: 'School', id: 3}
    ])
    const taskListTitle = 'All tasks';

    return ( 
        <TaskList tasks={tasks} title={taskListTitle} />
     );
}
 
export default Tasks;