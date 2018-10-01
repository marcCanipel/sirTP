import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Recommandation {

    public static void main (String args[]) throws IOException {

        ArrayList<String> Themes = new ArrayList();
        ArrayList<String> Usagers = new ArrayList();

        FileWriter fileThemes = new FileWriter(new File("Themes.txt"));
        FileWriter fileUsagers = new FileWriter(new File("Usagers.txt"));

        InputStream is = new FileInputStream("Log-clients-themes.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String numcursor;
        while ((numcursor = br.readLine()) != null){
            String[] donnees = numcursor.split(";");
            if(donnees.length != 3) continue;

            if(!Themes.contains(donnees[2]))
                Themes.add(donnees[2]);
            if(!Usagers.contains(donnees[1]))
                Usagers.add(donnees[1]);
        }

        for(String theme : Themes) fileThemes.write(theme + "\n");
        for(String usager : Usagers) fileUsagers.write(usager + "\n");

        fileThemes.close();
        fileUsagers.close();
        
        FileWriter matMUT = new FileWriter(new File("mut.txt"));
        for(String myuser : Usagers){
            for(String mytheme : Themes){

                int compteur=0;

                InputStream ismut =new FileInputStream("Log-clients-themes.txt");
                InputStreamReader isrmut = new InputStreamReader(ismut);
                BufferedReader brmut = new BufferedReader(isrmut);

                String lepointer;
                while ((lepointer = brmut.readLine()) != null){
                    String[] matrice = lepointer.split(";");
                    if(matrice.length != 3) continue;

                    if(matrice[1].equals(myuser) && matrice[2].equals(mytheme))
                        compteur++;
                }
                matMUT.write(compteur +";");
            }
            matMUT.write("\n");
        }
        matMUT.close();

    }

}
