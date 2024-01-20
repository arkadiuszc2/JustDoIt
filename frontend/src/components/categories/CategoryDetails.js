import { useNavigate } from 'react-router-dom';
import { categoriesApi } from '../../api/CategoriesApi';
import { Link } from 'react-router-dom';
import './TaskDetails.css'

const CategoryDetails = (props) => {
    const category = props.categories.data[0];
    const navigate = useNavigate();

    const handleDelete = () => {
        categoriesApi.delete(category.id);
        navigate('/categories');
    }

    return (
        <div className="task-details">
            {category && (
                <article>
                    <div className="task-details-header">Name:</div>
                    <h2>{category.name}</h2>
                    <div className="task-details-header">Description:</div>
                    <p>{category.description}</p>
                    <button onClick={handleDelete}>delete</button>
                    <Link to={'/addCategory/' + category.id}>
                        <button >edit</button>
                    </Link>
                </article>)}
        </div>
    );
}

export default CategoryDetails;

