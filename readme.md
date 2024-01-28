[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10534042&assignment_repo_type=AssignmentRepo)
# Project eCourse


## 1. Description of the Project

This application is part of the lab project for the course unit of LAPR4/EAPLI/SCOMP/RCOMP/LPROG. 

Parts of the application were developed to show specific approaches or techniques; 
as such, the overall application is not consistent in terms of design. 
for production ready code this would obviously be a problem as we should strive for consistency. 
In this case, it is acceptable as the inconsistencies are meant to provide samples of different valid approaches.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/readme.md)

## 3. How to Build

Make sure Maven is installed and on the PATH.

The java source is Java 1.8+ so any JDK 1.8 or later will work. However, in order to generate the javadoc and UML diagrams the JDK version must be *strictly 1.8*.

run script

    rebuild-all.bat


## 4. How to Run

make sure a JRE is installed and on the PATH

first run bootstrap if needed

    run-bootstrap.bat

run script

    run-backoffice.bat

or

    run-user.bat

## 5. How to download the repository

first cd into the appropriate location using

    cd "path of repository"

finally clone the project using the command
    
    git clone https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-22-23-6.git


## 6. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh


