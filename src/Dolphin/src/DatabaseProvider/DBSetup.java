/**
 * 
 */
package Dolphin.src.DatabaseProvider;

import android.content.Context;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class DBSetup {
	Context ctx;
	
	public void DBsetup(){
		//none;
	}
	public DBSetup(Context ctx_1){
		ctx = ctx_1;
		//none;
	}
	
	public int mainDBSetup(){
		 DBAdapter adapter = new DBAdapter(ctx);
//		 Below codes are used to setup database for test use, which will be
//		 opened when data need to be modifilied in database.
		 String description = "100$ This cap is disigned for the boy.";
		 String color = "Black";
		 String size = "One";
		 adapter.open();
		 adapter.insertTitle("imagerres", "mlb201010yj001", "mlb201010yj001", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj002", "mlb201010yj002", "150$ washing stud slim fit PT"+description, "150$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj003", "mlb201010yj003", "120$ leather pocket slim fit jean"+description, "120$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj004", "mlb201010yj004", "130$ converse FFT401U BK JP"+description, "130$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj005", "mlb201010yj005", "110$ classic beige wool tie"+description, "110$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj006", "mlb201010yj006", "120$ coduroy patch boxy fit MTM"+description, "120$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj007", "mlb201010yj007", "100$ beige check slim fit NB"+description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj008", "mlb201010yj008", "110$ twist knit sleeve stripe knit T"+description, "110$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj009", "mlb201010yj009", "150$ check pocket semi baggy jean"+description, "150$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj010", "mlb201010yj010", "120$ real loose fit soft CD"+description, "120$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj011", "mlb201010yj011", "200$ two color wool muffler"+description, "200$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj012", "mlb201010yj012", description, "210$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj013", "mlb201010yj013", description, "230$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj014", "mlb201010yj014", description, "250$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj015", "mlb201010yj015", description, "900$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj016", "mlb201010yj016", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj017", "mlb201010yj017", description, "120$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj018", "mlb201010yj018", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj019", "mlb201010yj019", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj020", "mlb201010yj020", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj021", "mlb201010yj021", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj022", "mlb201010yj022", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj023", "mlb201010yj023", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj024", "mlb201010yj024", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj025", "mlb201010yj025", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj026", "mlb201010yj026", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj027", "mlb201010yj027", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj028", "mlb201010yj028", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj029", "mlb201010yj029", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj030", "mlb201010yj030", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj031", "mlb201010yj031", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj032", "mlb201010yj032", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj033", "mlb201010yj033", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj034", "mlb201010yj034", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj035", "mlb201010yj035", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj035", "mlb201010yj036", description, "100$", color, size);
		 adapter.insertTitle("imagerres", "mlb201010yj035", "mlb201010yj037", description, "100$", color, size);
		 adapter.close();
		return 1;
	}
	public int detailDBSetup(){
		DBAdapter adapter = new DBAdapter(ctx);
		final String DATABASE_TABLE_DETAIL = "imageresdetail";
		final String DATABASE_CREATE_DETAIL = "create table IF NOT EXISTS "+DATABASE_TABLE_DETAIL+"(_id integer primary key autoincrement, "
		+ "isbn text not null, idDetail text not null, "
		+ "name text not null, "
		+ "category text not null,"
		+ "date text not null,"
		+ "fatherId text not null"
		+");";
		
		adapter.setDateBaseCreate(DATABASE_CREATE_DETAIL); // Change the database create function command.
		adapter.setDateBaseTableName(DATABASE_TABLE_DETAIL); // Change the table name.
		adapter.open();
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj001", "mlb", "detail", "2010/10/20", "mlb201010yj001");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj002", "mlb", "detail", "2010/10/20", "mlb201010yj002");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj003", "mlb", "detail", "2010/10/20", "mlb201010yj003");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj004", "mlb", "detail", "2010/10/20", "mlb201010yj004");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj005", "mlb", "detail", "2010/10/20", "mlb201010yj005");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj006", "mlb", "detail", "2010/10/20", "mlb201010yj006");
		
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj007", "mlb", "detail", "2010/10/20", "mlb201010yj007");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj007de1", "mlb", "detail", "2010/10/20", "mlb201010yj007");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj007de2", "mlb", "detail", "2010/10/20", "mlb201010yj007");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj007de3", "mlb", "detail", "2010/10/20", "mlb201010yj007");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj007de4", "mlb", "detail", "2010/10/20", "mlb201010yj007");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj008", "mlb", "detail", "2010/10/20", "mlb201010yj008");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj008de1", "mlb", "detail", "2010/10/20", "mlb201010yj008");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj008de2", "mlb", "detail", "2010/10/20", "mlb201010yj008");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj008de3", "mlb", "detail", "2010/10/20", "mlb201010yj008");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj008de4", "mlb", "detail", "2010/10/20", "mlb201010yj008");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj009", "mlb", "detail", "2010/10/20", "mlb201010yj009");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj009de1", "mlb", "detail", "2010/10/20", "mlb201010yj009");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj009de2", "mlb", "detail", "2010/10/20", "mlb201010yj009");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj009de3", "mlb", "detail", "2010/10/20", "mlb201010yj009");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj009de4", "mlb", "detail", "2010/10/20", "mlb201010yj009");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj010", "mlb", "detail", "2010/10/20", "mlb201010yj010");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj011", "mlb", "detail", "2010/10/20", "mlb201010yj011");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj011de1", "mlb", "detail", "2010/10/20", "mlb201010yj011");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj011de2", "mlb", "detail", "2010/10/20", "mlb201010yj011");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj011de3", "mlb", "detail", "2010/10/20", "mlb201010yj011");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj011de4", "mlb", "detail", "2010/10/20", "mlb201010yj011");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj012", "mlb", "detail", "2010/10/20", "mlb201010yj012");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj013", "mlb", "detail", "2010/10/20", "mlb201010yj013");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj013de1", "mlb", "detail", "2010/10/20", "mlb201010yj013");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj013de2", "mlb", "detail", "2010/10/20", "mlb201010yj013");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj013de3", "mlb", "detail", "2010/10/20", "mlb201010yj013");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj013de4", "mlb", "detail", "2010/10/20", "mlb201010yj013");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj014", "mlb", "detail", "2010/10/20", "mlb201010yj014");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj015", "mlb", "detail", "2010/10/20", "mlb201010yj015");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj015de1", "mlb", "detail", "2010/10/20", "mlb201010yj015");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj015de2", "mlb", "detail", "2010/10/20", "mlb201010yj015");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj015de3", "mlb", "detail", "2010/10/20", "mlb201010yj015");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj015de4", "mlb", "detail", "2010/10/20", "mlb201010yj015");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj016", "mlb", "detail", "2010/10/20", "mlb201010yj016");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj016de1", "mlb", "detail", "2010/10/20", "mlb201010yj016");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj016de2", "mlb", "detail", "2010/10/20", "mlb201010yj016");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj016de3", "mlb", "detail", "2010/10/20", "mlb201010yj016");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj016de4", "mlb", "detail", "2010/10/20", "mlb201010yj016");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj017", "mlb", "detail", "2010/10/20", "mlb201010yj017");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj018", "mlb", "detail", "2010/10/20", "mlb201010yj018");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj019", "mlb", "detail", "2010/10/20", "mlb201010yj019");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj020", "mlb", "detail", "2010/10/20", "mlb201010yj020");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj020de1", "mlb", "detail", "2010/10/20", "mlb201010yj020");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj020de2", "mlb", "detail", "2010/10/20", "mlb201010yj020");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj020de3", "mlb", "detail", "2010/10/20", "mlb201010yj020");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj020de4", "mlb", "detail", "2010/10/20", "mlb201010yj020");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj021", "mlb", "detail", "2010/10/20", "mlb201010yj021");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj021de1", "mlb", "detail", "2010/10/20", "mlb201010yj021");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj021de2", "mlb", "detail", "2010/10/20", "mlb201010yj021");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj021de3", "mlb", "detail", "2010/10/20", "mlb201010yj021");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj021de4", "mlb", "detail", "2010/10/20", "mlb201010yj021");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj022", "mlb", "detail", "2010/10/20", "mlb201010yj022");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj022de1", "mlb", "detail", "2010/10/20", "mlb201010yj022");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj022de2", "mlb", "detail", "2010/10/20", "mlb201010yj022");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj022de3", "mlb", "detail", "2010/10/20", "mlb201010yj022");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj022de4", "mlb", "detail", "2010/10/20", "mlb201010yj022");
		
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj023", "mlb", "detail", "2010/10/20", "mlb201010yj023");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj023de1", "mlb", "detail", "2010/10/20", "mlb201010yj023");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj023de2", "mlb", "detail", "2010/10/20", "mlb201010yj023");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj023de3", "mlb", "detail", "2010/10/20", "mlb201010yj023");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj023de4", "mlb", "detail", "2010/10/20", "mlb201010yj023");
			
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj024", "mlb", "detail", "2010/10/20", "mlb201010yj024");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj025", "mlb", "detail", "2010/10/20", "mlb201010yj025");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj026", "mlb", "detail", "2010/10/20", "mlb201010yj026");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj027", "mlb", "detail", "2010/10/20", "mlb201010yj027");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj028", "mlb", "detail", "2010/10/20", "mlb201010yj028");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj029", "mlb", "detail", "2010/10/20", "mlb201010yj029");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj031", "mlb", "detail", "2010/10/20", "mlb201010yj030");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj032", "mlb", "detail", "2010/10/20", "mlb201010yj031");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj033", "mlb", "detail", "2010/10/20", "mlb201010yj032");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj034", "mlb", "detail", "2010/10/20", "mlb201010yj034");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj035", "mlb", "detail", "2010/10/20", "mlb201010yj035");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj036", "mlb", "detail", "2010/10/20", "mlb201010yj036");
		adapter.insertDetailTitle("imageresdetail", "mlb201010yj037", "mlb", "detail", "2010/10/20", "mlb201010yj037");
		
		
		
		Log.i("DATABASE","incert manipulate is done");
		adapter.close();
		return 1;
	}
	
	public int registerDBSetup(){
		DBAdapter adapter = new DBAdapter(ctx);
		final String DATABASE_TABLE_NAME = "register";
		final String DATABASE_CREATE_DETAIL = "create table IF NOT EXISTS "+DATABASE_TABLE_NAME+"(_id integer primary key autoincrement, "
		+ "name text not null, phone text not null, "
		+ "password text not null, "
		+ "repassword text not null,"
		+ "email text not null"
		+");";
		
		adapter.setDateBaseCreate(DATABASE_CREATE_DETAIL); // Change the database create function command.
		adapter.setDateBaseTableName(DATABASE_TABLE_NAME); // Change the table name.
		adapter.open();
		adapter.close();
		return 1;
	}
	

}
