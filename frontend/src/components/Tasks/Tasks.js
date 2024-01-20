import TaskList from "./TaskList";
import { tasksApi } from "../../api/TasksApi";
import './Tasks.css'
import useFetch from "../../custom-hooks/useFetch";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

const Tasks = () => {
    //const { data: tasks, isPending, error } = useFetch(tasksApi.getAll);
    const [categoryName, setCategoryName] = useState('');
    const [sortBy, setSortBy] = useState('');
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        tasksApi.getAll()
            .then((res) => {
                setTasks(res.data)
            })
            .catch(err => console.error('[Fetch Error]:', err))
    }, [])


    const handleSubmit = async (e) => {
        e.preventDefault();
        tasksApi.getByCategoryNameAndSort(categoryName, sortBy)
        .then((res) => {
            setTasks(res.data)
        }).catch(err => console.error('[Fetch Error]:', err))
    };


    return (
        <div className="tasks">
            <div className='search-menu'>
                <form onSubmit={handleSubmit}>
                    <label>Category name:</label>
                    <input
                        type="text"
                        value={categoryName}
                        onChange={(e) => setCategoryName(e.target.value)}
                    />
                    <br />
                    <div className="radio-group">
                        <label> Sort by:</label>
                        <input
                            type="radio"
                            value="priority"
                            checked={sortBy === 'priority'}
                            onChange={() => setSortBy('priority')}
                        />
                        Priority
                        <input
                            type="radio"
                            value="status"
                            checked={sortBy === 'status'}
                            onChange={() => setSortBy('status')}
                        />
                        Status
                    </div>
                    <button type="submit">Filter and sort</button>
                </form>
            </div>
            
            <div className="task-add-link">
            <Link to={"/addTask/new"}>
                <button> Add Task</button>
            </Link>
            </div>

            {tasks && <TaskList tasks={tasks} />}
        </div>
    );

}

export default Tasks;