import { useNavigate, useParams } from 'react-router-dom';
import { categoriesApi } from '../../api/CategoriesApi';
import './TaskForm.css'
import { useState, useEffect } from 'react';

const CategoryForm = () => {
    const [isPending, setIsPending] = useState(false);
    const navigate = useNavigate();

    const { categoryId } = useParams();
    const [category, setCategory] = useState({
        name: '',
        description: '',
    })

    useEffect(() => {
        if (categoryId !== 'new') {
            categoriesApi.getById(categoryId)
                .then((res) => {
                    setCategory(res.data[0])
                })
        }
    }, [categoryId])

    const handleSubmit = async (e) => {
        e.preventDefault();

            if (category.id) {
                await categoriesApi.update(category.id, category);
            } else {
                await categoriesApi.create(category);
            }

            setIsPending(true);
            navigate('/categories');

    }

    const handleChange = (event) => {
        const target = event.target
        const value = target.value
        const name = target.name

        setCategory({ ...category, [name]: value })
    }

    const pageTitle = <h2>{category.id ? 'Edit category' : 'Add category'}</h2>
    const buttonTitle = <p>{category.id ? 'update' : 'save'}</p>

    return (
        <div className="TaskForm">
            {pageTitle}
            <form onSubmit={handleSubmit}>
                <label>Name: </label>
                <input
                    type="text"
                    required
                    value={category.name || ''}
                    name='name'
                    onChange={handleChange}
                />
                <label>Description: </label>
                <textarea
                    required
                    value={category.description || ''}
                    name='description'
                    onChange={handleChange}
                />
                {!isPending && <button>{buttonTitle}</button>}
                {isPending && <button disabled>{buttonTitle}</button>}
            </form>
        </div>
    );
}

export default CategoryForm;