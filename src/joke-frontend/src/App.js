import './App.css';
import {Route, Routes} from "react-router-dom";
import Joke from "./Joke";
import JokeOfTheDay from "./JokeOfTheDay";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Joke/>}/>
            <Route path="/day" element={<JokeOfTheDay/>}/>
        </Routes>
    );
}

export default App;
