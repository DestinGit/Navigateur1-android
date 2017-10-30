package db.fr.navigateur1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TacheAsynchroneTest extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewProgressionPourcentage;
    private Button buttonTacheAsynchrone;
    private ProgressBar barreDeProgression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache_asynchrone_test);

        initInterface();
    }

    // -----------------------
    private void initInterface() {

        textViewProgressionPourcentage = (TextView) findViewById(R.id.textViewProgressionPourcentage);
        buttonTacheAsynchrone = (Button) findViewById(R.id.buttonTacheAsynchrone);
        barreDeProgression = (ProgressBar) findViewById(R.id.barreDeProgression);

        buttonTacheAsynchrone.setOnClickListener(this);
    } // / initInterface

    @Override
    public void onClick(View vue) {
        // --- Tache asynchrone
        if (vue == buttonTacheAsynchrone) {
            new TacheAsynchrone().execute();
        } // / if buttonTacheAsynchrone
    }


    /*
     * AsyncTask<Params, Progress, Result>
     */
    private class TacheAsynchrone extends AsyncTask<String, Integer, Integer> {
        @Override
        // ----------------------------
        protected Integer doInBackground(String... asParametres) {
            // String... parametre : nombre variable d'arguments

            // Se deplace dans un thread d'arriere-plan
            int liProgression;

            // Execute la tache en arriere-plan et maj de la barre de progression
            for (liProgression = 1; liProgression <= 100; liProgression++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                // Sans l'appel a cette methode l'UI n'est pas maj
                publishProgress(liProgression);
            } /// for

            // Renvoie la valeur a onPostExecute
            return liProgression - 1;
        } // / doInBackground

        @Override
        // ----------------------------
        protected void onProgressUpdate(Integer... aiProgressions) {
            // Synchronisation avec le thread de l'UI
            // MAJ de la barre de progression
            barreDeProgression.setProgress(aiProgressions[0]);
            textViewProgressionPourcentage.setText(Integer.toString(aiProgressions[0]) + " %");
        } // / onProgressUpdate

        @Override
        // -------------------------
        protected void onPostExecute(Integer aiResultat) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
            barreDeProgression.setProgress(aiResultat);
            textViewProgressionPourcentage.setText(Integer.toString(aiResultat) +  " %");
        } // / onPostExecute
    } // / TacheAsynchrone

}
