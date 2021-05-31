import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import java.util.Scanner;

//Akul Kumar
//Project 2 - COMP482
public class Project2 {

    public static void main(String[] args) {
        ArrayList<Point> input_coordinates;
        input_coordinates = readFile();
        findMidPointL1(input_coordinates);
        findMidPointL2(input_coordinates);
    }

    //Read the input.txt file by reading the line and parsing it to extract the X and Y coordinates
    public static ArrayList<Point> readFile() {
        File inputFile = new File("input2.txt");
        ArrayList<Point> input_coord = new ArrayList<>();
        try {
            // scanning input file
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                // Parsing X and Y coordinates from the input2.txt file
                String[] parsing = reader.nextLine().split("\\s+");

                // adding new coordinate pair to collection
                input_coord.add(new Point(Integer.parseInt(parsing[0]), Integer.parseInt(parsing[1])));

            }
            reader.close();
            return input_coord;
        } catch (Exception e) {
            System.out.println("Error reading input file.\n");
        }
        return input_coord;
    }

    public static void findMidPointL2(ArrayList<Point> input) {
        //Find the smallest and biggest X & Y points, then pass all the possible MidPoints inside our enclosing box.
        Point min_max_X = find_smallest_biggest_X_point(input);
        Point min_max_Y = find_smallest_biggest_Y_point(input);
        ArrayList<Point> allPoints = new ArrayList<>();
        for (int i = min_max_X.x; i <= min_max_X.y; i++) { //i is our X value
            for (int j = min_max_Y.x; j <= min_max_Y.y; j++) { //j is our Y value
                Point temp = new Point();
                temp.setLocation(i, j);
                allPoints.add(temp);
            }
        }
        Point2D midIndex_Distance = findDistanceL2(allPoints, input);
        System.out.println("(" + allPoints.get((int) midIndex_Distance.getX()).getX() + "," + allPoints.get((int) midIndex_Distance.getX()).getY() + ") "
                + midIndex_Distance.getY() + "\n");
    }

    public static Point2D findDistanceL2(ArrayList<Point> possiblePoints, ArrayList<Point> target) {

        //Find the distance between every possible point and the input coordinates.
        //-> Then return the Point that has the smallest distance between the input coordinates
        ArrayList<Double> pointsDistance = new ArrayList<>();
        double smallestIndex = 1000;
        //√[(x1−x2)2+ (y1−y2)2]
        for (Point possiblePoint : possiblePoints) {
            double distance = 0;
            for (Point point : target) {
                //Sum the distance between current point and the target Points
                distance += L2(possiblePoint, point);
            }
            pointsDistance.add(distance);
        }
        int counter = 0;
        while (smallestIndex == 1000) {
            if (pointsDistance.get(counter).equals(Collections.min(pointsDistance))) {
                //Save the Point that has the smallest distance sum between the input coordinates
                smallestIndex = counter;
            } else {
                counter++;
            }
        }
        Point2D ret = new Point2D.Double();
        ret.setLocation(smallestIndex, Collections.min(pointsDistance));
        return ret;
    }

    public static void findMidPointL1(ArrayList<Point> input) {
        //Sort between X and Y table and find it's median point to determine mid point.
        //-> Then find the distance sum and print results
        ArrayList<Integer> X_coord = new ArrayList<>();
        ArrayList<Integer> Y_coord = new ArrayList<>();
        Point myPoint = new Point();
        //Parsing into two different ArrayList in order to sort the X and Y values.
        for (Point value : input) {
            X_coord.add((int) value.getX());
        }
        for (Point value : input) {
            Y_coord.add((int) value.getY());
        }
        Collections.sort(X_coord);
        Collections.sort(Y_coord);
        //Different median formula is used according to the parity (odd or even)
        if (X_coord.size() % 2 != 0) { //is Odd
            myPoint.setLocation(X_coord.get(X_coord.size() / 2), Y_coord.get(Y_coord.size() / 2));
        } else { //is even
            int x = (X_coord.get((int) Math.round((double) X_coord.size() / 2)) + X_coord.get(((int) Math.round((double) X_coord.size() / 2) - 1))) / 2;
            int y = (Y_coord.get((int) Math.round((double) Y_coord.size() / 2)) + Y_coord.get(((int) Math.round((double) Y_coord.size() / 2) - 1))) / 2;
            myPoint.setLocation(x, y);
        }
        //Now we find the distance of our set mid Point with all the other input points
        int distance = 0;
        for (Point point : input) {
            distance += L1(myPoint, point);
        }
        //Print results
        System.out.println("(" + myPoint.getX() + "," + myPoint.getY() + ") " + distance);


    }

    public static double L1(Point a, Point b) { //L1 Distance formula

        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public static double L2(Point a, Point b) { //L2 Distance formula
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public static Point find_smallest_biggest_X_point(ArrayList<Point> myList) {
        //Function to find the smallest and biggest X Point in the table.
        int min = (int) myList.get(0).getX();
        int max = (int) myList.get(0).getX();
        for (int i = 1; i < myList.size(); i++) {
            if (myList.get(i).getX() > max) {
                max = (int) myList.get(i).getX();
            } else if (myList.get(i).getX() < min) {
                min = (int) myList.get(i).getX();
            }
        }
        Point min_and_max_X = new Point();
        min_and_max_X.setLocation(min, max);
        //-> Returned as a point for ease of accessing
        return min_and_max_X;
    }

    public static Point find_smallest_biggest_Y_point(ArrayList<Point> myList) {
        //Function to find the smallest and biggest Y Point in the table.
        int min = (int) myList.get(0).getY();
        int max = (int) myList.get(0).getY();
        for (int i = 1; i < myList.size(); i++) {
            if (myList.get(i).getY() > max) {
                max = (int) myList.get(i).getY();
            } else if (myList.get(i).getY() < min) {
                min = (int) myList.get(i).getY();
            }
        }
        //-> Returned as a point for ease of accessing
        Point min_and_max_Y = new Point();
        min_and_max_Y.setLocation(min, max);
        return min_and_max_Y;
    }
}