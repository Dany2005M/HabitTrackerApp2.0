Java Habit Tracker

A desktop application built with Java Swing for tracking daily habits. This project demonstrates a clean architecture (UI separated from logic), persistent database storage with JDBC, and professional project management using Maven and JUnit.

<!--
INSTRUCTIONS:

Take a screenshot of your application running.

Save it as "https://www.google.com/search?q=habittracker-gui.png" in the root folder of this project.

Replace the line below with:
-->

(Screenshot placeholder)

Features

    Create & Remove Habits: Easily add new habits to track or remove old ones.

    Daily Tracking: Mark habits as "Done" each day with a simple click.

    Streak Counter: Automatically tracks your consecutive day streak for each habit.

    Weekly Summaries: View a periodic summary of your completion percentage and longest streaks.

    Persistent Storage: All habits, streaks, and progress are saved to a local SQLite database, so your data is never lost between sessions.

Tech Stack

This project showcases a range of professional-grade tools and practices:

    Language: Java 17

    UI: Java Swing (for the desktop GUI)

    Build & Dependency Management: Apache Maven is used to manage the project lifecycle and dependencies.

    Database: SQLite with the JDBC API for robust, persistent data storage.

    Testing: JUnit 5 is used for comprehensive unit and integration testing of the core application logic and database interactions.

How to Run

    Prerequisites

    Java JDK 17 or newer must be installed on your system.

Option 1: Run the Executable JAR

        This is the simplest way to run the application.




Option 2: Build from Source

        You can build the project from the source code using Maven.

        Clone this repository:

        git clone [https://github.com/Dany2005M/HabitTrackerApp2.0.git](https://github.com/Dany2005M/HabitTrackerApp2.0.git)


        Navigate into the project directory:

        cd HabitTrackerApp2.0


        Build the project and run all tests using Maven:

        mvn package


        Once the build is complete, run the newly created JAR file from the target directory:

        java -jar target/HabitTrackerApp-1.0-SNAPSHOT.jar
