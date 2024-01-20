import Navbar from './components/Navbar';
import Tasks from './components/Tasks/Tasks';
import Home from './components/Home';
import TaskForm from './components/Tasks/TaskForm';
import TaskDetailsPage from './components/Tasks/TaskDetailsPage';
import CategoryDetailsPage from './components/categories/CategoryDetailsPage';
import CategoryForm from './components/categories/CategoryForm';
import { Route, Routes } from 'react-router-dom';
import NotFoundPage from './components/NotFoundPage';
import Categories from './components/categories/Categories';

function App() {
  return (
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/tasks" element={<Tasks />} />
            <Route path="/categories" element={<Categories />} />
            <Route path="/addTask/:taskId" element={<TaskForm />} />
            <Route path="/addCategory/:categoryId" element={<CategoryForm />} />
            <Route path="/tasks/:id" element={<TaskDetailsPage />} />
            <Route path="/categories/:id" element={<CategoryDetailsPage />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        </div>
      </div>
  );
}

export default App;
