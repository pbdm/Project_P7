package projet.agenda;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Ajouter extends Activity{
	protected Button bAjouter;
	protected Button bDate;
	protected Button bHeureD;
	protected Button bHeureF;
	protected Button bLieuN;
	protected Button bEnvoyer;
	protected Button bNouveauPesronne;
	protected Button bPersonneA;
	protected Button bPersonneN;
	protected Button bLieuA;
	protected EditText eEvenment;
	protected EditText eDate;
	protected EditText eHeureD;
	protected EditText eHeureF;
	protected EditText eRemarque;
	protected static  TextView tLieu;
	protected Spinner sType;
	protected Spinner sAlarme;
	protected Spinner sPriorite;
	protected static final String[] AGENDA_ALARME={"0","1","2","3","4"};
	protected static final String[] TYPE={"","RDV","Cours","Aniversaire","Voyage"};
	protected static final String[] PRIORITE={"Important","Moyen","Niveau Bas"};
	protected String type = TYPE[0];
	protected static long id_lieu;
	protected String priorite = PRIORITE[0];
	protected String alarme = AGENDA_ALARME[0];
	protected Calendar c = Calendar.getInstance();
	protected Calendar cAlarme = Calendar.getInstance();
	protected Controleur controle;
	protected static LinearLayout lPersonne;
	protected static int cPersonne=0;
	protected static long[] id_pers = new long[10];
	static TextView[] tPersonne = new TextView[10];
	static boolean aPersonne=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajouter);
		controle = new Controleur();
		//alarme
		
		
		eEvenment = (EditText) findViewById(R.id.eEvenment);
//spinner de choisir du type
		sType = (Spinner) findViewById(R.id.sType);
		ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TYPE);  
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_item); 
      //sType.
        sType.setAdapter(adapterType);  
		sType.setOnItemSelectedListener(controle);  

//spinner de coisir du alarme
		sAlarme = (Spinner) findViewById(R.id.sAlarme);
		ArrayAdapter<String> adapterAlarme = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,AGENDA_ALARME);  
        adapterAlarme.setDropDownViewResource(android.R.layout.simple_spinner_item);  
        sAlarme.setAdapter(adapterAlarme);  
		sAlarme.setOnItemSelectedListener(controle);  
