Delivery Scheduler
The DeliveryScheduler Java class represents a program that schedules the delivery of two orders by a delivery executive named Aman. It calculates the total time required to deliver both orders, taking into account travel time, preparation time at restaurants, and travel time from restaurants to consumers.

Features
Geo-locations: The program utilizes latitude and longitude coordinates to represent various locations, including Aman's location, two consumers' locations (C1 and C2), and two restaurants' locations (R1 and R2).

Haversine Formula: The calculateDistance method calculates the distance between two points using the Haversine formula, which is suitable for calculating distances between two points on the Earth's surface given their latitude and longitude.

Travel Time Calculation: The calculateTravelTime method calculates the time required to travel between two points based on the distance and an average speed constant.

Dijkstra's Algorithm: The dijkstra method implements Dijkstra's algorithm to find the shortest path between two locations in terms of time. It considers the distances between locations and optimizes the route based on travel time.

Total Time Calculation: The calculateTotalTime method utilizes Dijkstra's algorithm to find the shortest paths for both orders, calculates the total time required for delivery, and includes the preparation time at the restaurants.

Usage
To use the program, compile the DeliveryScheduler.java file and execute the compiled bytecode.
javac DeliveryScheduler.java
java DeliveryScheduler

The program will output the total time required for delivery in hours.

Limitations
Simplified Graph Representation: The program simplifies the graph representation by assuming a direct road between every pair of locations. This simplification may not accurately reflect real-world scenarios where road networks are complex and may involve multiple routes with different travel times.

Single-Threaded Execution: The program executes sequentially and may not be suitable for scenarios requiring concurrent processing or handling of multiple delivery executives.

Static Location Data: The program uses static location data initialized in a map, which may not be suitable for dynamic scenarios where locations change frequently.

Next Steps
Dynamic Location Data: Implement functionality to dynamically update location data based on real-time information.

Integration with External APIs: Integrate with external APIs (e.g., Google Maps API) to retrieve real-time traffic data and optimize delivery routes accordingly.

Concurrency: Enhance the program to support concurrent processing, allowing for the scheduling of multiple delivery executives and orders simultaneously.