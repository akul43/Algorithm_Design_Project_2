# Algorithm_Design_Project_2
Program to find a lattice point which is “most central” to the data set and the total distance to a lattice point which is “most central” to the data using both the L1 (ie Manhattan) and L2 (ie normal distance) metrics.

## Input Format
Every line of the file will contain two strictly positive integers seperated by whitespace (spaces/tabs) each of which
will be one integer lattice point. 

These are example values to place into a data structure.
### Two sample input files - input2.txt:

#### First:

0 0

1 0

2 0

3 0

4 0

5 0

6 0

#### Second:

0 0

10 0

0 10

## Output

Output is 2 lines. The first line will be an ordered pair which is the location of a “most
central” point according to the L1 metric, a space, and the total distance from all data items to the “most central”
point. 

The second line will be an ordered pair which is the location of a “most central” point according the the L2
metric, a space, and the total distance from all data items to the “most central” point.

### Sample output: For the first sample input above:

(3,0) 12

(3,0) 12

### For the second sample input above:

(0,0) 20

(2,2) 19.32084962721683
