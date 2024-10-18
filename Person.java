public class Person {
    Utilities utilities = new Utilities();

    // Person's attributes
    public int startHealth = utilities.MAX_HEALTH;
    public int startPeople = utilities.MAX_PEOPLE;
    public int startFood = utilities.MAX_FOOD;
    public int startZombies = utilities.MAX_ZOMBIES;
    public int startAmmo = utilities.MAX_GUN_AMMO;
    public boolean isAlive = utilities.MIN_HEALTH <= utilities.MAX_HEALTH;
    private int survivorsFound = 0;
    private int foodFound = 0;

    // object to create a person
    public Person(int startPeople, int startHealth, int startFood, int startAmmo, int startZombies) {
        this.startZombies = utilities.generateRandomInRange(utilities.MIN_ZOMBIES, utilities.MAX_ZOMBIES);
        this.startPeople = utilities.generateRandomInRange(utilities.MIN_PEOPLE, utilities.MAX_PEOPLE);
        this.startFood = utilities.generateRandomInRange(utilities.MIN_FOOD, utilities.MAX_FOOD);
        this.isAlive = isAlive;
        this.startAmmo = utilities.generateRandomInRange(utilities.MIN_GUN_AMMO, utilities.MAX_GUN_AMMO);
        this.startHealth = utilities.generateRandomInRange(utilities.MIN_HEALTH, utilities.MAX_HEALTH);
    }


    public void searchForOtherSurvivors() {
        // searching for survivors based on the number of people and random factors
        if (Math.random() < Utilities.PROBABILITY_OF_FINDING_SURVIVOR) {
            int survivorsFound = utilities.generateRandomInRange(0, utilities.MAX_SURVIVORS_FOUND_PER_SEARCH);
            startPeople += survivorsFound;
            System.out.println("People searched for other survivors and found " + survivorsFound + " survivors.");
        } else {
            System.out.println("No survivors found during the search.");
        }
    }

    /*
     * Simulate food search based on the number of people and random factors
     */
    public void searchForFood() {
        // Simulate food search based on the number of people and random factors
        int foodFound = utilities.generateRandomInRange(0, utilities.MAX_FOOD_FOUND_PER_SEARCH * this.getNumPeople());
        startFood += foodFound;
        int foodConsumed = utilities.generateRandomInRange(1, startFood);
        startFood -= foodConsumed;
        System.out.println("Fed people with " + foodConsumed + " units of food.");
        System.out.println("People searched for food and found " + foodFound + " units.");
    }

    // Getters and setters
    public int getStartHealth() {
        return startHealth;
    }

    public void setStartHealth(int startHealth) {
        this.startHealth = startHealth;
    }

    public int getSurvivorsFound() {
        return survivorsFound;
    }

    public int getFoodFound() {
        return foodFound;
    }

    public int getStartPeople() {
        return startPeople;
    }

    public void setStartPeople(int startPeople) {
        this.startPeople = startPeople;
    }

    public int getStartFood() {
        return startFood;
    }

    public void setStartFood(int startFood) {
        this.startFood = startFood;
    }

    public int getStartZombies() {
        return startZombies;
    }

    public void setStartZombies(int startZombies) {
        this.startZombies = startZombies;
    }

    public int getStartAmmo() {
        return startAmmo;
    }

    public void setStartAmmo(int startAmmo) {
        this.startAmmo = startAmmo;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getNumHealth() {
        return startHealth;
    }

    public void setNumHealth(int startHealth) {
        this.startHealth = startHealth;
    }

    public int getNumAmmo() {
        return startAmmo;
    }

    public void setNumAmmo(int startAmmo) {
        this.startAmmo = startAmmo;
    }

    public int getNumFood() {
        return startFood;
    }

    public void setNumFood(int startFood) {
        this.startFood = startFood;
    }

    public int getNumZombies() {
        return startZombies;
    }

    public void setNumZombies(int startZombies) {
        this.startZombies = startZombies;
    }

    public int getNumPeople() {
        return startPeople;
    }

    public void setNumPeople(int startPeople) {
        this.startPeople = startPeople;
    }

    // Method to reduce ammo and return new value
    public int reduceAmmo() {
        return startAmmo > 0 ? --startAmmo : 0;
    }

    // Feeding people and reduce the food amount
    public void feedPeople() {
        int foodConsumed = utilities.generateRandomInRange(1, startFood);  // Random amount of food
        startFood -= foodConsumed;
        System.out.println("Fed people with " + foodConsumed + " units of food.");
        if (startFood < 0) {
            startFood = 0;
            System.out.println("Food has run out.");
        }
    }

    // Zombie and battle-related methods
    public void takeGunAndBluntShot() {
        if (startAmmo > 0) {
            if (!(!gunHit() || instantKill())) {
                killZombie();
                startAmmo--;
            } else {
                takeSecondShot();
            }
        } else {
            if (bluntHit() && bluntInstantKill()) {
                killZombie();
            } else {
                takeSecondBluntShot();
            }
        }
    }

    public void takeSecondShot() {
        if (gunHit() && gunHit()) {
            killZombie();
            startAmmo--;
        } else {
            if (zombieHit()) {
                killPerson();
                startAmmo--;
            } else {
                killZombie();
            }
        }
    }

    public void takeSecondBluntShot() {
        if (bluntHit() && bluntHit() && bluntHit()) {
            killZombie();
        } else {
            killPerson();
        }
    }

    public void killZombie() {
        utilities.zombies--;
        System.out.println("A zombie was killed.");
    }

    public boolean zombieHit() {
        return utilities.generateRandomInRange(1, utilities.MAX_ZOMBIE_HIT_PROB) == 1;
    }

    public boolean instantKill() {
        return utilities.generateRandomInRange(1, utilities.MAX_INSTANT_KILL_PROB) == 1;
    }

    public boolean gunHit() {
        return utilities.generateRandomInRange(1, utilities.MAX_GUN_HIT_PROB) == 1;
    }

    public boolean bluntHit() {
        return utilities.generateRandomInRange(1, utilities.MAX_BLUNT_HIT_PROB) == 1;
    }

    public boolean bluntInstantKill() {
        return utilities.generateRandomInRange(1, utilities.MAX_BLUNT_INSTANT_KILL_PROB) == 1;
    }

    public void killPerson() {
        isAlive = false;
        startPeople--; // Reduce people count when a person dies
        System.out.println("A person has died.");
    }
}