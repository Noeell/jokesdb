import Header from "./Header";
import {Col, Container, Row} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import Loading from "./Loading";
import {Button, Rating} from "@mui/material";
import CachedIcon from '@mui/icons-material/Cached';

function JokeOfTheDay() {
    const [joke, setJoke] = useState();
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null);
    const [buttonPressed, setButtonPressed] = useState(false);
    const [rating, setRating] = useState(null);

    useEffect(() => {
        loadJoke();
    }, [])

    function loadJoke() {
        fetch(`http://localhost:8080/randomjoke`, {
            "headers": {
                "Accept": "application/json",
            }
        }).then(response => {
            if (!response.ok) {
                throw Error('could not fetch the data for that resource');
            }
            return response.json();
        })
            .then(data => {
                setIsPending(false);
                loadRating(data.id);
                setJoke(data);
                setError(null);
            }).catch(err => {
            setIsPending(false);
            setError(err.message);
        })
    }

    function loadRating(jokesId) {
        setRating(null);
        fetch(`http://localhost:8080/loadrating/${jokesId}`, {
            "headers": {
                "Accept": "application/json",
            }
        }).then(response => {
            if (!response.ok) {
                throw Error('could not fetch the data for that resource');
            }
            return response.json();
        })
            .then(data => {
                if (data !== 10) {
                    setRating(data);
                }
                setError(null);
            }).catch(err => {
            setError(err.message);
        })
    }

    function saveRating(newRating) {
        const requestOptions = {
            method: 'POST'
        };
        fetch(`http://localhost:8080/saverating/${newRating}/${joke.id}`, requestOptions)
            .catch(err => {
                setIsPending(false);
                setError(err.message);
            })
        setRating(newRating);
    }

    return (
        <>
            <Header/><br/>
            <Container style={{textAlign: "center"}}>
                <h1>Joke of the Day</h1>
                <hr/>
                <br/>
                {isPending && <Loading/>}
                {error && <p>{error}</p>}
                <h4>{joke?.joke}</h4>
                <h4>{joke?.setup}</h4>
                {joke?.setup && <div>
                    <br/>
                    <br/>
                    {buttonPressed && <p>{joke?.delivery}</p>}
                    {!buttonPressed &&
                        <Button variant="outlined" onClick={() => setButtonPressed(true)}>Show more</Button>
                    }
                </div>}
                <br/>
                <Button variant="contained" endIcon={<CachedIcon/>} onClick={() => {
                    loadJoke();
                    setButtonPressed(false);
                }}>
                    Load New Joke
                </Button>
                <br/><br/>
                <h4>Rating:</h4>
                <hr/>
                <Rating
                    name="simple-controlled"
                    value={rating}
                    onChange={(event, newValue) => {
                        saveRating(newValue)
                    }}
                />
                <br/><br/>
                <h4>Flags:</h4>
                <hr/>
                <Row>
                    <Col>
                        <p>Nsfw: {String(joke?.nsfw)}</p>
                    </Col>
                    <Col>
                        <p>Religious: {String(joke?.religious)}</p>
                    </Col>
                    <Col>
                        <p>Political: {String(joke?.political)}</p>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <p>Racist: {String(joke?.racist)}</p>
                    </Col>
                    <Col>
                        <p>Sexist: {String(joke?.sexist)}</p>
                    </Col>
                    <Col>
                        <p>Explicit: {String(joke?.explicit)}</p>
                    </Col>
                </Row>
            </Container>
        </>
    );
}

export default JokeOfTheDay;