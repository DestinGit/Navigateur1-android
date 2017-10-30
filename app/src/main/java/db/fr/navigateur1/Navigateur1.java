package db.fr.navigateur1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Navigateur1 extends AppCompatActivity {
    private WebView navigateur;

    @Override
    // -----------------
    public void onCreate(Bundle savedInstanceState) {
        // -----------------
        super.onCreate(savedInstanceState);

        // --- Le layout
        setContentView(R.layout.navigateur1);

        // --- Le widget WebView
        navigateur = (WebView) findViewById(R.id.navigateur);

        // --- Charge une page dans le navigateur 'perso' ie la WebView du layout
        navigateur.loadUrl("http://www.meteo-paris.com/ile-de-france/previsions.php");
//        navigateur.loadUrl("https://www.google.fr/");

    } /// onCreate()

}
