import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pather {

    static boolean test = true;

    public static void main(String[] args) throws IOException {
        if(test) {
            System.out.println("Pather");
        }

        String input = "input.txt";
        String output = "output.txt";
        try {
            input = args[0];
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("missing input file parameter");
        }
        try {
            output = args[1];

        } catch (Exception e) {
            throw new IndexOutOfBoundsException("missing output file parameter");
        }
        if(test) {
            System.out.println("input: " + input);
            System.out.println("output: " + output);
        }


        List<String> inputLines = new ArrayList<String>();
        FileInputStream inputStream = null;

        BufferedReader reader = null;

        try {
            inputStream = new FileInputStream(input);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //System.out.println("Reading File line by line using BufferedReader");

            String line = reader.readLine();

            while(line != null){
                //System.out.println(line);
                inputLines.add(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                reader.close();
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        List<String> outputLines = findPath(inputLines);


        BufferedWriter writer = null;
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(output);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            //System.out.println("Reading File line by line using BufferedReader");

            for(String s : outputLines) {
                //System.out.println(s);
                writer.write(s);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                writer.close();
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(pather.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        if(test) {
            System.out.println("End of Pather");
        }
    }

    //todo: find path method given the input file
    private static List<String> findPath(List<String> inputLines) {
        List<String> outputLines = new ArrayList<String>();
        int rows = inputLines.size();
        int cols = inputLines.get(0).length();
        List<Point> pathPointList = new ArrayList<Point>();
        List<Point> hashMarkerList = new ArrayList<Point>();

        for(int row = 0; row < rows; row++) {
            cols = inputLines.get(row).length();
            for(int col = 0; col < cols; col++) {
                int colindex = -1;
                if(inputLines.get(row).charAt(col) == '.' && hashMarkerList.size() == 0) {
                    if(test) {
                        System.out.println("Seen '.' and Hash Markers == 0");
                    }
                    outputLines.add(".");
                    if(col+1 == cols) {
                        outputLines.add("\n");
                    }
                }
                if(inputLines.get(row).charAt(col) == '.' && hashMarkerList.size() > 0) {
                    if(test) {
                        System.out.println("Seen '.' and Hash Markers > 0");
                    }
                }

                if(inputLines.get(row).charAt(col) == '#') {
                    if(test) {
                        System.out.println("Seen '#' and Row Index: " + row + " Col Index: " + col);
                    }


                    //todo: check if there is a prev # marker, if it does then connect them
                    if(hashMarkerList.size() > 0) {
                        if(test) {
                            System.out.println("Entering into connect portion");
                        }
                        //todo: # exists, need to connect them
                        int rowStart = hashMarkerList.get(0).x;
                        int colStart = hashMarkerList.get(0).y+1; //todo: get rid of +1
                        int rowEnd = row;
                        int colEnd = col;
                        Point point1 = new Point(rowStart,hashMarkerList.get(0).y);
                        Point point2 = new Point(rowEnd,colEnd);
                        pathPointList = getPath(point1,point2);
                        //check row and coldifferences
                        int rowDiff = rowEnd - rowStart;
                        int colDiff = colEnd - colStart;
                        while(rowStart <= rowEnd) {
                            if(rowStart == rowEnd) {
                                if(test) {
                                    System.out.println("rowStart == rowEnd");
                                    System.out.println("colStart = " + colStart + " colEnd = " + colEnd);
                                }
                                while(colStart < colEnd) {
                                    if(test) {
                                        System.out.println("colStart = " + colStart + " < colEnd = " + colEnd);
                                    }
                                    if(true) { //todo: check list of path points to see if this point is in path
                                        outputLines.add("*");
                                    }
                                    else {
                                        outputLines.add(".");
                                    }
                                    colStart++;
                                }
                            }
                            if(rowStart < rowEnd) {
                                if(test) {
                                    System.out.println("rowStart < rowEnd");
                                }
                                while(colStart < cols) {
                                    if(test) {
                                        System.out.println("colStart = " + colStart + " cols = " + cols);
                                    }
                                    if(true) { //todo: check list of path points to see if this point is in path
                                        outputLines.add("*");
                                    }
                                    else {
                                        outputLines.add(".");
                                    }
                                    colStart++;
                                }
                                outputLines.add("\n");
                            }
                            colStart = 0;
                            rowStart++;
                        }
                        if(test) {
                            System.out.println("Past the outputLines portion for connecting");
                        }

                        //todo: check which row move form to use based on where the cols are
                        //from rowStart+1 < rowEnd if on same col
                        //from rowStart+1 <= rowEnd if on diff col
                        if(colDiff == 0) {
                            while(rowStart+1 < rowEnd) {

                                rowStart++;
                            }
                        }
                        else {
                            while(rowStart+1 <= rowEnd) {

                                rowStart++;
                            }
                        }
                        //todo: check which col move form to use based on where the rows are
                        //from colfirst+1 < colsecond if on same row
                        //from colfirst+1 <= colsecond if on diff row
                        int colfirst = (colStart < colEnd) ? colStart : colEnd;
                        int colsecond = (colStart > colEnd) ? colStart : colEnd;
                        if(rowDiff == 0) {
                            while(colfirst+1 < colsecond) {

                                colfirst++;
                            }
                        }
                        else {
                            while(colfirst+1 <= colsecond) {

                                colfirst++;
                            }
                        }


                        //remove first # marker
                        hashMarkerList.remove(0);
                    }
                    //add new # marker to hashMarker list
                    outputLines.add("#");
                    Point newHashPoint = new Point(row,col);
                    hashMarkerList.add(newHashPoint);
                }
            }
        }
        //no other matches
        //todo: output '.' after last #
        if(test) {
            System.out.println("rows = " + rows);
        }
        int lastRows = hashMarkerList.get(0).x;
        int lastCols = hashMarkerList.get(0).y+1; //todo: get rid of +1
        while(lastRows < rows) {
            if(test) {
                System.out.println("lastRows = " + lastRows);
            }
            while(lastCols < cols) {
                outputLines.add(".");
                lastCols++;
            }
            outputLines.add("\n");
            lastCols = 0;
            lastRows++;
        }

        return outputLines;
    }

    private static List<Point> getPath(Point point1, Point point2) {
        if(test) {
            System.out.println("Entering getPath");
        }
        List<Point> pathPointList = new ArrayList<Point>();
        int row1 = point1.x;
        int col1 = point1.y;
        int row2 = point2.x;
        int col2 = point2.y;

        int rowDiff = row2-row1; //if zero no shift
        int colDiff = col2-col1; //if pos then left shift, if neg then right shift
        int rowShift = rowDiff;
        int colShift = colDiff;
        if(colShift == 0) { //there is # on the last row separating so there is one less row shift for the path
            rowShift--;
        }
        while(rowShift > 0) {
            if(test) {
                System.out.println("rowShift " + rowShift + " > 0");
            }
            if(rowShift == 1 && colDiff == 0 && rowDiff == 1) {
                //do not add to pathPointList because points are next to each other
                if(test) {
                    System.out.println("do not add to pathPointList because points are next to each other");
                }
            }
            else {
                Point p = new Point(row1+rowShift,col1);
                pathPointList.add(p);
                if(test) {
                    System.out.println("Point added in row shift: (" + p.x + ", " + p.y + ")");
                }
            }
            rowShift--;
        }
        /*while(rowShift < rowDiff) {
            if(test) {
                System.out.println("rowShift = " + rowShift + " < rowDiff " + rowDiff);
            }
            Point p = new Point(row1+rowShift,col1);
            pathPointList.add(p);
            rowShift++;

        }*/
        if(colShift != 0) {
            if(test) {
                System.out.println("colshift != 0");
                System.out.println("row2 = " + row2 + ", col1 = " + col1);
            }
            Point p = new Point(row2,col1);
            pathPointList.add(p);
            if(colShift > 0) {
                colShift--;
            }
            else {
                colShift++;
            }
        }
        if(colShift > 0) {
            while(colShift > 0) {
                if(test) {
                    System.out.println("colShift = " + colShift);
                    int colVal = col1+colShift;
                    System.out.println("Point (" + row2 + ", " + colVal + ")");
                }
                Point p = new Point(row2,col1+colShift);
                pathPointList.add(p);
                colShift--;
            }
        }
        else {
            while(colShift < 0) {
                if(test) {
                    System.out.println("colShift = " + colShift);
                    int colVal = col1+colShift;
                    System.out.println("Point (" + row2 + ", " + colVal + ")");
                }
                Point p = new Point(row2,col1+colShift);
                pathPointList.add(p);
                colShift++;
            }
        }


        for(Point p : pathPointList) {
            System.out.println("Output Point (" + p.x + ", " + p.y + ")");
        }

        if(test) {
            System.out.println("Finished getPath");
        }
        return pathPointList;
    }
}
