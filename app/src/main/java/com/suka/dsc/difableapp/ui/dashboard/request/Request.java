package com.suka.dsc.difableapp.ui.dashboard.request;


import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.utils.PermissionManager;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Request extends Fragment {

    String setOutputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/request.amr";
    MediaRecorder mediaRecorder = null;
    private String[] permissionNeeded = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE_RECORD_ACTIVITY = 111;

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
                if (PermissionManager.hasPermissions(getContext(), permissionNeeded)){
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        Toast.makeText(getContext(), "Record Started", Toast.LENGTH_SHORT).show();
                        // setAudioRecordState();
                        // startAudioRecord();
                    }else if (event.getAction() == MotionEvent.ACTION_UP){
                        Toast.makeText(getContext(), "Record Finished", Toast.LENGTH_SHORT).show();
                        // stopAudioRecord();
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
        if (!PermissionManager.hasPermissions(getContext(), permissionNeeded)){
            requestPermission();
        }
    }

    private void requestPermission() {
        PermissionManager.requestPermissions(getActivity(), permissionNeeded, REQUEST_CODE_RECORD_ACTIVITY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_RECORD_ACTIVITY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), R.string.toast_permission_granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), R.string.toast_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setAudioRecordState(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setAudioSamplingRate(16000);
        mediaRecorder.setOutputFile(setOutputFile);
    }

    private void startAudioRecord(){
        try {
            mediaRecorder.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    private void stopAudioRecord(){
        try{
            mediaRecorder.stop();
        }catch (RuntimeException ignored){

        }
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void sendAudioData(){

    }
}