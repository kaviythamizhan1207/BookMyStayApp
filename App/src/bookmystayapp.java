/**
 * The entry point for Use Case 2 of the Hotel Booking System.
 * Demonstrates polymorphism, object initialization, and static availability state management.
 *
 * @author Kaviyathamizhan G.
 * @version 2.0
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("* Welcome to the Hotel Booking System v2.0    *");
        System.out.println("*************************************************\n");

        // 1. Polymorphism: Creating concrete objects using the Abstract reference type
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // 2. Static Availability Representation: Using simple variables (State management)
        // This highlights the limitations of hardcoded state before arrays/lists are introduced.
        int availableSingleRooms = 10;
        int availableDoubleRooms = 5;
        int availableSuiteRooms = 2;

        System.out.println("--- Room Types and Details ---");
        singleRoom.displayDetails();
        doubleRoom.displayDetails();
        suiteRoom.displayDetails();

        System.out.println("\n--- Current Static Availability ---");
        System.out.println(singleRoom.getRoomType() + " Availability: " + availableSingleRooms);
        System.out.println(doubleRoom.getRoomType() + " Availability: " + availableDoubleRooms);
        System.out.println(suiteRoom.getRoomType() + " Availability: " + availableSuiteRooms);

        System.out.println("\nSystem operation complete.");
    }
}