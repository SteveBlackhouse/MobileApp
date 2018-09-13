package com.example.steve.myfirstapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private TextView messageLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.et_input);
        messageLabel = findViewById(R.id.tv_data);
        initShowNameButton();
        initClearButton();
        // Get the widgets reference from XML layout
    }
    public void initShowNameButton() {
        findViewById(R.id.btn_showdata).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateHelloMessage();
                    }
                }
        );
    }
    private void initClearButton() {
        findViewById(R.id.btn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameInput.setText("");
                    }
                }
        );
    }
    private void updateHelloMessage() {
        String name = nameInput.getText().toString();

        messageLabel.setText("Hello " + name);
    }
}







