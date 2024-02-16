public class Path {

    private LinkedList<City> allCities;

    // Create a new path
    public Path() {
        allCities = new LinkedList<City>();
    }

    // Add a new city to the path
    public void addCity(City city) {
        allCities.add(city);
    }

    // Return the total time
    public int getTotalTime() {
        int totalTime = 0;

        for (int i = 0; i < allCities.size() - 1; i++) {
            City city = allCities.get(i);
            City toCity = allCities.get(i + 1);

            for (int j = 0; j < city.getFinalCities().size(); j++) {
                FinalCity finalCity = city.getFinalCities().get(j);

                if (finalCity.getCity() == toCity) {
                    totalTime += finalCity.getTime();
                }
            }
        }

        return totalTime;
    }

    // Return the total cost
    public double getTotalCost() {
        double totalCost = 0;

        for (int i = 0; i < allCities.size() - 1; i++) {
            City city = allCities.get(i);
            City toCity = allCities.get(i + 1);

            for (int j = 0; j < city.getFinalCities().size(); j++) {
                FinalCity finalCity = city.getFinalCities().get(j);

                if (finalCity.getCity() == toCity) {
                    totalCost += finalCity.getCost();
                }
            }
        }

        return totalCost;
    }
    
    // Return arrow which represents path direction
    public String toString() {
        String string = "";
        
        for(int i = 0; i < allCities.size(); i++) {
            string += allCities.get(i).getName();
            
            if(i + 1 < allCities.size()) {
                string += " -> ";
            }
        }
        
        return string;
    }
}
