package db.fr.navigateur1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSV2TextView extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewProgressionPourcentage;
    private Button buttonTacheAsynchrone;
    private ProgressBar barreDeProgression;

    private TextView textViewFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv2_text_view);

        initInterface();
    }

    // -----------------------
    private void initInterface() {

        textViewProgressionPourcentage = (TextView) findViewById(R.id.textViewProgressionPourcentage);
        buttonTacheAsynchrone = (Button) findViewById(R.id.buttonTacheAsynchrone);
        barreDeProgression = (ProgressBar) findViewById(R.id.barreDeProgression);

        textViewFile = (TextView) findViewById(R.id.textViewFile);

        buttonTacheAsynchrone.setOnClickListener(this);
    } // / initInterface

    @Override
    public void onClick(View v) {
        // --- Tache asynchrone
        if (v == buttonTacheAsynchrone) {
            new TacheAsynchrone().execute();
        } // / if buttonTacheAsynchrone
    }

    /*
     * AsyncTask<Params, Progress, Result>
     */
    private class TacheAsynchrone extends AsyncTask<String, String, Integer> {
//        private class TacheAsynchrone extends AsyncTask<String, Integer, Integer> {
        @Override
        // ----------------------------
        protected Integer doInBackground(String... asParametres) {
            // String... parametre : nombre variable d'arguments

            // Se deplace dans un thread d'arriere-plan
            int liProgression = 0, ligneLu = 0;
            InputStream is;
            InputStreamReader isr;
            BufferedReader br;
            String lsLigne = "";
            StringBuilder lsbContenu = new StringBuilder("");

            // Execute la tache en arriere-plan et maj de la barre de progression
            try{
                is = getBaseContext().getResources().openRawResource(R.raw.communes);
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                while ((lsLigne = br.readLine()) != null) {
                    lsLigne = lsLigne + "\n";
                    ligneLu++;

                    liProgression = (ligneLu * 100) / 38950;

                    publishProgress(lsLigne,String.valueOf(liProgression));
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Renvoie la valeur a onPostExecute
            return liProgression - 1;
        } // / doInBackground

        @Override
        // ----------------------------
        protected void onProgressUpdate(String... aiProgressions) {
            // Synchronisation avec le thread de l'UI
            // MAJ de la barre de progression
            textViewFile.setText(aiProgressions[0]);

            barreDeProgression.setProgress(Integer.parseInt(aiProgressions[1]));
            textViewProgressionPourcentage.setText(Integer.toString(Integer.parseInt(aiProgressions[1])) + " %");
        } // / onProgressUpdate

        @Override
        // -------------------------
        protected void onPostExecute(Integer aiResultat) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
//            barreDeProgression.setProgress(aiResultat);
//            textViewProgressionPourcentage.setText(Integer.toString(aiResultat) +  " %");
        } // / onPostExecute
    } // / TacheAsynchrone

}
