import Navbar from './components/Navbar';
import Tasks from './components/Tasks';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Tasks />} />
          </Routes>
        </div>
      </div>
  );
}

export default App;
