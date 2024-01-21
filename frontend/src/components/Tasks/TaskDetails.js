import { useNavigate } from 'react-router-dom';
import { tasksApi } from '../../api/TasksApi';
import { Link } from 'react-router-dom';
import './TaskDetails.css'
import { useAuth } from 'react-oidc-context';

const TaskDetails = (props) => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const task = props.tasks;
    //console.log(task);
    const navigate = useNavigate();

    const handleDelete = () => {
        tasksApi.delete(task.id, accessToken);
        navigate('/');
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
                    <div className="task-details-header">Created by:</div>
                    <p>{task.createdBy}</p>
                    <button onClick={handleDelete}>delete</button>
                    <Link to={'/addTask/' + task.id}>
                        <button >edit</button>
                    </Link>
                </article>)}
        </div>
    );
}

export default TaskDetails;

