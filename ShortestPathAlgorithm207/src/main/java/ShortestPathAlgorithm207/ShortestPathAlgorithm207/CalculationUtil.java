package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

/**
 * Code to answer questions from 2 to 9 from handout
 * @author Aaron Vannack Lee
 */
public final class CalculationUtil {
	
	public static double timeSpentOnRoad(int distanceTraveled) {
		double milesPerHour = 60.0;
		return distanceTraveled/milesPerHour;
	}
	
	public static double costOfFuel(int distanceTraveled) {
		double pricePerGallon = 3.32;
		int milesPerGallon = 7;
		return (pricePerGallon/milesPerGallon)*distanceTraveled;
	}
	
	public static double driverSalary(int distanceTraveled) {
		double fixedSalary = 1200;
		double bonusPerMile = 0.56;
		return fixedSalary+ (bonusPerMile*distanceTraveled);
	}
	
	public static double helpersSalary(int distanceTraveled) {
		double fixedSalary = 900;
		double bonusPerMile = 0.42;
		return fixedSalary + (bonusPerMile*distanceTraveled);
	}
	
	public static double costOfHotel(int distanceTraveled, int citiestraveled) {
		double averageCostPerPerson = 100;
		int numberOfPeople = 2;
		return (numberOfPeople*(averageCostPerPerson))*citiestraveled;
	}

	public static double costOfFood(int distanceTraveled, int citiesTraveled) {
		double averageMealCostPerPerson = 68.0;
		int numberOfPeople = 2;
		return (numberOfPeople*(averageMealCostPerPerson))*citiesTraveled;
	}
	
	public static double costOfmaintenance(int distanceTraveled) {
		double maintenanceCostperMile = 0.88;
		return maintenanceCostperMile*distanceTraveled;
	}
	
	public static double totalCost(int distanceTraveled, int citiesTraveled) {
		return costOfFuel(distanceTraveled) + driverSalary(distanceTraveled) +
				helpersSalary(distanceTraveled) + costOfHotel(distanceTraveled, citiesTraveled)+
				costOfFood(distanceTraveled, citiesTraveled) + costOfmaintenance(distanceTraveled);
	}
}
