package com.uservoice.uservoicesdk.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.uservoice.uservoicesdk.R;
import com.uservoice.uservoicesdk.activity.ContactActivity;

public class UnhelpfulDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle(R.string.uv_unhelpful_article_message_question);
		
		builder.setNegativeButton(R.string.uv_no, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		builder.setPositiveButton(R.string.uv_yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getActivity().startActivity(new Intent(getActivity(), ContactActivity.class));
				dialog.cancel();
			}
		});
		
		return builder.create();
	}
}
