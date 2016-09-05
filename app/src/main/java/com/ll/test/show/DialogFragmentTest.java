package com.ll.test.show;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by LL on 2016/9/5.
 */
public class DialogFragmentTest extends DialogFragment {
    private static String CURRENT_TIME = "CURRENT_TIME";

    public static DialogFragmentTest newInstance(String currentTime) {
        // Create a new Fragment instance with the specified
        // parameters.
        DialogFragmentTest fragment = new DialogFragmentTest();
        Bundle args = new Bundle();
        args.putString(CURRENT_TIME, currentTime);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the new Dialog using the AlertBuilder.
        AlertDialog.Builder timeDialog =
                new AlertDialog.Builder(getActivity());

        // Configure the Dialog UI.
        timeDialog.setTitle("The Current Time Is...");
        timeDialog.setMessage(getArguments().getString(CURRENT_TIME));

        // Return the configured Dialog.
        return timeDialog.create();
    }

    private void showDialogFragment() {
        String dateString = "April 1 2012";

        /**
         * Listing 10-25: Displaying a Dialog Fragment
         */
        String tag = "my_dialog";
        DialogFragment myFragment =
                DialogFragmentTest.newInstance(dateString);
//显示
        myFragment.show(getFragmentManager(), tag);
//        myFragment.dismiss();
    }


//  /**
//   * Listing 10-26: Using the On Create View handler
//    自定义
//   */
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//    Bundle savedInstanceState) {
//
//    // Inflate the Dialog's UI.
//    View view = inflater.inflate(R.layout.dialog_view, container, false);
//
//    // Update the Dialog's contents.
//    TextView text = (TextView)view.findViewById(R.id.dialog_text_view);
//    text.setText("This is the text in my dialog");
//
//    return view;
//  }
}
