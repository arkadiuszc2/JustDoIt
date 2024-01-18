import './Navbar.css'
import { Link } from 'react-router-dom'

const Navbar = () => {
    return ( 
        <nav className="navbar">
            <h1>JustDoIt</h1>
            <div className="links">
                <Link to="/home">Home</Link>  
                <Link to="/tasks">Tasks</Link>
                <Link to="/addTask">New Task</Link>
            </div>
        </nav>
     );
}
 
export default Navbar;