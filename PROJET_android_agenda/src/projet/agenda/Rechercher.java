package projet.agenda;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;



public class Rechercher extends Activity{
	private ButtonR btn;
	Controleur controle;
	LinearLayout linearLayout2;
	LinearLayout linearLayout;
	ScrollView scrollView;
	Cursor c;
	String type;
	String action;
	Intent i,i2;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.rechercher);
		controle = new Controleur();
		linearLayout=new LinearLayout(this); 
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(linearLayout);  
	    scrollView = new ScrollView(this); 
	    linearLayout2=new LinearLayout(this); 
	    linearLayout2.setOrientation(LinearLayout.VERTICAL);
	    linearLayout.addView(scrollView);
	    scrollView.addView(linearLayout2);
	    i=getIntent();
	    type = i.getStringExtra("type");
	    action = i.getStringExtra("action");
	    System.out.println("type"+type);
	}
	
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Uri uri = null;
		String n = null;
		linearLayout2.removeAllViews();
		if(type.equals("Evenment")){
			uri = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment");
			n="   Date:   ";
		}
		if(type.equals("Lieu")){
			uri = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/lieu");
			n="  Type:   ";
		}
		if(type.equals("Personne")){
			uri = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne");
			n="   Type:   ";
		}
		System.out.println(uri);
		c = managedQuery(uri, null, null, null, null);
		int cont=0;
		while (c.moveToNext()){
			btn = new ButtonR(this);
			btn.setId(cont);
			btn.setIdR(c.getInt(0));
			cont++;
			System.out.println("idbnt:::"+btn.getId());
			btn.setText(type+":"+c.getString(1)+n+c.getString(2));
			btn.setOnClickListener(controle);
			linearLayout2.addView(btn);  
		}
	}
	class ButtonR extends Button{
		int id;
		public ButtonR(Context context) {
			super(context);
			
		}
		void setIdR(int arg){
			id=arg;
		}
		int getIdR(){
			return id;
		}
	}

	class Controleur implements OnClickListener{

		@Override
		public void onClick(View arg0) {	
			if(i.getFlags()==0){//rechercher from menu
				if(type.equals("Evenment"))
					i2 = new Intent(Rechercher.this, Modifier.class);
				if(type.equals("Lieu"))
					i2 = new Intent(Rechercher.this, ModifierLieu.class);
				if(type.equals("Personne"))
					i2 = new Intent(Rechercher.this, ModifierPersonne.class);
				i2.setFlags(((ButtonR) arg0).getIdR());
				startActivity(i2);
			}
			else{//rechercher from ajouter
				c.moveToFirst();
				c.move(arg0.getId());
				System.out.println("id:==="+arg0.getId());
				if(i.getFlags()==1){
					Ajouter.tLieu.setText(c.getString(1));
					Ajouter.id_lieu=c.getInt(0);
					finish();
				}
				if(i.getFlags()==2){
					Ajouter.id_pers[Ajouter.cPersonne]=c.getInt(0);
					Ajouter.tPersonne[getIntent().getIntExtra("cPersonne", 0)].setText(c.getString(1));
					//Ajouter.(values.getAsString("nom_pers"));
					
					finish();
				}
			}
			
		}
		
	}
}
