import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Recommandation {

    public static void main (String args[]) throws IOException {

        // Initialisation des listes
        ArrayList<String> Themes = new ArrayList();
        ArrayList<String> Usagers = new ArrayList();


        // On se prépare pour lire le fichier
        InputStream is = new FileInputStream("src/Log-clients-themes.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);


        // On fait le rangement ( => 0 : nb visite | 1 -> Usager | 2 -> Theme )
        String numcursor;
        while ((numcursor = br.readLine()) != null){
            String[] donnees = numcursor.split(";");
            if(donnees.length == 3){
                if(!Themes.contains(donnees[2])){
                    Themes.add(donnees[2]);
                }
                if(!Usagers.contains(donnees[1])){
                    Usagers.add(donnees[1]);
                }
            }
        }
        // On met tout ca dans 2 fichiers.txt
        FileWriter ListeThemes = new FileWriter(new File("LesThemes.txt"));
        FileWriter ListeUsagers = new FileWriter(new File("LesUsagers.txt"));
        for(String untheme : Themes){
            ListeThemes.write(untheme + "\n");
        }
        for(String unusager : Usagers){
            ListeUsagers.write(unusager + "\n");
        }
        ListeThemes.close();
        ListeUsagers.close();


        // On fait le fichier MUT
        FileWriter matMUT = new FileWriter(new File("MatriceMUT.txt"));
        for(String myuser : Usagers){
            for(String mytheme : Themes){

                int compteur=0;

                InputStream ismut =new FileInputStream("src/Log-clients-themes.txt");
                InputStreamReader isrmut = new InputStreamReader(ismut);
                BufferedReader brmut = new BufferedReader(isrmut);

                String lepointer;
                while ((lepointer = brmut.readLine()) != null){
                    String[] matrice = lepointer.split(";");
                    if(matrice.length == 3){
                        if(matrice[1].equals(myuser) && matrice[2].equals(mytheme)){
                            compteur++;
                        }
                    }
                }
                matMUT.write(""+ compteur +";");
            }
            matMUT.write("\n");
        }
        matMUT.close();

    }

}
