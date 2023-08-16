package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils;

public class Text extends Fragment {

    public Text() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        TextToSpeech textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.ERROR_NETWORK){
                    Toast.makeText(getContext(), "Network error",Toast.LENGTH_SHORT).show();
                }
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
                                    textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH,null);
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

        return view;
    }
}