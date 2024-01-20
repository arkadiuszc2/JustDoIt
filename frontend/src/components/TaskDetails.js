import './TaskDetails.css'

const TaskDetails = (props) => {
    const task = props.tasks.data[0];

    return (
    <div className="task-details">
        { task && (
        <article>
            <h2>Title: {task.title}</h2>
            <p>Description: {task.description}</p>
            <p>Priority: {task.priority}</p>
            <p>Status: {task.status}</p>
            <p>Category: {task.categoryName}</p>
        </article>)}
    </div>
    );
}

export default TaskDetails;

