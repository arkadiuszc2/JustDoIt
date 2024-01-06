const Home = () => {
    const title = 'JustDoIt';
    const description = 'Manage your tasks like a pro';
    const linkToRepo = 'https://github.com/arkadiuszc2'

    return ( 
        <div className="home">
            <h1>Homepage</h1>
            <h2>{ title }</h2>
            <p>{ description }</p>
            <a href={ linkToRepo } >Link to app repo</a>
        </div>
     );
}
 
export default Home;