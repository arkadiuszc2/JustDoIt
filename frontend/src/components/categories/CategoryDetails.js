import { useNavigate } from 'react-router-dom';
import { categoriesApi } from '../../api/CategoriesApi';
import { Link } from 'react-router-dom';
import './TaskDetails.css'
import { useAuth } from 'react-oidc-context';

const CategoryDetails = (props) => {
    const auth = useAuth()
    const accessToken = auth.user.access_token
    const category = props.categories;

    const navigate = useNavigate();

    const handleDelete = () => {
        categoriesApi.delete(category.id, accessToken);
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

