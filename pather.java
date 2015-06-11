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
                System.out.println(s);
                writer.write(s + "\n");
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
        int rowindex = 0;
        for(String line : inputLines) {
            int colindex = -1;
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == '#') {
                    colindex = i;
                    System.out.println("Row Index: " + rowindex + " Col Index: " + colindex);
                    //todo: check if # marker exists already
                    if(rowHashMarker.size() > 0 && colHashMarker.size() > 0) {
                        //todo: # exists, need to connect them

                        //todo: remove first # marker
                        rowHashMarker.remove(0);
                        colHashMarker.remove(0);
                    }
                    //todo: add new # marker to hashMarker list
                    rowHashMarker.add(rowindex);
                    colHashMarker.add(colindex);

                }
                if(line.charAt(i) == '.' && rowHashMarker.size() > 0 && colHashMarker.size() > 0) {
                    System.out.println("Seen '.' and Hash Markers > 0");
                }
                if(line.charAt(i) == '.' && rowHashMarker.size() == 0 && colHashMarker.size() == 0) {
                    System.out.println("Seen '.' and Hash Markers == 0");
                    outputLines.add(".");
                }
            }
            rowindex++;
        }


        return outputLines;
    }
}
