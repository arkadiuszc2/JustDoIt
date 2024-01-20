// import TaskList from "./TaskList";
// import { tasksApi } from "../../api/TasksApi";
// import './Tasks.css'
// import useFetch from "../../custom-hooks/useFetch";
// import { Link } from "react-router-dom";

// const Categories = () => {
//     const { data: categories, isPending, error } = useFetch(categoriesApi.getAll);


//     return (
//         <div className="tasks">
//             <div className="tasks-add-link">
//             <Link to={"/addTask/new"}>
//                 <button> Add category</button>
//             </Link>
//             </div>
//             {error && <div className="taskError">{error.message}</div>}
//             {isPending && <div className="loading"><p>Loading...</p></div>}
//             {tasks && <TaskList tasks={tasks} />}
//         </div>
//     );

// }

// export default Tasks;