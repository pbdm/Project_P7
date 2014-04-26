package projet.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("sql:create db");
		
		db.execSQL("create table lieu(_id integer primary key autoincrement," +
				"nom_lieu text, type_lieu text, address_lieu text," +
				"tel_lieu integer);");
		db.execSQL("create table personne(_id integer primary key autoincrement," +
				" nom_pers text, type_pers text," +
				"tel_pers integer);");
		db.execSQL("create table evenment(_id integer primary key autoincrement," +
				"evenment text,date date," +
				"id_lieu integer ,type text," +
				"heure_debut time,heure_fin time,alarme int,type_alerme text," +
				"priorite text,remarque text," +
				"foreign key(id_lieu) references lieu(_id));");
		db.execSQL("create table even_pers(_id integer primary key autoincrement," +
				"id_even integer, id_pers integer," +
				"foreign key(id_even) references evenment(_id)," +
				"foreign key(id_pers) references evenment(_id));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("sql:update db");
		db.execSQL("DROP TABLE IF EXISTS even_pers;");
		db.execSQL("DROP TABLE IF EXISTS evenment;");
		db.execSQL("DROP TABLE IF EXISTS personne;");
		db.execSQL("DROP TABLE IF EXISTS lieu;");
		
		onCreate(db);
	}
	
}
