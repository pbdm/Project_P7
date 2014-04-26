package projet.agenda;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Pourvoyeur extends ContentProvider{
	public static final String AUTHORITY= "projet.agenda.Pourvoyeur";
	public static final String DATABASE_NAME = "Agenda.db";
	String tableName;
	Uri ContentUri;
	public static final UriMatcher uriMatcher;
	private DatabaseHelper dbHelper;
	public static final int EVENMENT_DIR=1;	
	public static final int EVENMENT_ITEM=2;	
	public static final int LIEU_DIR=3;	
	public static final int LIEU_ITEM=4;
	public static final int PERSONNE_DIR=5;	
	public static final int PERSONNE_ITEM=6;	
	public static final int EVEN_PERS_DIR=7;	
	public static final int EVEN_PERS_ITEM=8;	
	static {
		uriMatcher = new UriMatcher(0);
		uriMatcher.addURI(AUTHORITY, "evenment", EVENMENT_DIR);  
		uriMatcher.addURI(AUTHORITY, "evenment/#", EVENMENT_ITEM);	 
		uriMatcher.addURI(AUTHORITY, "lieu", LIEU_DIR);  
		uriMatcher.addURI(AUTHORITY, "lieu/#", LIEU_ITEM);
		uriMatcher.addURI(AUTHORITY, "personne", PERSONNE_DIR);  
		uriMatcher.addURI(AUTHORITY, "personne/#", PERSONNE_ITEM);
		uriMatcher.addURI(AUTHORITY, "even_pers", EVEN_PERS_DIR);  
		uriMatcher.addURI(AUTHORITY, "even_pers/#", EVEN_PERS_ITEM);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		System.out.println("pv:delete");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] options;
		String id;
		
		int rowId =0;
		switch (uriMatcher.match(uri)){
		case EVENMENT_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.delete("evenment", "_id=?", options);
		case PERSONNE_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.delete("personne", "_id=?", options);
		case LIEU_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.delete("lieu", "_id=?", options);
		case EVEN_PERS_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.delete("even_pers", "_id=?", options);
		}
		if(rowId>0){
			Uri delectUri = ContentUris.withAppendedId(ContentUri,rowId);
			getContext().getContentResolver().notifyChange(delectUri, null);
			return rowId;
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		
		System.out.println("gettype");
		switch(uriMatcher.match(uri)){
		case EVENMENT_DIR:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case EVENMENT_ITEM:
			return "vnd.android.cursor.item/vnd.agenda.evenment";
		case PERSONNE_DIR:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case PERSONNE_ITEM:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case LIEU_DIR:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case LIEU_ITEM:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case EVEN_PERS_DIR:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		case EVEN_PERS_ITEM:
			return "vnd.android.cursor.dir/vnd.agenda.evenment";
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		System.out.println("pv:insert");
		System.out.println("uriMatcher:"+uriMatcher.match(uri));
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if(uriMatcher.match(uri) == EVENMENT_DIR){
			tableName="evenment";
			ContentUri = Uri.parse("content://"+AUTHORITY+"/evenment");
		}
		if(uriMatcher.match(uri) == LIEU_DIR){
			tableName="lieu";
			ContentUri = Uri.parse("content://"+AUTHORITY+"/lieu");
		}
		if(uriMatcher.match(uri) == PERSONNE_DIR){
			tableName="personne";
			ContentUri = Uri.parse("content://"+AUTHORITY+"/personne");
		}
		if(uriMatcher.match(uri) == EVEN_PERS_DIR){
			tableName="even_pers";
			ContentUri = Uri.parse("content://"+AUTHORITY+"/even_pers");
		}
		long rowId=db.insert(tableName,null,values);
		if(rowId>0){
			Uri insertedUri = ContentUris.withAppendedId(ContentUri,rowId);
			getContext().getContentResolver().notifyChange(insertedUri, null);
			return insertedUri;
		}
		
		throw new SQLException("Failed to insert row into"+uri);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DatabaseHelper(getContext(), DATABASE_NAME, null, 1);
		System.out.println("pv:create");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		System.out.println("pv:query");
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c;
		String[] options;
		String id;
		switch (uriMatcher.match(uri)) {
		case  EVENMENT_DIR:
			c = db.rawQuery("select * from evenment;",null);
			return c;
		case  EVENMENT_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			c = db.rawQuery("select * from evenment where _id =?;",options);
			return c;
		case LIEU_DIR:
			c = db.rawQuery("select * from lieu;",null);
			return c;
		case  LIEU_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] =id;
			c = db.rawQuery("select * from lieu where _id =?;",options);
			return c;
		case  PERSONNE_DIR:
			c = db.rawQuery("select * from personne;",null);
			return c;
		case  PERSONNE_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] =id;
			c = db.rawQuery("select * from personne where _id =?;",options);
			return c;
		case  EVEN_PERS_DIR:
			c = db.rawQuery("select * from even_pers;",null);
			return c;
		case  EVEN_PERS_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] =id;
			c = db.rawQuery("select * from even_pers where _id =?;",options);
			return c;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		System.out.println("pv:update");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int rowId =0;
		String[] options;
		String id;
		switch (uriMatcher.match(uri)){
		case EVENMENT_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.update("evenment", values, "_id=?",options);
			return rowId;
		case PERSONNE_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.update("personne", values, "_id=?",options);
			return rowId;
		case LIEU_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.update("lieu", values, "_id=?",options);
			return rowId;
		case EVEN_PERS_ITEM:
			id = uri.getPathSegments().get(1);
			options = new String[1];
			options[0] = id;
			rowId= db.update("even_pers", values, "_id=?",options);
			return rowId;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
	}

}
