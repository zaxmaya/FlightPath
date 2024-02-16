import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) throws Exception {
    
        if (args.length != 3) {
            System.out.println("Input file names: java Main (flight data file) (paths to calculate file) (output file)");
            return;
        }
        // Sends flight paths to be anaylized
        GraphImp cities = citiesConnectivity(args[0]);

        // Reads from requests file
        Scanner file = new Scanner(new File(args[1]));
        file.nextLine();

        // Prints out result in new file
        PrintWriter result = new PrintWriter(new FileWriter(new File(args[2])));

        int requestNumber = 1;

        while (file.hasNextLine()) {
            String[] elementSplit = file.nextLine().split("\\|");

            // Grab elements of request
            String requestFromCity = elementSplit[0];
            String requestToCity = elementSplit[1];
            String requestSortType = elementSplit[2];

            // Process it
            Path[] paths = cities.findPaths(requestFromCity, requestToCity);

            result.print("Flight " + requestNumber + ": " + requestFromCity + ", " + requestToCity + " ");

            if (requestSortType.equals("C")) {
                result.println("(Cost)");
                sortByCost(paths);
            } else {
                result.println("(Time)");
                sortByTime(paths);
            }

            // Prints out the three most efficient flight plans
            for (int i = 0; i < 3 && i < paths.length; i++) {
                result.println("Path " + (i + 1) + ": " + paths[i].toString() + ". Time: " + paths[i].getTotalTime() + " Cost: " +  paths[i].getTotalCost());
            }

            result.println();
            
            requestNumber++;
        }

        file.close();
        result.close();
    }

    // Processes cities and their connectivity
    private static GraphImp citiesConnectivity(String filename) throws Exception {
        GraphImp cities = new GraphImp();

        Scanner file = new Scanner(new File(filename));
        file.nextLine();

        while (file.hasNextLine()) {
            String[] elementSplit = file.nextLine().split("\\|");

            String fromCityName = elementSplit[0];
            String toCityName = elementSplit[1];
            double cost = Double.parseDouble(elementSplit[2]);
            int time = Integer.parseInt(elementSplit[3]);

            cities.joinCities(fromCityName, toCityName, cost, time);
            cities.joinCities(toCityName, fromCityName, cost, time);
        }

        file.close();

        return cities;
    }

    // Sort the paths most time efficient
    private static void sortByTime(Path[] paths) {
        for (int i = 0; i < paths.length - 1; i++) 
        {
            for (int j = i + 1; j < paths.length; j++) 
            {
                if (paths[j].getTotalTime() < paths[i].getTotalTime()) {

                    Path temp = paths[i];
                    paths[i] = paths[j];
                    paths[j] = temp;
                }
            }
        }
    }

    // Sort the paths most cost efficient
    private static void sortByCost(Path[] paths) {
        for (int i = 0; i < paths.length - 1; i++) 
        {
            for (int j = i + 1; j < paths.length; j++) 
            {
                if (paths[j].getTotalCost() < paths[i].getTotalCost()) {

                    Path temp = paths[i];
                    paths[i] = paths[j];
                    paths[j] = temp;
                }
            }
        }
    }

    
}
