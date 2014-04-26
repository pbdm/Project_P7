package projet.agenda;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AjouterPersonne extends Activity{
	
	protected Button bAjouter;
	protected EditText eNom;
	protected EditText eTelephone;
	protected Spinner sType;
	protected static final String[] TYPE={"","Prof","Edute","Travailler","Autre"};
	protected String type = TYPE[0];
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personne);
        Controleur controle = new Controleur();
        bAjouter = (Button)findViewById(R.id.bAjouterPersonne);
        bAjouter.setOnClickListener(controle);
       
      //spinner de choisir du type
		sType = (Spinner) findViewById(R.id.sTypePersonne);
		ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TYPE);  
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_item);  
		sType.setAdapter(adapterType);  
		sType.setOnItemSelectedListener(controle);
		eTelephone = (EditText)findViewById(R.id.eTelephonePersonne);
		eNom = (EditText)findViewById(R.id.eNomPersonne);
	}
	private class Controleur implements OnClickListener,OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			type = TYPE[arg2];
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onClick(View boutonClique) {
			// TODO Auto-generated method stub
			if (boutonClique == bAjouter) {
				ContentValues values = new ContentValues();
				values.put("nom_pers", eNom.getText().toString());
				values.put("type_pers",type);
				values.put("tel_pers", eTelephone.getText().toString());
				Uri uri = getContentResolver().insert(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne"), values);
				System.out.println(""+uri.toString());
				Ajouter.id_pers[Ajouter.cPersonne]=ContentUris.parseId(uri);
				Ajouter.tPersonne[getIntent().getIntExtra("cPersonne", 0)].setText(values.getAsString("nom_pers"));
				//Ajouter.(values.getAsString("nom_pers"));
				
				finish();
			}
			
		}
		
	}
}
