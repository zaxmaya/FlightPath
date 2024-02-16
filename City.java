public class City {

    private String name;
    private LinkedList<FinalCity> finalCities;

    // Create a city
    public City(String name) {
        this.name = name;
        finalCities = new LinkedList<FinalCity>();
    }
    
    // Add a new final to the city
    public void addFinalCity(City city, int time, double cost) {
        finalCities.add(new FinalCity(cost, time, city));
    }

    public LinkedList<FinalCity> getFinalCities() {
        return finalCities;
    }

    // Get the name
    public String getName() {
        return name;
    }
}
