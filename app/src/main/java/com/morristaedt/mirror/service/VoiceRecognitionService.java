package com.morristaedt.mirror.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.morristaedt.mirror.R;

/**
 * Created by kdeloria on 9/15/2015.
 */
public class VoiceRecognitionService extends Service implements RecognitionListener {

    private SpeechRecognizer speechRecognizer;

    @Override
    public void onCreate() {
        super.onCreate();
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        this.speechRecognizer.setRecognitionListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent recognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // Not to mistake it as the intent that called this service (MirrorActivity)
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getString(R.string.voice_recognition_extra_package));
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        speechRecognizer.startListening(recognitionIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.speechRecognizer.stopListening();
        this.speechRecognizer.destroy();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.i("VoiceRecognition", "onReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i("onBufferReceived", "onBufferReceived");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i("onRmsChanged", "onRmsChanged");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i("onBufferReceived", "onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.i("onEndOfSpeech", "onEndOfSpeech");
    }

    @Override
    public void onError(int error) {
        Log.i("onError", "onError");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i("onResults", "onResults");
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.i("onPartialResults", "onPartialResults");
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.i("onEvent", "onEvent");
    }
}
