/**
 * 
 */
package Dolphin.src.Dialog;

import Dolphin.src.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * @author Administrator
 * 
 */
public class HelpDialog extends AlertDialog {
	public HelpDialog(Context context) {
		super(context);
		final View view = getLayoutInflater().inflate(R.layout.help_dialog,
				null);
		//2014-3-30, The setButton has been rewritten there.
		setButton(DialogInterface.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Do nothing.
			}
		});
		setIcon(R.drawable.icon2);
		setTitle("Dolphin version:1.0");
		setView(view);
	}
}
