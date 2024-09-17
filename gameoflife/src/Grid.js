import React, { useEffect, useState, useRef } from 'react';
import './Grid.css'; 

const Grid = () => {
    const [grid, setGrid] = useState([]);
    const [gen, setGen] = useState(0);
    const [speed, setSpeed] = useState(150);
    const [isRunning, setIsRunning] = useState(false);
    const intervalId = useRef(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/game/initialize', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify([50, 50]),
        }).then(() => {
            fetchGridState();
        });

        return () => clearInterval(intervalId.current);
    }, []);

    const fetchGridState = () => {
        fetch('http://localhost:8080/api/game/state')
            .then(response => response.json())
            .then(data => {
                const gridData = data.rutenett.rutene;
                setGrid(gridData);
            })
            .catch(error => console.error('Error fetching grid state:', error));
    };

    const fetchGridUpdate = () => {
        fetch('http://localhost:8080/api/game/update')
            .then(response => response.json())
            .then(data => {
                const gridData = data.rutenett.rutene;
                setGrid(gridData);

                const gen = data.genNr;
                setGen(gen);
            })
            .catch(error => console.error('Error fetching grid update:', error));
    };

    // Function to start the interval
    const startUpdates = (intervalSpeed) => {
        stopUpdates();
        intervalId.current = setInterval(() => {
            fetchGridUpdate();
        }, intervalSpeed);
        setIsRunning(true);
    };

    // Function to stop the interval
    const stopUpdates = () => {
        if (intervalId.current) {
            clearInterval(intervalId.current);
            intervalId.current = null;
            setIsRunning(false); 
        }
    };

    // Function to handle slider change
    const handleSpeedChange = (event) => {
        const newSpeed = event.target.value;
        setSpeed(newSpeed);

        if (isRunning) {
            startUpdates(newSpeed);
        }
    };

    const squares = grid.flat().map((cell, index) => (
        <div
            key={index}
            className={`square ${cell.levende ? 'black' : 'white'}`}
        ></div>
    ));

    return (
        <div>
            <div className="horizontal-container">
                <p>Generation number: {gen}</p>
                <button onClick={stopUpdates} disabled={!isRunning}>Stop Updates</button>
                <button onClick={() => startUpdates(speed)} disabled={isRunning}>Start Updates</button>
                
                <p>Speed: {speed} ms</p>
                    <input 
                        type="range" 
                        id="speedSlider" 
                        min="50" 
                        max="1000" 
                        value={speed} 
                        onChange={handleSpeedChange} 
                    />
            </div>
            <div className="grid-container">
                {squares}
            </div>
        </div>
    );
};

export default Grid;
