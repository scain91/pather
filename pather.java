import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pather {

    public static void main(String[] args) throws IOException {
        boolean test = true;
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

        List<Integer> rowHashMarker = new ArrayList<Integer>(); //marks last seen # position
        List<Integer> colHashMarker = new ArrayList<Integer>(); //marks last seen # position
        System.out.println("rowHashMarker size: " + rowHashMarker.size());
        System.out.println("colHashMarker size: " + colHashMarker.size());

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < inputLines.get(row).length(); col++) {
                int colindex = -1;
                if(inputLines.get(row).charAt(col) == '#') {
                    colindex = col;
                    System.out.println("Seen '#' and Row Index: " + row + " Col Index: " + colindex);
                    //todo: check if # marker exists already, if it does then connect them
                    if(rowHashMarker.size() > 0 && colHashMarker.size() > 0) {
                        System.out.println("Entering into connect portion");
                        //todo: # exists, need to connect them
                        int row1 = rowHashMarker.get(0);
                        int col1 = colHashMarker.get(0);
                        int row2 = row;
                        int col2 = col;
                        //check row and coldifferences
                        int rowDiff = row2 - row1;
                        int colDiff = col2 - col1;

                        int rowStart = row1;
                        int rowEnd = row2;
                        int colStart = col1+1;
                        int colEnd = col2;
                        while(rowStart <= rowEnd) {
                            if(rowStart == rowEnd) {
                                System.out.println("rowStart == rowEnd");
                                while(colStart < colEnd) {
                                    System.out.println("colStart = " + colStart + " colEnd = " + colEnd);
                                    outputLines.add("*");
                                    colStart++;
                                }
                                rowStart++;
                            }
                            if(rowStart < rowEnd) {
                                System.out.println("rowStart < rowEnd");
                                while(colStart < cols) {
                                    System.out.println("colStart = " + colStart + " cols = " + cols);
                                    outputLines.add("*");
                                    colStart++;
                                }
                                outputLines.add("\n");
                                colStart = 0;
                                rowStart++;
                            }
                            /*if(colStart == cols) {
                                System.out.println("colStart == cols");
                                outputLines.add("\n");
                                colStart = 0;
                                rowStart++;
                            }*/
                        }
                        System.out.println("Past the outputLines portion for connecting");

                        //todo: check which row move form to use based on where the cols are
                        //from row1+1 < row2 if on same col
                        //from row1+1 <= row2 if on diff col
                        if(colDiff == 0) {
                            while(row1+1 < row2) {

                                row1++;
                            }
                        }
                        else {
                            while(row1+1 <= row2) {

                                row1++;
                            }
                        }
                        //todo: check which col move form to use based on where the rows are
                        //from colfirst+1 < colsecond if on same row
                        //from colfirst+1 <= colsecond if on diff row
                        int colfirst = (col1 < col2) ? col1 : col2;
                        int colsecond = (col1 > col2) ? col1 : col2;
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
                        rowHashMarker.remove(0);
                        colHashMarker.remove(0);
                    }
                    //add new # marker to hashMarker list
                    outputLines.add("#");
                    rowHashMarker.add(row);
                    colHashMarker.add(colindex);

                }
                if(inputLines.get(row).charAt(col) == '.' && rowHashMarker.size() > 0 && colHashMarker.size() > 0) {
                    System.out.println("Seen '.' and Hash Markers > 0");
                }
                if(inputLines.get(row).charAt(col) == '.' && rowHashMarker.size() == 0 && colHashMarker.size() == 0) {
                    System.out.println("Seen '.' and Hash Markers == 0");
                    outputLines.add(".");
                }
                /*if(inputLines.get(row).charAt(col) == '\n') {
                    System.out.println("NEWLINE");
                    outputLines.add("\n");
                }*/
            }
        }
        //no other matches
        //todo: output '.' after last #
        System.out.println("rows = " + rows);
        int lastRows = rowHashMarker.get(0);
        int lastCols = colHashMarker.get(0)+1;
        while(lastRows < rows) {
            System.out.println("lastRows = " + lastRows);
            while(lastCols < cols) {
                outputLines.add(".");
                lastCols++;
            }
            outputLines.add("\n");
            lastCols = 0;
            lastRows++;
        }
        //outputLines.add("\n\n");

        return outputLines;
    }
}
