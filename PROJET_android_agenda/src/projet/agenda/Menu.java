package projet.agenda;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
	private Button bAdd;
	private Button bExit;
	private Button bRechercher;
	private Button bGestionPersonne;
	private Button bGestionLieu;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Controleur controle = new Controleur();
        bAdd = (Button)findViewById(R.id.bAdd);
        bAdd.setOnClickListener(controle);
        bExit = (Button)findViewById(R.id.bExit);
        bExit.setOnClickListener(controle);
        bRechercher = (Button)findViewById(R.id.bRechercher);
        bRechercher.setOnClickListener(controle);
        bGestionPersonne = (Button)findViewById(R.id.bGestionPersonne);
        bGestionPersonne.setOnClickListener(controle);
        bGestionLieu = (Button)findViewById(R.id.bGestionLieu);
        bGestionLieu.setOnClickListener(controle);
    }
    private class Controleur implements OnClickListener {
		public void onClick(View boutonClique) {
			if (boutonClique == bAdd) {
				Intent i = new Intent(Menu.this, Ajouter.class);
				startActivity(i);
			
			}
			if (boutonClique == bRechercher) {
				Intent i = new Intent(Menu.this, Rechercher.class);
				i.putExtra("type", "Evenment");
				startActivity(i);
			
			}
			if (boutonClique == bGestionPersonne) {
				Intent i = new Intent(Menu.this, Rechercher.class);
				i.putExtra("type", "Personne");
				startActivity(i);
			
			}
			if (boutonClique == bGestionLieu) {
				Intent i = new Intent(Menu.this, Rechercher.class);
				i.putExtra("type", "Lieu");
				startActivity(i);
			
			}
			
			
		}
    }
}