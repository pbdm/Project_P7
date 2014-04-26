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

public class AjouterLieu extends Activity{
	
	protected Button bAjouter;
	protected EditText eNom;
	protected EditText eAddress;
	protected EditText eTelephone;
	protected Spinner sType;
	protected static final String[] TYPE={"","Universite","Marche","Restorant","Gare"};
	protected String type = TYPE[0];
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lieu);
        Controleur controle = new Controleur();
        bAjouter = (Button)findViewById(R.id.bAjouterLieu);
        bAjouter.setOnClickListener(controle);
      //spinner de choisir du type
		sType = (Spinner) findViewById(R.id.sTypeLieu);
		ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TYPE);  
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_item);  
		sType.setAdapter(adapterType);  
		sType.setOnItemSelectedListener(controle);
		eAddress = (EditText)findViewById(R.id.eAddressLieu);
		eTelephone = (EditText)findViewById(R.id.eTelephoneLieu);
		eNom = (EditText)findViewById(R.id.eNomLieu);
	}
	private class Controleur implements OnClickListener,OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("type_lieu:"+TYPE[arg2]);
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
				values.put("nom_lieu", eNom.getText().toString());
				values.put("type_lieu",type);
				values.put("address_lieu", eAddress.getText().toString());
				values.put("tel_lieu", eTelephone.getText().toString());
				System.out.println(values);	
				Uri uri = getContentResolver().insert(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/lieu"), values);
				System.out.println(""+uri.toString());
				Ajouter.tLieu.setText(values.getAsString("nom_lieu"));
				Ajouter.id_lieu=ContentUris.parseId(uri);
				finish();
			}
		}
		
	}
}
