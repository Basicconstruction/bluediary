package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class AddLockDialog extends DialogFragment {
    public interface MyDialogInterface {
        public void onDialogPositiveClick(View layouts);
        public void onDialogNegativeClick(View layouts);
    }

    public static class InterfaceUtils{
        public static String passwordText;
        static boolean isLocked;
        public InterfaceUtils(String passwordText,boolean isLocked){
            InterfaceUtils.passwordText = passwordText;
            InterfaceUtils.isLocked = isLocked;
        }
    }

    MyDialogInterface listener;
    InterfaceUtils interfaceUtils;
    private View layouts;
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try{
            listener = (MyDialogInterface)context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        layouts = inflater.inflate(R.layout.add_dialog,null);
        builder.setView(layouts)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText password = layouts.findViewById(R.id.password);
                        Log.d("TAG", "onClick: "+password.getText());
                        InterfaceUtils.passwordText = password.getText().toString();
                        InterfaceUtils.isLocked = true;
                        listener.onDialogPositiveClick(layouts);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(layouts);
                        Objects.requireNonNull(AddLockDialog.this.getDialog()).cancel();
                    }
                });
        return builder.create();
    }

}

