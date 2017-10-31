package sf.fr.androidweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVFromWeb extends AppCompatActivity {
    private TextView textViewCSV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csvfrom_web);
        textViewCSV = (TextView) findViewById(R.id.textViewCSV);

        new TacheAsynchrone().execute();
    }

    private class TacheAsynchrone extends AsyncTask<String, Integer, List<String>> {
        @Override
        // ----------------------------------
        protected List<String> doInBackground(String... asParametres) {
            // String... parametre : nombre variable d'arguments
            // Se deplace dans un thread d'arriere-plan
            List<String> liste = new ArrayList<>();

            String lsURL;

            lsURL = "http://172.26.10.29:8084/ServletsJSPJSTLCours/CommuneToCSV";

            URL urlConnection = null;
            HttpURLConnection httpConnection = null;

            try {
                // Instanciation de HttpURLConnection avec l'objet url
                urlConnection = new URL(lsURL);
                httpConnection = (HttpURLConnection) urlConnection.openConnection();
                // Choix de la methode get ou post
                //httpConnection.setRequestMethod("GET");
                // Autorise l'envoi de donnees
                // Sets the flag indicating whether this URLConnection allows input.
                // true est la valeur par defaut
                // Elle permet par exemple de recuperer le code retour ...
                //httpConnection.setDoInput(true);

                // Connexion
                httpConnection.connect();
                // EXECUTION DE LA REQUETE ET RESPONSE
                InputStream is = httpConnection.getInputStream();
                // Comme l'on recoit un flux Text ASCII
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String lsLigne;
                while ((lsLigne = br.readLine()) != null) {
                    liste.add(lsLigne);
                }
                br.close();
                is.close();
            } catch (IOException e) {
                liste.add(e.getMessage());
            } finally {
                // Deconnexion
                httpConnection.disconnect();
            }
            // Renvoie la valeur a onPostExecute
            return liste;
        } /// doInBackground

        @Override
        // -------------------------
        protected void onPostExecute(List<String> liste) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
            StringBuilder lsb = new StringBuilder();
            for (String element : liste) {
                lsb.append(element);
                lsb.append("\n");
            }
            textViewCSV.setText(lsb.toString());
        } /// onPostExecute
    } /// TacheAsynchrone
}
