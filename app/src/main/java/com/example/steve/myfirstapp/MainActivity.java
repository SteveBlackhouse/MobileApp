package com.example.steve.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_input;
    Button btn_showdata;
    TextView tv_data;
    String str_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the widgets reference from XML layout
        final EditText et_input = (EditText) findViewById(R.id.et_input);
        Button btn = (Button) findViewById(R.id.btn);
        btn_showdata = (Button) findViewById(R.id.btn_showdata);
        tv_data = (TextView) findViewById(R.id.tv_data);

        btn_showdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = et_input.getText().toString();
                        tv_data.setText("Hello " + name);
                    }
                }
        );

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_input.setText("");
                    }
                }
        );

    }

}







