const TaskList = (props) => {
    const tasks = props.tasks;
    const title = props.title;

    return ( 
        <div className="task-list">
            <h1>{title}</h1>
            {tasks.map((task) => (
                <div className="task-preview" key={task.id}>
                    <h2>{ task.title }</h2>
                    <p>{ task.category }</p>
                </div>
            ))}
        </div>
     );
}
 
export default TaskList;