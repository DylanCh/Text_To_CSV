import java.util.*;
import java.io.*;

class ReadTxtIntoCSV {
    public static void  main(String[] args) {
        // In command line: java ReadTxtIntoCSV TestDoc.txt (or whichever file you want to read)
        String fileName = args[0];

        if (fileName.endsWith(".rtf")){
            System.out.print("Special decoding needed");
            System.exit(1);
        }

        /************************* PART 1 *************************************** */

        // String builder for the buffered reader to read into
        StringBuilder sb = new StringBuilder();
        
        try {
            // buffered reader for reading the file
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Reading begins     
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            } // reading ends

            System.out.println(sb.toString());

        }
        catch(FileNotFoundException fe){
            System.out.print("Can't read file");
        }
        catch(IOException ie){
            System.out.print("Can't read file");
        }

        // separate the read data into lines
        String everything = sb.toString();
        String lines[] = everything.split("\\r?\\n");

        /************************** PART 2 *************************************** */

        // Specify strings that are numbers
        final Set<String> set = new HashSet<>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));

        try{
            // PrintWrite for writing into a file
            PrintWriter pw = new PrintWriter(new File("test1.csv"));

            // A string to write into the file (initialized with a header)
            StringBuilder writeToFile = new StringBuilder("QuestionText,Answer 1,Answer2,Answer 3,Answer 4\n");


            for (int i =0; i< lines.length; i++){
                // check if the line is empty
                if (!lines[i].trim().isEmpty()){
                    // check the first letter of each line, if it matches one of members of the numeric strings then ..
                    if (set.contains(lines[i].trim().substring(0,1)) ) {
                        // ... then we have writeToFile append the line and the 4 lines below, in CSV format
                        writeToFile.append(lines[i]+","+lines[i+1]+","+lines[i+2]+","+lines[i+3]+","+lines[i+4]+"\n");
                    }
                }
            }

            System.out.println(writeToFile);

            /***************************** Part 3: exporting to a CSV *************************************** */
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("test1.csv");           
                fileWriter.append(writeToFile.toString());
            }
            catch (IOException ioe2) {}
            finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing fileWriter !!!");
                    e.printStackTrace();
                }
            }
        }
        catch(FileNotFoundException fe2){}              
    }    
}
