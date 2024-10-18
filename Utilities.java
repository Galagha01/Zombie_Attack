import static java.lang.Math.*;
import java.util.Random;

public class Utilities {
	// Game logic constants
	public  int MIN_INSTANT_KILL_PROB = 1, MAX_INSTANT_KILL_PROB = 5;
	public  int MIN_BLUNT_INSTANT_KILL_PROB = 1;
	public  int MAX_BLUNT_INSTANT_KILL_PROB = 10;
	public  int MIN_BLUNT_HIT_PROB = 1, MAX_BLUNT_HIT_PROB = 3;
	public  int START_NUM_DAYS = 1, START_NUM_FOOD = 10;
	public  int MIN_GUN_HIT_PROB = 1, MAX_GUN_HIT_PROB = 2;
	public  int MIN_ZOMBIE_HIT_PROB = 1, MAX_ZOMBIE_HIT_PROB = 2;
	public  int MIN_ZOMBIES = 10, MAX_ZOMBIES = 15;
	public  int MIN_PEOPLE = 1, MAX_PEOPLE = 20;
	public  int MIN_FOOD = 10, MAX_FOOD = 90;
	public  int MIN_GUN_AMMO = 0, MAX_GUN_AMMO = 600;
	public  int MIN_HEALTH = 1, MAX_HEALTH = 100;
	public static int MAX_FOOD_FOUND_PER_SEARCH = 10;
	public  int zombies = generateRandomInRange(MIN_ZOMBIES, MAX_ZOMBIES);
	public  int MAX_SURVIVORS_FOUND_PER_SEARCH = 5;
	public static double PROBABILITY_OF_FINDING_SURVIVOR = 0.3;
	public  int MIN_DAYS = 1, MAX_DAYS = 10;

	//Zombie generator
	public void generateZombies() {
		zombies = generateRandomInRange(MIN_ZOMBIES, MAX_ZOMBIES);
	}

	public enum Status { ALIVE, DEAD }

	int max =50;
	int min =15;
	public static int generateRandomInRange(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

}