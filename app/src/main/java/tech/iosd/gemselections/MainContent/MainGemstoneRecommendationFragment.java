package tech.iosd.gemselections.MainContent;

/**
 * Created by kushalgupta on 21/12/17.
 */
import java.text.DecimalFormat;


import android.app.DatePickerDialog;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
;
import android.widget.EditText;

import android.widget.Toast;
import android.widget.DatePicker;



import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Calendar;
import java.util.Locale;

import tech.iosd.gemselections.R;

/**
 * Created by kushalgupta on 21/12/17.
 */

public class MainGemstoneRecommendationFragment extends Fragment {

    private YouTubePlayer YPlayer;
    private static final String DEVELOPER_KEY = "AIzaSyBKlHdEkS-X7Vb2mW2qQSlF1TOxKzWpSU8";
    private static final int RECOVERY_REQUEST = 1;

    private EditText dateOfBirth,  phoneNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gemstone_recommendation, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        youTubePlayerFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

                if (!wasRestored) {
                    YPlayer = player;
                    YPlayer.cueVideo("DRZfRX9YKmU");
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                if (arg1.isUserRecoverableError()) {
                    arg1.getErrorDialog(getActivity(), RECOVERY_REQUEST).show();
                } else {
                    String error = String.format(getString(tech.iosd.gemselections.R.string.player_error), arg1.toString());
                    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.recommendation_video, youTubePlayerFragment).commit();

        int f=0;
        final EditText name=view.findViewById(R.id.et_name);
        final EditText message=view.findViewById(R.id.et_message);
        final EditText placeOfBirth=view.findViewById(R.id.et_place_of_birth);
        final EditText timeOfBirth=view.findViewById(R.id.time_of_birth);
        dateOfBirth = (EditText) view.findViewById(R.id.et_date_of_birth);
        phoneNumber = (EditText) view.findViewById(R.id.et_phone_number);
        Button send=view.findViewById(R.id.sendMailBtn);

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");
                //Toast.makeText(getActivity().getApplicationContext(),"dp",Toast.LENGTH_SHORT).show();
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=1;
                String sendMailName=name.getText().toString();
                String sendtimeOfBirth=timeOfBirth.getText().toString();
                String sendMessageMail=message.getText().toString();
                String sendplaceOfBirth=placeOfBirth.getText().toString();
                String sendDateOfBirth= dateOfBirth.getText().toString();
                String sendPhoneNumber = phoneNumber.getText().toString();
                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                 //   intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, " Gemstone Recommendation user name:" + " " + sendMailName);
                    intent.putExtra(Intent.EXTRA_TEXT, "Place of Birth:" + sendplaceOfBirth + "\n"+"Time of birth:"+sendtimeOfBirth +"\n"+"Phone Number: "+sendPhoneNumber+"\n" +"Date of Birth: " +sendDateOfBirth + "Questions:" + sendMessageMail);
                    intent.setData(Uri.parse("mailto:aradikhanna@gmail.com"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(intent, "Mail Via..."));

                    name.setText(null);
                    placeOfBirth.setText(null);
                    message.setText(null);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Oops, you don't seem to have any app installed to send this email ", Toast.LENGTH_SHORT).show();
                }

            }
        });






        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Gemstone Recommendation");
    }


}





