import './TaskDetails.css'

const TaskDetails = (props) => {
    const tasks = props.tasks.data;

    return (
    <div className="task-details">
        { tasks && (
        <article>
            <h2>{tasks[0].title}</h2>
            <p>{tasks[0].description}</p>
        </article>)}
    </div>
    );
}

export default TaskDetails;

