import Header from "./Header";
import {Container} from "react-bootstrap";

function Joke() {
    return (
        <>
            <Header/><br/>
            <Container style={{textAlign: "center"}}>
                <h1>Joke Website</h1>
                <hr/>
                <br/>
                <p>This website can tell you the</p>
                <a href={"/day"}>yoke of the day</a>
            </Container>
        </>
    );
}

export default Joke;