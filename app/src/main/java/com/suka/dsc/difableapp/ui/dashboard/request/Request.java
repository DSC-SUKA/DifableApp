package com.suka.dsc.difableapp.ui.dashboard.request;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.model.UserData;
import com.suka.dsc.difableapp.utils.PermissionManager;
import com.suka.dsc.difableapp.utils.SessionManager;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Request extends Fragment implements RequestView{

    //    String setOutputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/request.amr";
    private String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/request.3gp";
    private MediaRecorder mediaRecorder = null;
    private String[] permissionNeeded = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE_RECORD_ACTIVITY = 111;
    private SessionManager mSessionManager;
    private UserData userData;
    private RequestPresenter mPresenter;

    public Request() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blind_home_request_fragment, container, false);
        final ImageButton recordButton = view.findViewById(R.id.record_button);

        recordButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (PermissionManager.hasPermissions(getContext(), permissionNeeded)) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), R.string.toast_record_started, Toast.LENGTH_SHORT).show();
                        setAudioRecordState();
                        startAudioRecord();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Toast.makeText(getContext(), R.string.toast_record_finished, Toast.LENGTH_SHORT).show();
                        stopAudioRecord();
                        showDialog(getContext());
                    }
                } else {
                    Toast.makeText(getContext(), R.string.toast_need_permission, Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                return false;
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!PermissionManager.hasPermissions(getContext(), permissionNeeded)) {
            requestPermission();
        }

        mPresenter = new RequestPresenter(this);
        mSessionManager = new SessionManager(getContext());
        userData = mSessionManager.getUserData();
    }

    private void requestPermission() {
        PermissionManager.requestPermissions(getActivity(), permissionNeeded, REQUEST_CODE_RECORD_ACTIVITY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_RECORD_ACTIVITY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), R.string.toast_permission_granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), R.string.toast_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setAudioRecordState() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        mediaRecorder.setAudioSamplingRate(16000);
        mediaRecorder.setOutputFile(outputPath);
    }

    private void startAudioRecord() {
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    private void stopAudioRecord() {
        try {
            mediaRecorder.stop();
        } catch (RuntimeException ignored) {

        }
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void sendAudioData() {
        mPresenter.sendRequest(outputPath, userData);
    }

    private void showDialog(final Context context) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_send_request);

        RelativeLayout dimBackground = (RelativeLayout) dialog.findViewById(R.id.dim_background_send_request);
        dimBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnYes = (Button) dialog.findViewById(R.id.btn_send_request_dialog_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                sendAudioData();
                Toast.makeText(context, "Sending your request...", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNo = (Button) dialog.findViewById(R.id.btn_send_request_dialog_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void onSuccessSendAudio(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorSendAudio(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}