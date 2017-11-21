package com.iteso.lenguajesformales.beans;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Palaf on 19/11/2017.
 */

public  class ResultDialogFragment extends android.support.v4.app.DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(getArguments().getCharSequenceArray("RESULTS"),null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                }).setTitle("Results:");
        return builder.create();
    }
    public static ResultDialogFragment newInstance() {
        return new ResultDialogFragment();
    }

}