import { useState } from 'react';

const Home = () => {
    const title = 'JustDoIt';
    const description = 'Manage your tasks like a pro';
    const linkToRepo = 'https://github.com/arkadiuszc2'

    const [reaction, setReaction] = useState('Give us a like!')

    const handleClick = (e) => {
        setReaction('Thanks for liking us!');
    }

    return ( 
        <div className="home">
            <h1>Homepage</h1>
            <h2>{ title }</h2>
            <p>{ description }</p>
            <a href={ linkToRepo } >Link to app repo</a>
            <br />
            <button onClick={(e) => handleClick(e)}>Like</button>
            <p>{ reaction }</p>
        </div>
     );
}
 
export default Home;