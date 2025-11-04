import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Random;

public class TrainingGrounds {

        private static final int NUM_DUMMIES = 3;
        private static ChampionAttacker[] activeDummies = new ChampionAttacker[NUM_DUMMIES];
        private static PriorityQueue<ChampionAttacker> waitingChampions = new PriorityQueue<>();


        private static int timestampCounter = 1;
        private static Scanner scanner = new Scanner(System.in);
        private static Random random = new Random();

        public static void main(String[] args) {
            // Initialize all dummies to 'not being attacked' (null)
            for (int i = 0; i < NUM_DUMMIES; i++) {
                activeDummies[i] = null;
            }

            int choice = 0;
            while (choice != 4) {
                displayMenu();
                try {
                    choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            addNewChampion();
                            break;
                        case 2:
                            processDummyFreed();
                            break;
                        case 3:
                            displayWaitingChampions();
                            break;
                        case 4:
                            System.out.println("Exiting Training Simulator. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
                System.out.println();
            }
            scanner.close();
        }

        private static void displayMenu() {
            System.out.println("TFT TRAINING GROUNDS SIMULATOR");
            System.out.println("--------------------------------");
            System.out.println("1. Add New Champion to Fight");
            System.out.println("2. Dummy Defeated (Frees up)");
            System.out.println("3. View Waiting Champions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
        }

        private static void addNewChampion() {
            System.out.println("\nNEW CHAMPION ENTERING");
            System.out.println("---------------------");

            System.out.print("Enter Champion Name (e.g., Kog'Maw): ");
            String championName = scanner.nextLine();

            System.out.print("Enter Trait (e.g., Sniper): ");
            String championTrait = scanner.nextLine();

            int priority = getChampionPriority();

            ChampionAttacker newAttacker = new ChampionAttacker(championName, championTrait, priority, timestampCounter++);

            // Try to assign champion to a free dummy
            boolean dummyFound = false;
            for (int i = 0; i < NUM_DUMMIES; i++) {
                if (activeDummies[i] == null) {
                    activeDummies[i] = newAttacker;
                    System.out.println(newAttacker + " is now attacking Dummy " + (i + 1));
                    dummyFound = true;
                    break;
                }
            }

            // If no dummy is free, add to the waiting queue
            if (!dummyFound) {
                waitingChampions.add(newAttacker);
                System.out.println(newAttacker + " has been added to the waiting queue.");
            }

            displayDummyStatus();
        }


        private static int getChampionPriority() {
            int priority = 0;
            boolean validPriority = false;

            while (!validPriority) {
                System.out.println("\nSelect Champion Role (Priority):");
                System.out.println("2. Support (e.g., Milio)");
                System.out.println("3. Tank (e.g., Ornn)");
                System.out.println("4. Bruiser (e.g., Udyr)");
                System.out.println("5. Mage (e.g., Ahri)");
                System.out.println("6. ADC / Ranged Carry (e.g., Kog'Maw)");
                System.out.println("7. Melee Carry (e.g., Yone)");
                System.out.println("8. High-Threat (e.g., Kayn)");
                System.out.println("9. Area of Effect (e.g., Hwei)");
                System.out.println("10. 5-Cost Unit (Highest Priority)");

                System.out.print("\nEnter priority number (2-10): ");
                try {
                    priority = Integer.parseInt(scanner.nextLine());
                    if (priority >= 2 && priority <= 10) {
                        validPriority = true;
                    } else {
                        System.out.println("Invalid priority. Please select from 2-10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
            return priority;
        }


        private static void processDummyFreed() {
            int dummyNumber = random.nextInt(NUM_DUMMIES) + 1; // 1-3
            int dummyIndex = dummyNumber - 1; // 0-2

            System.out.println("\nDummy " + dummyNumber + " has been defeated!");

            if (activeDummies[dummyIndex] != null) {
                System.out.println(activeDummies[dummyIndex] + " has defeated Dummy " + dummyNumber);
                activeDummies[dummyIndex] = null;
            } else {
                System.out.println("Dummy " + dummyNumber + " was already free.");
            }

            // Check if champions are waiting in the queue
            if (!waitingChampions.isEmpty()) {
                ChampionAttacker nextAttacker = waitingChampions.poll();
                activeDummies[dummyIndex] = nextAttacker;
                System.out.println(nextAttacker + " is now attacking Dummy " + dummyNumber);
            }

            displayDummyStatus();
        }

        private static void displayWaitingChampions() {
            System.out.println("\nCURRENT CHAMPION QUEUE (WAITING FOR DUMMY)");
            System.out.println("------------------------------------------");

            if (waitingChampions.isEmpty()) {
                System.out.println("The queue is empty.");
            } else {
                PriorityQueue<ChampionAttacker> tempQueue = new PriorityQueue<>(waitingChampions);
                int position = 1;

                System.out.println("Position | Champion Information");
                System.out.println("-----------------------------------");

                while (!tempQueue.isEmpty()) {
                    ChampionAttacker attacker = tempQueue.poll();
                    System.out.println(String.format("%-8d | %s", position++, attacker));
                }
            }

            displayDummyStatus();
        }

        // Renamed method
        private static void displayDummyStatus() {
            System.out.println("\nCURRENT DUMMY STATUS");
            System.out.println("--------------------");

            for (int i = 0; i < NUM_DUMMIES; i++) {
                System.out.print("Dummy " + (i + 1) + ": ");
                if (activeDummies[i] == null) {
                    System.out.println("Available (Not being attacked)");
                } else {
                    System.out.println("Being attacked by: " + activeDummies[i]);
                }
            }
        }
}
