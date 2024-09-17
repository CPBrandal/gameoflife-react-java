[![Watch the video](https://raw.githubusercontent.com/CPBrandal/gameoflife-react-java/main/assets/gameoflifePicture.jpg)](https://raw.githubusercontent.com/CPBrandal/gameoflife-react-java/main/assets/gameoflifeVideo.mp4)


This program is a learning program to see if I could use React to display my game of life program.
The program is not very fast or efficient, and is only ment as a way of learning React, and how to create my own API which the frontend can communicate with.

The program is split into two parts the backend with Java, gradle, and Spring boot,
 and the frontend with React, html, js and css.

1. To run this program you need download or clone the git repository.

2. Unzip the folder.

3. Navigate to the gameoflife folder (cd gameoflife).

4. From cmd call yarn install, which will install all the dependencies.

5. Call yarn start.
The first time you run the program it might take a while for it to run. Just wait.


The backend creates and configures an application contexts and starts a server with url and port http://localhost:8080/.
The classes are serializable so that my frontend-program can execute API calls to the backend, http://localhost:8080/api/game/state.




