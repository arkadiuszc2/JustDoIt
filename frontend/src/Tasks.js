import { useState } from "react";

const Notes = () => {
    const [notes, setNotes] = useState([
        { title: 'Workouts', content: 'Pushups 10x3, Pullups 10x3, Squats 10x3', category: 'Sport', id: 1},
        { title: 'Create new endpoint', content: 'Finish the project', category: 'Work', id: 2},
        { title: 'Do homework', content: 'Do 5 math exercises', category: 'School', id: 3}
    ])

    return ( 
        <div className="tasks">
            {notes.map((note) => (
                <div className="task-preview" key={note.id}>
                    <h2>{ note.title }</h2>
                    <p>{ note.category }</p>
                </div>
            ))}
        </div>
     );
}
 
export default Notes;