//spinner de coisir du priorite
		sPriorite = (Spinner) findViewById(R.id.sPriorite);
		ArrayAdapter<String> adapterPriorite = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,PRIORITE);  
        adapterPriorite.setDropDownViewResource(android.R.layout.simple_spinner_item);  
        sPriorite.setAdapter(adapterPriorite);  
		sPriorite.setOnItemSelectedListener(controle);
        
		bAjouter = (Button)findViewById(R.id.bAjouter);
        bAjouter.setOnClickListener(controle);
        bDate = (Button)findViewById(R.id.bDate);
        bDate.setOnClickListener(controle);
        eDate = (EditText)findViewById(R.id.eDate);
        eDate.setText(c.get(Calendar.YEAR)+"/"+String.format("%02d",c.get(Calendar.MONTH)+1)+"/"+String.format("%02d",c.get(Calendar.DAY_OF_MONTH)));
        bHeureD = (Button)findViewById(R.id.bHeureD);
        bHeureD.setOnClickListener(controle);
        bHeureF = (Button)findViewById(R.id.bHeureF);
        bHeureF.setOnClickListener(controle);
        eHeureD = (EditText)findViewById(R.id.eHeureD);
        eHeureD.setText(String.format("%02d",c.get(Calendar.HOUR_OF_DAY))+":"+String.format("%02d",c.get(Calendar.MINUTE)));
        eHeureF = (EditText)findViewById(R.id.eHeureF);
        eHeureF.setText(String.format("%02d",c.get(Calendar.HOUR_OF_DAY))+":"+String.format("%02d",c.get(Calendar.MINUTE)));  
        bLieuN = (Button)findViewById(R.id.bLieuN);
        bLieuN.setOnClickListener(controle);
        bNouveauPesronne = (Button)findViewById(R.id.bNouveauPersonne);
        bNouveauPesronne.setOnClickListener(controle);
        bLieuA = (Button)findViewById(R.id.bLieuA);
        bLieuA.setOnClickListener(controle);
        tLieu = (TextView)findViewById(R.id.tLieu);
     //   tPersonne = (TextView)findViewById(R.id.tPersonne);
        eRemarque = (EditText)findViewById(R.id.eRemarque);
        lPersonne =(LinearLayout)findViewById(R.id.lPersonne);
        
        bEnvoyer = (Button)findViewById(R.id.bEnvoyer);
        bEnvoyer.setOnClickListener(controle);
        
        
	}
	

	protected void onStart() {
		super.onStart();
		aPersonne=true;
	}
	protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
        case 1:
          
            dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
                        eDate.setText(year+"/"+String.format("%02d",month+1)+"/"+String.format("%02d",dayOfMonth));
                        cAlarme.set(Calendar.YEAR, year);
                        cAlarme.set(Calendar.MONTH, month);
                        cAlarme.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    }
                },
                c.get(Calendar.YEAR), 
                c.get(Calendar.MONTH), 
                c.get(Calendar.DAY_OF_MONTH) 
                
            );
            break;
        case 2:
            dialog=new TimePickerDialog(
                this, 
                new TimePickerDialog.OnTimeSetListener(){
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    	eHeureD.setText(String.format("%02d", hourOfDay)+":"+String.format("%02d",minute));
                    	 cAlarme.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    	 cAlarme.set(Calendar.MINUTE, minute);
                    }
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            );
            break;
        case 3:
            dialog=new TimePickerDialog(
                this, 
                new TimePickerDialog.OnTimeSetListener(){
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    	eHeureF.setText(String.format("%02d", hourOfDay)+":"+String.format("%02d",minute));
                    }
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            );
            break;
        }
        return dialog;
    }
	
	
	private class Controleur implements OnClickListener,OnItemSelectedListener{
		LinearLayout layout;
		
		public void onClick(View boutonClique) {
			if (boutonClique == bAjouter) {
			
				 DateFormat dfT= new SimpleDateFormat("hh:mm"); 
				 DateFormat dfD= new SimpleDateFormat("yyyy/MM/dd"); 
				 try {
					if(
							
						dfT.parse(eHeureD.getText().toString()).after(dfT.parse(eHeureF.getText().toString()))
						){
						CharSequence contentText = "heure debut must<heure fin"; 
						Toast.makeText(Ajouter.this, contentText, Toast.LENGTH_LONG).show(); 
					}
//					eDate.setText(c.getString(2));
//					eEvenment.setText(c.getString(1));
//					eHeureD.setText(c.getString(5));
//					eHeureF.setText(c.getString(6));
					else{
					////check le heure ~~~
						boolean p=true;
						Uri uri2 = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment");
						Cursor cursor = managedQuery(uri2, null, null, null, null);
						while(cursor.moveToNext()){
							if( (dfD.parse(cursor.getString(2)).equals(dfD.parse(eDate.getText().toString())))&&
								((dfT.parse(eHeureD.getText().toString()).before(dfT.parse(cursor.getString(5)))&&dfT.parse(eHeureF.getText().toString()).after(dfT.parse(cursor.getString(5))))
								||(dfT.parse(eHeureD.getText().toString()).after(dfT.parse(cursor.getString(5)))&&dfT.parse(eHeureD.getText().toString()).before(dfT.parse(cursor.getString(6))))
								)
							  )
							{
								p=false;		
							}	
								
							
						}		
						if(p==false){
							CharSequence contentText = "already existe une evenment dans le meme temps"; 
							Toast.makeText(Ajouter.this, contentText, Toast.LENGTH_LONG).show();
						}
						else{
							ContentValues values = new ContentValues();
							values.put("type", type);
							values.put("id_lieu",id_lieu);
							values.put("date",eDate.getText().toString());
							values.put("evenment", eEvenment.getText().toString());
							values.put("heure_debut", eHeureD.getText().toString());
							values.put("heure_fin", eHeureF.getText().toString());
							values.put("alarme", Integer.parseInt(alarme));
							values.put("priorite", priorite);
							values.put("remarque", eRemarque.getText().toString());
							
							
							
							
							//System.out.println(values);	
							Uri uri = getContentResolver().insert(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment"), values);
							System.out.println(""+uri.toString());
							/////////////////////////////////////
							long id_even=ContentUris.parseId(uri);
							ContentValues values2 = new ContentValues();
							while (cPersonne!=0){
								
									values2.put("id_even", id_even);
									values2.put("id_pers", id_pers[cPersonne]);
									
							//Uri uri2 = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/even_pers");
								getContentResolver().insert(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/even_pers"), values2);
								cPersonne--;
								
							}
							if(alarme!="0"){
								AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  
								Intent intent = new Intent(Ajouter.this,Receiver.class);
								PendingIntent sender = PendingIntent.getBroadcast(Ajouter.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
						        cAlarme.set(Calendar.HOUR_OF_DAY, cAlarme.get(Calendar.HOUR_OF_DAY)-Integer.parseInt(alarme));
						        System.out.println(cAlarme.getTime());
						        am.set(AlarmManager.RTC_WAKEUP, cAlarme.getTimeInMillis(), sender);  
						        Log.i("Alarme","add alarm");
					        }  
							finish();
						}
					}
				 } catch (ParseException e) {
						
						e.printStackTrace();
					}
				//////
			}
			if (boutonClique == bDate){
				showDialog(1);
			}
			if (boutonClique == bHeureD){
				showDialog(2);
			}
			if (boutonClique == bHeureF){
				showDialog(3);
			}
			if (boutonClique == bLieuN){
				Intent i = new Intent(Ajouter.this, AjouterLieu.class);
				startActivity(i);
			}
			if (boutonClique == bNouveauPesronne){
				
				if (cPersonne>10)
				{
					
				}
				if (aPersonne ==true){
					cPersonne++;
					aPersonne =false;
					layout = new LinearLayout(Ajouter.this);
					bPersonneN = new Button(Ajouter.this);
					bPersonneN.setText("Personne nouveau");
					bPersonneN.setOnClickListener(controle);
					bPersonneA = new Button(Ajouter.this);
					bPersonneA.setText("Personne ancien");
					bPersonneA.setOnClickListener(controle);
					tPersonne[cPersonne] = new TextView(Ajouter.this);
					tPersonne[cPersonne].setId(cPersonne+2000);
					lPersonne.addView(layout);
					layout.addView(bPersonneA);
					layout.addView(bPersonneN);
					layout.addView(tPersonne[cPersonne]);
					
					
					
				}
			}
			
			if (boutonClique == bPersonneN){
				layout.removeView(bPersonneA);
				layout.removeView(bPersonneN);
				Intent i = new Intent(Ajouter.this, AjouterPersonne.class);
				i.putExtra("cPersonne", cPersonne);
				startActivity(i);
			}
			if (boutonClique == bLieuA){
				Intent i = new Intent(Ajouter.this, Rechercher.class);
				i.putExtra("type", "Lieu");
				i.setFlags(1);
				startActivity(i);
			}
			if (boutonClique == bPersonneA){
				layout.removeView(bPersonneA);
				layout.removeView(bPersonneN);
				Intent i = new Intent(Ajouter.this, Rechercher.class);
				i.putExtra("type", "Personne");
				i.putExtra("cPersonne", cPersonne);
				i.setFlags(2);
				startActivity(i);
			}
			if (boutonClique == bEnvoyer) {
				
				
		        
		        int cPersonneEnvoyer=cPersonne;
				while (cPersonneEnvoyer!=0){
//					values.put("id_lieu",id_lieu);
//					values.put("date",eDate.getText().toString());
					Uri uri4 = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/lieu/"+id_lieu);
					Cursor cursorL = managedQuery(uri4, null, null, null, null);
					cursorL.moveToNext();
					Uri uri3 = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/personne/"+id_pers[cPersonneEnvoyer]);
					Cursor cursor = managedQuery(uri3, null, null, null, null);
					cursor.moveToNext();
					String msg="Evenment:"+eEvenment.getText().toString()+"  Lieu:"+cursorL.getString(1)+"   herureD"+eHeureD.getText().toString()+"   heureF:"+eHeureF.getText().toString()+"  Date:"+eDate.getText().toString();
					String numero= cursor.getString(3);
					SmsManager gestionSMS=SmsManager.getDefault();
					gestionSMS.sendTextMessage(numero, null ,msg, null, null);
					cPersonneEnvoyer--;
				}
			}
		}
		
			
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
				if (arg0==sType){
					System.out.println("type:"+TYPE[arg2]);
					type = TYPE[arg2];
				}
				
				
				if (arg0==sAlarme){
					System.out.println("alarme"+AGENDA_ALARME[arg2]);
					alarme = AGENDA_ALARME[arg2];
					//ajouter le alarme
				
				}
				if (arg0==sPriorite){
					System.out.println("priorite:"+PRIORITE[arg2]);
					priorite = PRIORITE[arg2];
				}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		
		}
    }


	


	
	
}
