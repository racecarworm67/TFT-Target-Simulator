# TFT AI Target & Task Simulator

This is a Java project that uses a `PriorityQueue` to manage how AI units select targets on a battlefield, demonstrating a core game-engine concept.

This project re-skins a university assignment (an ER patient queue) into a Teamfight Tactics AI simulator.

## What It Does

This app simulates a training ground with a limited number of **`Target Dummies`** (resources). When a new **`ChampionAttacker`** (an event) enters, the system follows a clear logic:

1.  The champion attempts to attack a free `Target Dummy`.
2.  If all dummies are busy, the champion is added to a `PriorityQueue` of waiting attackers.
3.  The `PriorityQueue` automatically sorts all waiting champions based on a custom-defined order:
    * **1. By Priority:** A high-priority unit (like a 5-cost carry) is sorted to the front.
    * **2. By Timestamp:** If two units have the same priority, the one that arrived first goes first.
4.  When a dummy is "defeated" (a resource becomes free), the system automatically pulls the highest-priority champion from the queue and assigns them to that dummy.

This is a direct, working prototype of an **AI task scheduler** or an **event handling system** in a game engine.

## Core Concepts Demonstrated

* **`PriorityQueue` Data Structure:** The core of the project. Used to manage a "to-do" list of tasks that are automatically sorted by importance.
* **`Comparable` Interface:** Implementing the `compareTo` method in the `ChampionAttacker` class to define the custom sorting logic (Priority > Timestamp).
* **Resource Management:** Managing a fixed-size array (`activeDummies[]`) to represent a limited pool of resources (the dummies).
* **Event-Driven Programming:** Using a `switch` statement to simulate different game events ("New Champion Enters," "Dummy Defeated").
* **Object-Oriented Programming (OOP):** Encapsulating all champion data (name, priority, timestamp) into a clean `ChampionAttacker` object.

## How to Run It

1.  Clone the repository.
2.  Open the project in an IDE (like IntelliJ).
3.  Run the `main()` method inside the **`TrainingGrounds.java`** file.
4.  Follow the interactive prompts in the console to add champions and see the queue in action.
