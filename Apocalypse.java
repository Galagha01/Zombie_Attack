import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Apocalypse {

    // Reference to a utility class holding random number generation and constants
    private Utilities utilities = new Utilities();

    // Creates a new Person object representing the survivors
    private Person person = new Person(
            utilities.MAX_PEOPLE,
            utilities.MAX_HEALTH,
            utilities.MAX_FOOD,
            utilities.MAX_GUN_AMMO,
            utilities.MAX_ZOMBIES);

    /**
     * Runs the simulation of the apocalypse scenario.
     *
     * This method performs the following steps:
     *  - Sets up the output file and writer
     *  - Loops through each day of the simulation
     *      - Generates new zombies
     *      - Feeds the people
     *      - Simulates a battle between survivors and zombies
     *      - Updates remaining days and logs status
     *      - Checks for win/lose conditions based on people and food
     *  - Closes the output file (handled in the try-with-resources block)
     *  - Catches any IOExceptions that might occur while writing to the file
     */
    public void runSimulation() {
        String outputFileName = "files/output.txt";  // Path to the output file

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            // Loop through the simulation while survivors exist, have food, and days remain
            while (person.getNumPeople() > 0 && person.getNumFood() > 0 && utilities.MAX_DAYS > 0) {

                // Simulate the arrival of new zombies each day
                utilities.generateZombies();
                String zombieOutput = "Day " + (utilities.MAX_DAYS + 1) + ": Fresh wave of zombies! " + utilities.zombies + " have arrived.\n";
                System.out.println(zombieOutput);
                writer.write(zombieOutput);  // Log zombie generation to file

                // Feed the survivors using their method
                person.feedPeople();
                String foodOutput = "Food remaining after feeding: " + person.getNumFood() + "\n";
                System.out.println(foodOutput);
                writer.write(foodOutput);  // Log food status to file

                // Simulate a battle between survivors and zombies
                simulateBattle(writer);

                // Update remaining days and log status
                utilities.MAX_DAYS--;
                String statusOutput = "Food remaining: " + person.getNumFood() + "\n" +
                        "People left: " + person.getNumPeople() + "\n" +
                        "Days left: " + utilities.MAX_DAYS + "\n" +
                        "-------------------------------------------------------\n";
                System.out.println(statusOutput);
                writer.write(statusOutput);  // Log the current status

                // Check if everyone survived or ran out of food
                if (utilities.MAX_DAYS == 0) {
                    if (person.getNumPeople() > 0) {
                        String winMessage = "Congratulations! You survived the apocalypse! Some brave survivors remain.\n";
                        System.out.println(winMessage);
                        writer.write(winMessage);
                        break;
                    } else {
                        String loseMessage = "The relentless zombies overwhelmed the survivors. You lost.\n";
                        System.out.println(loseMessage);
                        writer.write(loseMessage);
                        break;
                    }
                }

                // Additional lose condition: if food runs out
                if (person.getNumFood() == 0) {
                    String foodOutMessage = "The survivors have run out of food and succumbed to starvation. You lost.\n";
                    System.out.println(foodOutMessage);
                    writer.write(foodOutMessage);
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Simulate a battle between the people and zombies
    public void simulateBattle(BufferedWriter writer) throws IOException {
        while (utilities.zombies > 0 && person.getNumPeople() > 0) {
            // Check if person survives the zombie attack
            if (!person.zombieHit()) {
                person.killZombie();
                utilities.zombies--;
            } else {
                person.killPerson(); // Reduce person count here if they don't survive
            }

            // Check if food is sufficient after the battle
            if (person.getNumFood() < person.getNumPeople() * utilities.MIN_FOOD) {
                // Sending people to search for food after the battle
                person.searchForFood();
            }

            // Search for other survivors after the battle
            person.searchForOtherSurvivors();

            writer.write("Battle in progress... Zombies left: " + utilities.zombies + "\n");

            writer.write("Survivors found: " + person.getSurvivorsFound() + "\n");
            writer.write("Food found: " + person.getFoodFound() + "\n");
        }
    }

}