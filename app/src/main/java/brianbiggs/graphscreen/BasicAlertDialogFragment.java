package brianbiggs.graphscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by biggs_000 on 8/7/2015.
 */
public class BasicAlertDialogFragment extends DialogFragment{
//    Intent intent = getIntent();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Formula goes here, ok?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

    /*public Intent getIntent() {
        Bundle bundle = intent.getBundleExtra("numbers");
        String param0 = intent.getStringExtra("param0");
        String param1 = intent.getStringExtra("param1");

        return intent;
    }*/
}
