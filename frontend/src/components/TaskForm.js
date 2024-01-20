import { tasksApi } from '../api/TasksApi';
import './TaskForm.css'
import { useState } from 'react';

const TaskForm = () => {
    const [title, setTitle] = useState('title');
    const [description, setDescription] = useState('');
    const [priority, setPriority] = useState('HIGH');
    const [status, setStatus] = useState('TODO');
    const [categoryName, setCategory] = useState('');
    const [isPending, setIsPending] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        const task = {title, description, priority, status, categoryName};
        tasksApi.create(task).then(setIsPending(true));
    }

    return (  
        <div className="TaskForm">
            <h2>Add a new task</h2>
            <form onSubmit={handleSubmit}>
                <label>Title: </label>
                <input
                    type="text"
                    required
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <label>Description: </label>
                <textarea
                    required
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <label>Priority: </label>
                <select
                    value={priority}
                    onChange={(e) => setPriority(e.target.value)}
                >
                    <option value="HIGH">HIGH</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="LOW">LOW</option>
                </select>
                <label>Status: </label>
                <select
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                >
                    <option value="TODO">TODO</option>
                    <option value="DONE">DONE</option>
                </select>
                <label>Category: </label>
                <input
                    type="text"
                    required
                    value={categoryName}
                    onChange={(e) => setCategory(e.target.value)}

                />
                {!isPending && <button>Add task</button> }
                {isPending && <button disabled>Saved</button> }
            </form>
        </div>
    );
}
 
export default TaskForm;