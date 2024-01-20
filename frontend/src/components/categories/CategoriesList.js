import './TaskList.css'
import { Link } from "react-router-dom";

const CategoriesList = (props) => {
    const categories = props.categories.data;

    return ( 
        <div className="task-list">
            {categories.map(category => (
                <div className="task-preview" key={category.id}>
                    <div className="task-preview-content">
                        <Link to={`/categories/${category.id}`}>
                        <h2>{ category.name }</h2>
                        <p>{ category.description }</p>
                        </Link>
                    </div>
                </div>
            ))}
        </div>
     );
}
 
export default CategoriesList;