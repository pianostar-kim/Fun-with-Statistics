// https://www.youtube.com/watch?v=ARezg1D9Zd0

package com.example.funwithstatistics2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddIntegerDialog extends AppCompatDialogFragment {
    private EditText dialogEdtTxtAddInt;
    private AddIntegerDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_integer, null);
        dialogEdtTxtAddInt = dialogView.findViewById(R.id.dialogEdtTxtAddInt);
        alert.setView(dialogView)
                .setPositiveButton("Add Integer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            int integerToAdd = Integer.parseInt(dialogEdtTxtAddInt.getText().toString());
                            listener.useIntegerToAdd(integerToAdd);
                            Toast.makeText(getActivity(), integerToAdd + " added successfully.", Toast.LENGTH_SHORT).show();
                        }
                        catch (NumberFormatException e) {
                            Toast.makeText(getActivity(), "Error: Must enter an integer.", Toast.LENGTH_SHORT).show();;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return alert.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddIntegerDialogListener) (context);
        }
        catch (ClassCastException e) {
            Toast.makeText(context, "An error occurred. Please try again later. If this error persists, contact the developer of this app.", Toast.LENGTH_SHORT).show();
        }
    }

    public interface AddIntegerDialogListener {
        void useIntegerToAdd(int integerToAdd);
    }
}
