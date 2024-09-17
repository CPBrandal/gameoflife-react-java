import React, { useEffect, useState, useRef } from 'react';
import './Grid.css'; 

const Grid = () => {
    const [grid, setGrid] = useState([]);
    const [gen, setGen] = useState(0);
    const [speed, setSpeed] = useState(150); // Default speed in milliseconds
    const [isRunning, setIsRunning] = useState(false); // State to track if updates are running
    const intervalId = useRef(null); // Use useRef to track the interval

    useEffect(() => {
        // Initialize the grid
        fetch('http://localhost:8080/api/game/initialize', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify([50, 50]),
        }).then(() => {
            fetchGridState();
        });

        // Clean up the interval when the component unmounts
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
        stopUpdates(); // Ensure any existing interval is cleared
        intervalId.current = setInterval(() => {
            fetchGridUpdate();
        }, intervalSpeed);
        setIsRunning(true); // Set isRunning to true when updates start
    };

    // Function to stop the interval
    const stopUpdates = () => {
        if (intervalId.current) {
            clearInterval(intervalId.current);
            intervalId.current = null; // Clear the interval ID
            setIsRunning(false); // Set isRunning to false when updates stop
        }
    };

    // Function to handle slider change
    const handleSpeedChange = (event) => {
        const newSpeed = event.target.value;
        setSpeed(newSpeed);

        // If the interval is running, restart it with the new speed
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
