import './TaskList.css'
import { Link } from "react-router-dom";

const TaskList = (props) => {
    const tasks = props.tasks;
    return ( 
        <div className="task-list">
            {tasks.map(task => (
                <div className="task-preview" key={task.id}>
                    <div className="task-preview-content">
                        <Link to={`/tasks/${task.id}`}>
                        <h2>{ task.title }</h2>
                        <p>{ task.categoryName} | {task.status} | {task.priority}</p>
                        </Link>
                    </div>
                </div>
            ))}
        </div>
     );
}
 
export default TaskList;