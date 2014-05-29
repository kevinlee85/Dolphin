/**
 * 
 */
package Dolphin.src.Dialog;

import Dolphin.src.R;
import Dolphin.src.DatabaseProvider.DBAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import Dolphin.src.Util.DisplayUtils;

/**
 * @author Administrator
 * 
 */
public class RegisterDialog extends Dialog implements OnClickListener {

	Context myContext = null;
	private static String tag = "RegisterDialog";
	EditText add_name = null;
	EditText add_phone = null;
	EditText add_password = null;
	EditText add_repassword = null;
	EditText add_email = null;
//	private String st_add_name = null;
//	private String st_add_phone = null;
//	private String st_add_password = null;
//	private String st_add_repassword = null;
//	private String st_add_email = null;
	Dialog dialogAdd;
	/**
	 * @param context
	 */
	public RegisterDialog(Context context) {
		super(context);
		myContext = context;
		// TODO Auto-generated constructor stub
	}

	// 2010-10-24 add for specific test purpose
	public Dialog createDialogAdd() {
		View viewAdd = this.getLayoutInflater().inflate(
				R.layout.register_dialog, null);

	    dialogAdd = new Dialog(myContext);
		Log.i(tag, "createDialogAdd");
		dialogAdd.setContentView(viewAdd);
		dialogAdd.setTitle("Welcome, Input your info here:");

		add_name = (EditText) viewAdd.findViewById(R.id.add_name);
		add_phone = (EditText) viewAdd.findViewById(R.id.add_phone);
		add_password = (EditText) viewAdd.findViewById(R.id.add_password);
		add_repassword = (EditText) viewAdd.findViewById(R.id.add_repassword);
		add_email = (EditText) viewAdd.findViewById(R.id.add_email);

		Button add_ok = (Button) viewAdd.findViewById(R.id.add_ok);
		Button add_cancel = (Button) viewAdd.findViewById(R.id.add_cancel);
		add_ok.setOnClickListener(this);
		add_ok.setOnClickListener(this);
		
		//Add more settings on the diaglog to make it looks more gorgeous.2014-3-23
		Window dialogWindow = dialogAdd.getWindow();  
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();  
        lp.width = DisplayUtils.getDisplayWidthPixels(myContext) - 10; //Use the screen Size to minus 60 px.
        dialogWindow.setAttributes(lp);  
        //end
        
		add_cancel.setOnClickListener(this);
		return dialogAdd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_ok: {
			   String[] data = {  
					   add_name.getText().toString(),  
                       add_phone.getText().toString(),   
                       add_password.getText().toString(),   
                       add_repassword.getText().toString(), 
                       add_email.getText().toString()
               }; 
			if(!data[0].equals(null)&&!data[1].equals(null)&&!data[2].equals(null)&&!data[3].equals(null)&&!data[3].equals(null)&&!data[4].equals(null))			
			{				
				if(data[2].toString()!=data[3].toString())
				{
					AlertDialog dl = new AlertDialog.Builder(myContext).setIcon(
							android.R.drawable.ic_dialog_alert).setTitle(
							"PASSWORD IS NOT EQUIRE TO REPASSWORD").setPositiveButton("Confirm",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									add_password.setText("");
									add_repassword.setText("");
									Log.i(tag, "");

								}
							}).setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Log.i(tag, "");
								}
							}).create();
						dl.show();
						return;
					
				}
				Log.i(tag,"The input date is "+data[1].toString()+" "+data[2].toString());
				DBAdapter adapter = new DBAdapter(myContext);
				final String DATABASE_TABLE_NAME = "register";
				adapter.setDateBaseTableName(DATABASE_TABLE_NAME);
				adapter.open();
				adapter.insertRegisterTitle(data[0].toString(), data[1].toString(), data[2].toString(), data[3].toString(), data[4].toString());
				adapter.close();
				Log.i(tag,"The input manipulate is done");
			}
			
			dialogAdd.dismiss();
			break;
		}
		case R.id.add_cancel: {
			Log.i(tag,"Cancel button has been pressed");
			
			dialogAdd.dismiss();
			break;
		}
		}

	}
}
