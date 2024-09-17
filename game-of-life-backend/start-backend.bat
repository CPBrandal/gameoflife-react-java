@echo off
cd /d "%~dp0"
"C:\Program Files\Java\jdk-22\bin\java.exe" -cp build/libs/*.jar com.example.game_of_life_backend.GameOfLifeBackendApplication
