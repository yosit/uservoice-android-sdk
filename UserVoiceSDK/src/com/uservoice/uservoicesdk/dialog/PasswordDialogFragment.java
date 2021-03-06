package com.uservoice.uservoicesdk.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.uservoice.uservoicesdk.R;
import com.uservoice.uservoicesdk.Session;
import com.uservoice.uservoicesdk.model.AccessToken;
import com.uservoice.uservoicesdk.ui.DefaultCallback;

@SuppressLint("ValidFragment")
public class PasswordDialogFragment extends DialogFragment {

	private final Runnable callback;

	public PasswordDialogFragment(Runnable callback) {
		this.callback = callback;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.uv_password_dialog_title);
		View view = getActivity().getLayoutInflater().inflate(R.layout.password_dialog, null);
		final EditText password = (EditText) view.findViewById(R.id.password);
		builder.setView(view);
		builder.setNegativeButton(R.string.uv_cancel, null);
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, int which) {
				AccessToken.authorize(Session.getInstance().getEmail(), password.getText().toString(), new DefaultCallback<AccessToken>(getActivity()) {
					@Override
					public void onModel(AccessToken model) {
						Session.getInstance().setAccessToken(model);
						callback.run();
					}
				});
			}
		});
        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return dialog;
	}

}
