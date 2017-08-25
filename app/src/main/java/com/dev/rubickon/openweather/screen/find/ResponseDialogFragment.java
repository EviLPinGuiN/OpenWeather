package com.dev.rubickon.openweather.screen.find;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.dev.rubickon.openweather.R;
import com.dev.rubickon.openweather.utils.Constants;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class ResponseDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        String value = mArgs.getString(Constants.DIALOG_KEY);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(value)
                .setPositiveButton(getResources().getString(R.string.btn_success_response), (dialog, id) -> dialog.cancel());
        return builder.create();
    }


}
