import Navbar from './components/Navbar';
import Tasks from './components/Tasks';
import Home from './components/Home';
import TaskForm from './components/TaskForm';
import TaskDetails from './components/TaskDetails';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/tasks" element={<Tasks />} />
            <Route path="/addTask" element={<TaskForm />} />
            <Route path="/tasks/:id" element={<TaskDetails />} />
          </Routes>
        </div>
      </div>
  );
}

export default App;
