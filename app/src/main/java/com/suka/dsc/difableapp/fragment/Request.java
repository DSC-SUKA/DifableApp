package com.suka.dsc.difableapp.fragment;


import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.suka.dsc.difableapp.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Request extends Fragment {

    String setOutputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/request.amr";
    MediaRecorder mediaRecorder = null;

    public Request() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blind_home_request_fragment, container, false);
        setAudioRecordState();
        final ImageButton recordButton = view.findViewById(R.id.record_button);

        recordButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    startAudioRecord();
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    stopAudioRecord();
                }
                return false;
            }
        });


        return view;
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