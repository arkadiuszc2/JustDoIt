import './Navbar.css'
import { Link } from 'react-router-dom'
import { useAuth } from 'react-oidc-context'

const Navbar = () => {
    const auth = useAuth()

    const handleLogIn = () => {
        auth.signinRedirect()
    }

    const handleLogOut = () => {
        auth.signoutSilent()
    }

    return (
        <nav className="navbar">
            <h1>JustDoIt</h1>
            <div className="links">
                <Link to="/">Home</Link>
                <Link to="/tasks">Tasks</Link>
                <Link to="/categories">Categories</Link>
                {!auth.isAuthenticated && (
                    <button onClick={handleLogIn}>
                        Login
                    </button>
                )}
                {auth.isAuthenticated && (
                    <Link to='/' onClick={handleLogOut} >
                        Logout
                    </Link>
                )}
            </div>
        </nav>
    );
}

export default Navbar;