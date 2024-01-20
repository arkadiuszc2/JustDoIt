import CategoryDetails from "./CategoryDetails";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";
import { categoriesApi } from "../../api/CategoriesApi";


const CategoryDetailsPage = () => {
    const { id } = useParams();
    const {data: categories} = useFetch(categoriesApi.getById, id);
    return (
    <div className="TaskDetailsPage">
        {categories && <CategoryDetails categories={categories} />}
    </div>  );
}
 
export default CategoryDetailsPage;