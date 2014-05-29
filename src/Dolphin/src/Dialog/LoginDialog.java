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
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Administrator
 * 
 */
public class LoginDialog extends Dialog implements OnClickListener {

	Context myContext = null;
	private static String tag = "LoginDialog";
	public Dialog loginDialog = null;
	private EditText login_name = null;
	private EditText login_password = null;

	/**
	 * @param context
	 */
	public LoginDialog(Context context) {
		super(context);
		myContext = context;
		// TODO Auto-generated constructor stub
	}

	public Dialog createDialog() {
		View viewAdd = this.getLayoutInflater().inflate(R.layout.login_dialog,
				null);

		loginDialog = new Dialog(myContext);
		loginDialog.setContentView(viewAdd);
		loginDialog.setTitle("LOGIN");

		login_name = (EditText) viewAdd.findViewById(R.id.login_name);
		login_password = (EditText) viewAdd.findViewById(R.id.login_password);
		Button add_ok = (Button) viewAdd.findViewById(R.id.add_ok);
		Button add_cancel = (Button) viewAdd.findViewById(R.id.add_cancel);
		add_ok.setOnClickListener(this);
		add_ok.setOnClickListener(this);

		add_cancel.setOnClickListener(this);
		return loginDialog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.add_ok: {
			String[] data = { login_name.getText().toString(),
					login_password.getText().toString() };
			if (!data[0].equals(null) && !data[1].equals(null)) {
				DBAdapter adapter = new DBAdapter(myContext);
				final String DATABASE_TABLE_NAME = "register";
				adapter.setDateBaseTableName(DATABASE_TABLE_NAME);
				adapter.open();
				Cursor cursor = adapter.getTitleFromRegisterTable(data[0], data[1]);
				if (cursor.getCount() == 0) {
					AlertDialog dl = new AlertDialog.Builder(myContext)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("The User Name or Password may be wrong")
					.setPositiveButton("Confirm",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialog,
										int whichButton) {
									Log.i(tag, "");

								}
							}).create();
							dl.show();
					cursor.close();
					adapter.close();
				}
				cursor.close();
				adapter.close();
				Log.i(tag, "The input manipulate is done");	

				}
			else{
				AlertDialog dl = new AlertDialog.Builder(myContext)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("User Name and Passowrd can not be empty")
				.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog,
									int whichButton) {
								Log.i(tag, "");

							}
						}).setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog,
									int whichButton) {
								Log.i(tag, "");
							}
						}).create();
						dl.show();
						return;
			}
				
			loginDialog.dismiss();
			break;
		}
		case R.id.add_cancel: {
			Log.i(tag, "Cancel button has been pressed");

			loginDialog.dismiss();
			break;
		}
		}

	}

}
