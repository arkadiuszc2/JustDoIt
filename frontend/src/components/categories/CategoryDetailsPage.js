import CategoryDetails from "./CategoryDetails";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";
import { categoriesApi } from "../../api/CategoriesApi";
import { useState, useEffect } from "react";
import { useAuth } from "react-oidc-context";


const CategoryDetailsPage = () => {
    const auth = useAuth()
    const accessToken = auth.user.access_token

    const { id } = useParams();
    const [ category, setCategory ] = useState([]);

    useEffect(() => {
        categoriesApi.getById(id, accessToken)
            .then((res) => {
                setCategory(res.data[0])
            })
    },[ id, accessToken])

    return (
        <div className="TaskDetailsPage">
            {category && <CategoryDetails categories={category} />}
        </div>);
}

export default CategoryDetailsPage;