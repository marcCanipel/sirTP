import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Mtt {

    public static void main (String args[]) throws IOException {

        ArrayList<String> dataMut = new ArrayList();

        FileWriter fileMtt = new FileWriter(new File("mtt.txt"));

        InputStream is = new FileInputStream("mut.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String numcursor;
        while ((numcursor = br.readLine()) != null){
            String[] donnees = numcursor.split(";");
            for (String donnee: donnees) {
                String d = Integer.parseInt(donnee)==0?"0":"1";
                fileMtt.write(d + ";");
            }
            fileMtt.write("\n");
        }

        fileMtt.close();
    }

}
