import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class testPather {
    String inputDirectory = "C:\\Users\\Sara\\Documents\\Big Data\\sideprojects\\src\\main\\java\\";
    String outputDirectory = "C:\\Users\\Sara\\Documents\\Big Data\\sideprojects\\src\\main\\java\\outputTests\\";

    @Test(expected = IndexOutOfBoundsException.class)
    public void patherNoArgumentsTest() throws IOException {
        String[] patherArgs = new String[0];
        String output = "";
        pather.main(patherArgs);
        assertEquals("", output);
    }

    @Test(expected = NullPointerException.class)
    public void patherOneArgumentsTest() throws IOException {
        String[] patherArgs = new String[2];
        patherArgs[0] = inputDirectory + "inputone.txt";
        String output = "";
        pather.main(patherArgs);
        assertEquals("", output);
    }

    @Test(expected = NullPointerException.class)
    public void patherOneArgumentsTestAgain() throws IOException {
        String[] patherArgs = new String[2];
        patherArgs[1] = outputDirectory + "outputoneTest.txt";
        String output = "";
        pather.main(patherArgs);
        assertEquals("", output);
    }

    /*@Test(expected = FileNotFoundException.class)
    public void patherInputArgumentDoesNotExist() throws FileNotFoundException {
        String[] patherArgs = new String[2];
        patherArgs[0] = inputDirectory + "inputFake.txt";
        patherArgs[1] = outputDirectory + "outputoneTest.txt";
        String output = "";
        try {
            pather.getInputFromFileToList(patherArgs[0]);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Could not find database/storage.");
            System.out.println(e.getMessage());
            throw e;
        }
        assertEquals("", output);
    }*/

    @Test
    public void patherTestOne() throws IOException {
        String inputFileName = inputDirectory + "inputone.txt";
        String outputFileName = inputDirectory + "outputoneTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add(".#***#.");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestTwo() throws FileNotFoundException {
        String inputFileName = inputDirectory + "inputtwo.txt";
        String outputFileName = inputDirectory + "outputtwoTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("......#.");
        correctOutput.add(".#*****.");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestThree() throws FileNotFoundException {
        String inputFileName = inputDirectory + "inputthree.txt";
        String outputFileName = inputDirectory + "outputthreeTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("....");
        correctOutput.add("#...");
        correctOutput.add("**#.");
        correctOutput.add("..*.");
        correctOutput.add("#**.");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestFour() throws FileNotFoundException {
        String inputFileName = inputDirectory + "inputfour.txt";
        String outputFileName = inputDirectory + "outputfourTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("..#..");
        correctOutput.add("..*..");
        correctOutput.add("..#..");
        correctOutput.add("..*..");
        correctOutput.add("..*..");
        correctOutput.add(".#*..");
        correctOutput.add(".***#");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestFive() throws FileNotFoundException {
        String inputFileName = inputDirectory + "inputfive.txt";
        String outputFileName = inputDirectory + "outputfiveTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("..#..");
        correctOutput.add("..*..");
        correctOutput.add("..#..");
        correctOutput.add("..*..");
        correctOutput.add("..*..");
        correctOutput.add(".#*..");
        correctOutput.add(".**##");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestEdgeOne() throws IOException {
        String inputFileName = inputDirectory + "inputEdgeOne.txt";
        String outputFileName = inputDirectory + "outputEdgeOneTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("#");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestEdgeTwo() throws IOException {
        String inputFileName = inputDirectory + "inputEdgeTwo.txt";
        String outputFileName = inputDirectory + "outputEdgeTwoTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("##");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestEdgeThree() throws IOException {
        String inputFileName = inputDirectory + "inputEdgeThree.txt";
        String outputFileName = inputDirectory + "outputEdgeThreeTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("###.");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestEdgeFour() throws IOException {
        String inputFileName = inputDirectory + "inputEdgeFour.txt";
        String outputFileName = inputDirectory + "outputEdgeFourTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("###.");
        correctOutput.add("....");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }

    @Test
    public void patherTestEdgeFive() throws IOException {
        String inputFileName = inputDirectory + "inputEdgeFive.txt";
        String outputFileName = inputDirectory + "outputEdgeFiveTest.txt";

        String[] patherArgs = new String[2];
        patherArgs[0] = inputFileName;
        patherArgs[1] = outputFileName;
        try {
            pather.main(patherArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> correctOutput = new ArrayList<String>();
        correctOutput.add("#*#*#.......");

        File outputFileCreated = new File(outputFileName);
        assertTrue(outputFileCreated.exists());
        List<String> outputFileLines = pather.getInputFromFileToList(outputFileName);
        assertEquals(correctOutput, outputFileLines);
    }
}
