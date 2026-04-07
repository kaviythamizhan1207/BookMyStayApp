import java.util.HashMap;
import java.util.Map;

/**
 * The entry point for Use Case 3 of the Hotel Booking System.
 * Demonstrates centralized inventory management using HashMap.
 *
 * @author Kaviyathamizhan G.
 * @version 3.1
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("* Welcome to the Hotel Booking System v3.1    *");
        System.out.println("*************************************************\n");

        // 1. Initialize the centralized inventory component
        RoomInventory inventory = new RoomInventory();

        // 2. Display the current initial inventory state
        System.out.println("System Initialization Complete. Fetching Inventory...\n");
        inventory.displayInventory();

        // 3. Simulate controlled updates (e.g., handling incoming booking requests)
        System.out.println("\n--- Simulating Room Bookings ---");

        System.out.println("Action: Booking 1 Single Room...");
        inventory.updateAvailability("Single Room", -1);

        System.out.println("Action: Booking 2 Double Rooms...");
        inventory.updateAvailability("Double Room", -2);

        System.out.println("Action: Attempting to book 3 Suite Rooms (only 2 available)...");
        inventory.updateAvailability("Suite Room", -3);

        // 4. Display the current inventory state after updates to prove consistency
        System.out.println("\n--- State After Updates ---");
        inventory.displayInventory();

        System.out.println("\nSystem operation complete.");
    }
}

/**
 * Manages and exposes room availability across the system.
 * Acts as a single source of truth using a centralized HashMap.
 * Note: Not declared 'public' so it can live in the same file as the main class.
 *
 * @author Kaviyathamizhan G.
 * @version 3.0
 */
class RoomInventory {

    // Encapsulated single source of truth for availability
    private Map<String, Integer> inventory;

    /**
     * Constructor initializes the inventory component and registers
     * room types with their initial available counts.
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        // O(1) insertion mapping room types to availability counts
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    /**
     * Retrieves the current availability for a specific room type.
     * @param roomType The type of room (e.g., "Single Room")
     * @return The number of rooms available, or 0 if the room type doesn't exist
     */
    public int getAvailability(String roomType) {
        // O(1) lookup
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates the availability of a specific room type in a controlled manner.
     * @param roomType The type of room
     * @param delta The change in availability (negative for booking, positive for cancellation)
     */
    public void updateAvailability(String roomType, int delta) {
        if (inventory.containsKey(roomType)) {
            int currentAvailability = inventory.get(roomType);
            int newAvailability = currentAvailability + delta;

            // Prevention of inconsistent state (e.g., double-booking leading to negative rooms)
            if (newAvailability >= 0) {
                inventory.put(roomType, newAvailability);
            } else {
                System.out.println("[Error] Insufficient inventory. Cannot complete update for: " + roomType);
            }
        } else {
            System.out.println("[Error] Invalid room type requested: " + roomType);
        }
    }

    /**
     * Displays the current state of the inventory.
     */
    public void displayInventory() {
        System.out.println("--- Current Centralized Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("%-15s | Available: %d%n", entry.getKey(), entry.getValue());
        }
    }
}