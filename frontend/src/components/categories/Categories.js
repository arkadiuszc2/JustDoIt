import CategoriesList from "./CategoriesList";
import './Categories.css'
import useFetch from "../../custom-hooks/useFetch";
import { Link } from "react-router-dom";
import { categoriesApi } from "../../api/CategoriesApi";

const Categories = () => {
    const { data: categories, isPending, error } = useFetch(categoriesApi.getAll);


    return (
        <div className="tasks">
            <div className="tasks-add-link">
            <Link to={"/addCategory/new"}>
                <button> Add category</button>
            </Link>
            </div>
            {error && <div className="taskError">{error.message}</div>}
            {isPending && <div className="loading"><p>Loading...</p></div>}
            {categories && <CategoriesList categories={categories} />}
        </div>
    );

}

export default Categories;