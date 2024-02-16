public class GraphImp {

    private LinkedList<City> cities;

    // Create a graph
    public GraphImp() {
        cities = new LinkedList<City>();
    }

    public City getCity(String name) {
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);

            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }

        return null;
    }

    // Joins cities
    public void joinCities(String requestFromCity, String requestToCity, double cost, int time) {
        City fromCity = getCity(requestFromCity);

        if (fromCity == null) {
            fromCity = new City(requestFromCity);
            cities.add(fromCity);
        }

        City toCity = getCity(requestToCity);

        if (toCity == null) {
            toCity = new City(requestToCity);
            cities.add(toCity);
        }

        fromCity.addFinalCity(toCity, time, cost);
    }

    // Finds the paths between cities
    private void findPaths(Stack<City> citiesPath, LinkedList<Path> solutionPaths, City toCity) {
        if (citiesPath.isEmpty()) {
            // No more solution
            return;
        }

        City currentCity = citiesPath.peek();

        if (currentCity == toCity) {
            // Found a solution
            Path path = new Path();

            for (int i = 0; i < citiesPath.size(); i++) {
                path.addCity(citiesPath.get(i));
            }

            solutionPaths.add(path);
        } else {
            // Find next solution
            for (int i = 0; i < currentCity.getFinalCities().size(); i++) {
                City nextCurrentCity = currentCity.getFinalCities().get(i).getCity();

                if (!citiesPath.contains(nextCurrentCity)) {
                    citiesPath.push(nextCurrentCity);
                    findPaths(citiesPath, solutionPaths, toCity);
                }
            }
        }
        
        citiesPath.pop();
    }
    
    // Find all paths between two city
    public Path[] findPaths(String requestFromCity, String requestToCity) {
        City fromCity = getCity(requestFromCity);
        City toCity = getCity(requestToCity);
        
        if(fromCity == null || toCity == null) {
            return null;
        }
        
        Stack<City> citiesPath = new Stack<City>();
        LinkedList<Path> solutionPaths = new LinkedList<Path>();
        
        citiesPath.add(fromCity);
        findPaths(citiesPath, solutionPaths, toCity);
        
        Path[] paths = new Path[solutionPaths.size()];
        
        for(int i = 0; i < solutionPaths.size(); i++) {
            paths[i] = solutionPaths.get(i);
        }
        
        return paths;
    }
}
