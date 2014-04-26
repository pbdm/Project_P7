package projet.agenda;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Modifier extends Ajouter{
	private Button bDelect;
	int a;
	private int id;
	//Calendar cAlarmCancel;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Controleur2 controleurM = new Controleur2();
		bDelect= new Button(this);
		bDelect.setText("Delect All");
		bAjouter.setText("Modifier");
		bAjouter.setOnClickListener(controleurM);
		((LinearLayout)findViewById(R.id.layout)).addView(bDelect);
		bDelect.setOnClickListener(controleurM);
		id=getIntent().getFlags();
		Uri uri2 = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/even_pers");
		Cursor c2 = managedQuery(uri2, null, null, null, null);
		while(c2.moveToNext()){
			if (c2.getInt(1)==id){
		//		l
			}
		}
		
		
		Uri uri = Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment/"+id);
		Cursor c = managedQuery(uri, null, null, null, null);
		c.moveToNext();
		type=c.getString(4);
		id_lieu=c.getInt(3);
		eDate.setText(c.getString(2));
		eEvenment.setText(c.getString(1));
		eHeureD.setText(c.getString(5));
		eHeureF.setText(c.getString(6));
		alarme=c.getString(7);
		priorite=c.getString(9);
		eRemarque.setText(c.getString(10));
		System.out.println("id_liue:   "+id_lieu);
		if (id_lieu!=0){
			Uri uriLieu =Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/lieu/"+id_lieu);
			Cursor cLieu = managedQuery(uriLieu, null, null, null, null);
			cLieu.moveToNext();
			tLieu.setText(cLieu.getString(1));
		}
		a=0;
		while(TYPE[a].equals(type)==false)
		{	
			a++;
		}
		sType.setSelection(a);
		a=0;
		while(AGENDA_ALARME[a].equals(alarme)==false)
		{	
			a++;
		}
		sAlarme.setSelection(a);
		a=0;
		while(PRIORITE[a].equals(priorite)==false)
		{	
			a++;
		}
		sPriorite.setSelection(a);
//		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  
//		Intent intent = new Intent(this,Receiver.class);
//		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
//		System.out.println(cAlarme.getTime());
//        am.cancel(sender); 
//        Log.i("Alarme","add alarm");
		
	}

	private class Controleur2 implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			if(arg0==bAjouter){
				 DateFormat dfT= new SimpleDateFormat("hh:mm"); 
				 DateFormat dfD= new SimpleDateFormat("yyyy/MM/dd"); 
				 try {
					if(
							
						dfT.parse(eHeureD.getText().toString()).after(dfT.parse(eHeureF.getText().toString()))
						){
						CharSequence contentText = "heure debut must<heure fin"; 
						Toast.makeText(Modifier.this, contentText, Toast.LENGTH_LONG).show(); 
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
							Toast.makeText(Modifier.this, contentText, Toast.LENGTH_LONG).show();
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
							System.out.println(values);	
							getContentResolver().update(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment/"+id), values, null, null);
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
								Intent intent = new Intent(Modifier.this,Receiver.class);
								PendingIntent sender = PendingIntent.getBroadcast(Modifier.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
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
			
			}
			
			
			
			if(arg0==bDelect){
				getContentResolver().delete(Uri.parse("content://"+Pourvoyeur.AUTHORITY+"/evenment/"+id), null, null);
				finish();
			}
		}
		
	}
}
