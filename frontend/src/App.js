import Navbar from './components/Navbar';
import Tasks from './components/Tasks';
import TaskForm from './components/TaskForm';
import Home from './components/Home'
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
          </Routes>
        </div>
      </div>
  );
}

export default App;
