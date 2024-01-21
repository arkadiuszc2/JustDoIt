import { useNavigate, useParams } from 'react-router-dom';
import { tasksApi } from '../../api/TasksApi';
import './TaskForm.css'
import { useState, useEffect } from 'react';
import { useAuth } from 'react-oidc-context';

const TaskForm = () => {
    const [isPending, setIsPending] = useState(false);
    const navigate = useNavigate();
    const auth = useAuth()
    const accessToken = auth.user.access_token

    const { taskId } = useParams();
    const [task, setTask] = useState({
        title: '',
        description: '',
        priority: 'HIGH',
        status: 'TODO',
        categoryName: ''
    })

    useEffect(() => {
        if (taskId !== 'new') {
            tasksApi.getById(taskId, accessToken)
                .then((res) => {
                    setTask(res.data[0])
                })
        }
    }, [taskId])

    const handleSubmit = async (e) => {
        e.preventDefault();

            if (task.id) {
                await tasksApi.update(task.id, task, accessToken);
            } else {
                await tasksApi.create(task, accessToken);
            }

            setIsPending(true);
            navigate('/tasks');

    }

    const handleChange = (event) => {
        const target = event.target
        const value = target.value
        const name = target.name

        setTask({ ...task, [name]: value })
    }

    const pageTitle = <h2>{task.id ? 'Edit task' : 'Add task'}</h2>
    const buttonTitle = <p>{task.id ? 'update' : 'save'}</p>

    return (
        <div className="TaskForm">
            {pageTitle}
            <form onSubmit={handleSubmit}>
                <label>Title: </label>
                <input
                    type="text"
                    required
                    value={task.title || ''}
                    name='title'
                    onChange={handleChange}
                />
                <label>Description: </label>
                <textarea
                    required
                    value={task.description || ''}
                    name='description'
                    onChange={handleChange}
                />
                <label>Priority: </label>
                <select
                    value={task.priority}
                    name='priority'
                    onChange={handleChange}
                >
                    <option value="HIGH">HIGH</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="LOW">LOW</option>
                </select>
                <label>Status: </label>
                <select
                    value={task.status}
                    name='status'
                    onChange={handleChange}
                >
                    <option value="TODO">TODO</option>
                    <option value="DONE">DONE</option>
                </select>
                <label>Category: </label>
                <input
                    type="text"
                    required
                    value={task.categoryName}
                    name='categoryName'
                    onChange={handleChange}

                />
                {!isPending && <button>{buttonTitle}</button>}
                {isPending && <button disabled>{buttonTitle}</button>}
            </form>
        </div>
    );
}

export default TaskForm;