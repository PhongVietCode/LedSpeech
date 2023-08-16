package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Speech extends Fragment {

    public Speech() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //---------

    //----------
    SpeechRecognizer speechRecognizer;
    MaterialTextView txtResult;
    MaterialButton btnRecord;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speech, container, false);
        // init item
        btnRecord = view.findViewById(R.id.btnRecord);
        txtResult = view.findViewById(R.id.resultTXT);
        txtResult.setMaxLines(3);
        txtResult.setMovementMethod(new ScrollingMovementMethod());
        txtResult.setOverScrollMode(View.TEXT_ALIGNMENT_TEXT_START);
        String[] languages = getResources().getStringArray(R.array.language);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),R.layout.dropdown_item,languages);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.change_language);
        autoCompleteTextView.setAdapter(adapter);
        // speechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                txtResult.setText("Waiting...");

            }

            @Override
            public void onError(int i) {

                if(i == SpeechRecognizer.ERROR_NETWORK){
                    txtResult.setText("Network error");

                }
                else if(i == SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE){
                    txtResult.setText("Unavailable language");
                }
                else if ((i == SpeechRecognizer.ERROR_NO_MATCH)
                        || (i == SpeechRecognizer.ERROR_SPEECH_TIMEOUT))
                {
                    txtResult.setText("Didn't get that ;(");
                }
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String result = data.get(0);
                txtResult.setText(result);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        Intent speech =  new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_US");
        //-------------
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtResult.setText("Waiting for you...");

                switch (i){
                    case 0:
                        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_US");
                        break;
                    case 1:
                        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi_VN");

                        break;
                    case 2:
                        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de_DE");

                        break;
                    case 3:
                        speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi_IN");

                        break;
                }
            }
        });
        //-------------
        btnRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txtResult.setText("Lisening...");
                        speechRecognizer.startListening(speech);
                        break;
                    case MotionEvent.ACTION_UP:
                        txtResult.setText("Just a second...");
                        speechRecognizer.stopListening();
                        break;
                }
                return true;
            }
        });
        if(getActivity() != null){
            Button btnMes = getActivity().findViewById(R.id.btnMessage);
            if(btnMes != null){
                btnMes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(getActivity() != null){
                            EditText edtMes = getActivity().findViewById(R.id.edtMessage);
                            if(edtMes != null){
                                Utils.closeKeyBoard(getContext(),getActivity());
                                String data = edtMes.getText().toString();
                                if(!data.equals("")){
                                    //todo: sent to board
                                }
                                else{
                                    Toast.makeText(getContext(),"Empty message",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                    }
                });
            }
        }
        //------
        return view;
    }

}