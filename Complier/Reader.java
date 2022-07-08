import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public ArrayList<String> Reader(String fileName) throws IOException {

        ArrayList<String> fileData = new ArrayList<String>();
        String[] arr;
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        // Reading everyline
        String line;
        while ((line = br.readLine()) != null) {
            // Ignores empty lines
            if (!(line.isEmpty())) {
                // Ignores comments
                int i = 0;
                while (line.charAt(i) == ' ' && i < line.length() - 1) {
                    i++;
                }
                if (line.charAt(i) != '/') {
                    arr = line.split("//");
                    fileData.add(arr[0]);
                }
            }
        }

        br.close();
        fr.close();
        return fileData;
    }
}
