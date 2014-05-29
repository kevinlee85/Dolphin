package Dolphin.src.DatabaseProvider;

import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;

import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_id";

	public static final String KEY_ISBN = "isbn";

	public static final String KEY_TITLE = "title";

	public static final String KEY_PATH = "path";
	
	public static final String KEY_DESCRIPTION = "description";
	
	public static final String KEY_PRICES = "prices";
	
	public static final String KEY_COLOR = "color";
	
	public static final String KEY_SIZE = "size";
	
	public static final String KEY_ID_DETAIL = "idDetail";
	
	public static final String KEY_NAME = "name";
	
	public static final String KEY_CATEGORY = "category";	
	
	public static final String KEY_DATE = "date";
	
	public static final String KEY_FATHER_ID = "fatherId";
	
	public static final String KEY_PHONE = "phone";
	
	public static final String KEY_PASSWORD = "password";
	
	public static final String KEY_REPASSWORD = "repassword";
	
	public static final String KEY_EMAIL = "email";

	private static final String TAG = "DBAdapter";

	private static String DATABASE_NAME = "DataBase_imageres";

	private static final String DATABASE_TABLE = "imageres";
	
	private static String DATABASE_TABLE_DETAIL = "imageresdetail";

	private static final int DATABASE_VERSION = 1;

	private static String DATABASE_CREATE = "create table IF NOT EXISTS imageres (_id integer primary key autoincrement, "
			+ "isbn text not null, title text not null, description text not null, prices text not null, color text not null, path text not null,"
			+ "size text not null);";
	
	private static String ORDER_BY = "prices";
	
	private static String ORDER_BY_DESC = "prices DESC";

	
	private final Context context;

	private DatabaseHelper DBHelper;

	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	public void setDateBaseCreate(String dbc){
		DATABASE_CREATE = dbc;		
	}
	
	public void setDateBaseName (String dbname){
		DATABASE_NAME=dbname;
	}
	
	public void setDateBaseTableName (String dbTableName){
		DATABASE_TABLE_DETAIL=dbTableName;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		DatabaseHelper(Context context){
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
           Log.i(TAG, "The database has been created ");
		}
	

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG,"");
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i(TAG,"");
			// TODO Auto-generated method stub

		}
	}
	
	//---打开数据库---

	public SQLiteDatabase open() throws SQLException{
		db = DBHelper.getWritableDatabase();
		db.execSQL(DATABASE_CREATE);
		return db;
	}

	//---关闭数据库---

	public void close()
	{
		DBHelper.close();
	}

	//---向数据库插入一个标题---

	public long insertTitle(String isbn, String title, String path, String description, String prices, String color, String size)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ISBN, isbn);
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_PATH, path);
		initialValues.put(KEY_DESCRIPTION, description);
		initialValues.put(KEY_PRICES, prices);
		initialValues.put(KEY_COLOR, color);
		initialValues.put(KEY_SIZE, size);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//---向数据库插入一个标题---

	public long insertDetailTitle(String isbn, String iddetail, String name, String category, String date, String fatherId)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ISBN, isbn);
		initialValues.put(KEY_ID_DETAIL, iddetail);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_CATEGORY, category);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_FATHER_ID, fatherId);
		return db.insert(DATABASE_TABLE_DETAIL, null, initialValues);
	}
	//---删除一个指定的标题---

	public boolean deleteTitle(long rowId)
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	//---删除所有的标题---

	public boolean cleanTitle()
	{
		Log.i(TAG, "Clean all the titles in this database!");
		return db.delete(DATABASE_TABLE, "*", null) > 0;
	}

	//---检索所有标题---
	public Cursor getAllTitles()
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ISBN,KEY_TITLE,KEY_DESCRIPTION,KEY_PRICES,KEY_COLOR,KEY_PATH,KEY_SIZE},null,null,null,null,null);
	}
	
	public Cursor getAllTitlesByPriceDown()
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ISBN,KEY_TITLE,KEY_DESCRIPTION,KEY_PRICES,KEY_COLOR,KEY_PATH,KEY_SIZE},null,null,null,null,ORDER_BY);
	}
	
	public Cursor getAllTitlesByPriceUp()
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ISBN,KEY_TITLE,KEY_DESCRIPTION,KEY_PRICES,KEY_COLOR,KEY_PATH,KEY_SIZE},null,null,null,null,ORDER_BY_DESC);
	}
		
		//---检索一个指定的标题---
	public Cursor getTitleFromImagesDB(String title) throws SQLException
	{
		Cursor mCursor =db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID,KEY_ISBN,KEY_TITLE,KEY_DESCRIPTION,KEY_PRICES,KEY_COLOR,KEY_PATH,KEY_SIZE},KEY_TITLE + "=" + title,null,null,null,null,null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor ;

	}
	
	//---检索一个指定的标题从DetailImages Table里面---
	public Cursor getTitleFromDetailImages(String fatherId) throws SQLException
	{
		Cursor mCursor =db.query(true, DATABASE_TABLE_DETAIL, new String[] {
				KEY_ROWID,KEY_ISBN,KEY_ID_DETAIL,KEY_NAME,KEY_CATEGORY,KEY_DATE,KEY_FATHER_ID},KEY_FATHER_ID + "='" + fatherId+"'",null,null,null,null,null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor ;

	}

	//---更新一个标题---
	public boolean updateTitle(long rowId, String isbn,String title, String path)
	{
		ContentValues args = new ContentValues();

		args.put(KEY_ISBN, isbn);
		args.put(KEY_TITLE, title);
		args.put(KEY_PATH, path);

		return db.update(DATABASE_TABLE, args,KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	//Register Table Handler.
	public long insertRegisterTitle(String name,String phone, String password, String repassword, String email)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PHONE, phone);
		initialValues.put(KEY_PASSWORD, password);
		initialValues.put(KEY_REPASSWORD, repassword);
		initialValues.put(KEY_EMAIL, email);
		return db.insert(DATABASE_TABLE_DETAIL, null, initialValues);
	}
	
	public Cursor getTitleFromRegisterTable(String name,String password) throws SQLException
	{
		Cursor mCursor =db.query(true, DATABASE_TABLE_DETAIL, new String[] {
				KEY_ROWID,KEY_NAME,KEY_PHONE,KEY_PASSWORD,KEY_REPASSWORD,KEY_EMAIL},KEY_NAME + "='" + name+"' and "+KEY_PASSWORD +"='" + password+"'",null,null,null,null,null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor ;

	}
	

}
