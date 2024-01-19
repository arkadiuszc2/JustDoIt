import './TaskList.css'
import { Link } from "react-router-dom";

const TaskList = (props) => {
    const tasks = props.tasks.data;
    const title = props.title;
    const handleDelete = props.handleDelete;

    return ( 
        <div className="task-list">
            <h1>{title}</h1>
            {tasks.map(task => (
                <div className="task-preview" key={task.id}>
                    <div className="task-preview-content">
                        <Link to={`/tasks/${task.id}`}>
                        <h2>{ task.title }</h2>
                        <p>{ task.categoryName }</p>
                        </Link>
                    </div>
                    <div className="task-preview-buttons">
                        <button onClick={() => handleDelete(task.id)}>Delete</button>
                    </div>
                </div>
            ))}
        </div>
     );
}
 
export default TaskList;