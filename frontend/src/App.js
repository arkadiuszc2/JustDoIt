import Navbar from './components/Navbar';
import Tasks from './components/Tasks';

function App() {
  return (
    <div className="App">
      <Navbar />
      <div className="content">
      <Tasks />
      </div>
    </div>
  );
}

export default App;
