package projet.agenda;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ModifierPersonne extends AjouterPersonne{
	private Button bDelect;
	int a;
	private int id;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Controleur2 controleurM = new Controleur2();
		bDelect= new Button(this);
		bDelect.setText("Delect All");
		bAjouter.setText("Modifier");
		bAjouter.setOnClickListener(controleurM);
		((LinearLayout)findViewById(R.id.layoutPersonne)).addView(bDelect);
		bDelect.setOnClickListener(controleurM);
		id=getIntent().getFlags();
		Uri uri = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne/"+id);
		Cursor c = managedQuery(uri, null, null, null, null);
		c.moveToNext();

		eNom.setText(c.getString(1));
		eTelephone.setText(c.getString(3));
		type=c.getString(2);
		a=0;
		while(TYPE[a].equals(type)==false)
		{	
			a++;
		}
		sType.setSelection(a);
	}
	private class Controleur2 implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v==bAjouter){
				ContentValues values = new ContentValues();
				values.put("type_pers", type);
				values.put("nom_pers",eNom.getText().toString());
				values.put("tel_pers", eTelephone.getText().toString());
				System.out.println(values);	
				getContentResolver().update(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne/"+id), values, null, null);
				finish();
			}
			if(v==bDelect){
				getContentResolver().delete(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne/"+id), null, null);
				finish();
			}
		}
	}
}

