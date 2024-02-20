import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DeliveryScheduler {

    // Constants
    private static final double AVERAGE_SPEED_KM_PER_HR = 20.0;

    // Geo-locations represented as latitude and longitude
    private static final Map<String, double[]> locations = new HashMap<>();

    static {
        // Initialize locations
        locations.put("Aman", new double[]{12.9305, 77.6245}); // Aman's location
        locations.put("C1", new double[]{12.9279, 77.6271}); // Consumer 1
        locations.put("C2", new double[]{12.9231, 77.6209}); // Consumer 2
        locations.put("R1", new double[]{12.9316, 77.6221}); // Restaurant for C1
        locations.put("R2", new double[]{12.9369, 77.6149}); // Restaurant for C2
    }

    // Method to calculate distance between two points using Haversine formula
    private static double calculateDistance(double[] loc1, double[] loc2) {
        double lat1 = loc1[0];
        double lon1 = loc1[1];
        double lat2 = loc2[0];
        double lon2 = loc2[1];

        // Haversine formula
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

    // Method to calculate time required to travel between two points
    private static double calculateTravelTime(double distance) {
        return distance / AVERAGE_SPEED_KM_PER_HR;
    }

    // Method to find the shortest path using Dijkstra's algorithm
    private static double dijkstra(String start, String end) {
        Map<String, Double> minDistances = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>((l1, l2) -> {
            double dist1 = minDistances.getOrDefault(l1, Double.MAX_VALUE);
            double dist2 = minDistances.getOrDefault(l2, Double.MAX_VALUE);
            return Double.compare(dist1, dist2);
        });

        minDistances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            double[] currentLocation = locations.get(current);
            if (current.equals(end)) {
                return minDistances.get(end);
            }
            for (String neighbor : locations.keySet()) {
                if (!neighbor.equals(current)) {
                    double[] neighborLocation = locations.get(neighbor);
                    double distance = calculateDistance(currentLocation, neighborLocation);
                    double totalTime = minDistances.get(current) + calculateTravelTime(distance);
                    if (minDistances.getOrDefault(neighbor, Double.MAX_VALUE) > totalTime) {
                        minDistances.put(neighbor, totalTime);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return Double.MAX_VALUE;
    }

    // Method to calculate total time for delivery
    public static double calculateTotalTime() {
        double totalTime = 0.0;

        // Calculate shortest paths for both orders
        double timeToR1 = dijkstra("Aman", "R1");
        double timeToR2 = dijkstra("Aman", "R2");
        double timeFromR1ToC1 = dijkstra("R1", "C1");
        double timeFromR2ToC2 = dijkstra("R2", "C2");

        // Calculate preparation time at restaurants
        double preparationTimeAtR1 = 15; // Example preparation time for restaurant 1 (pt1)
        double preparationTimeAtR2 = 20; // Example preparation time for restaurant 2 (pt2)

        // Calculate total time
        totalTime = timeToR1 + preparationTimeAtR1 + timeFromR1ToC1 +
                timeToR2 + preparationTimeAtR2 + timeFromR2ToC2;

        return totalTime;
    }

    public static void main(String[] args) {
        double totalTime = calculateTotalTime();
        System.out.println("Total time for delivery: " + totalTime + " hours");
    }
}
