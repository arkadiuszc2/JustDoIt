import { useNavigate } from 'react-router-dom';
import { tasksApi } from '../../api/TasksApi';
import { Link } from 'react-router-dom';
import './TaskDetails.css'

const TaskDetails = (props) => {
    const task = props.tasks.data[0];
    const navigate = useNavigate();

    const handleDelete = () => {
        tasksApi.delete(task.id);
        navigate('/tasks');
    }

    return (
        <div className="task-details">
            {task && (
                <article>
                    <div className="task-details-header">Title:</div>
                    <h2>{task.title}</h2>
                    <div className="task-details-header">Description:</div>
                    <p>{task.description}</p>
                    <div className="task-details-header">Priority:</div>
                    <p>{task.priority}</p>
                    <div className="task-details-header">Status:</div>
                    <p>{task.status}</p>
                    <div className="task-details-header">Category:</div>
                    <p>{task.categoryName}</p>
                    <button onClick={handleDelete}>delete</button>
                    <Link to={'/addTask/' + task.id}>
                        <button >edit</button>
                    </Link>
                </article>)}
        </div>
    );
}

export default TaskDetails;

