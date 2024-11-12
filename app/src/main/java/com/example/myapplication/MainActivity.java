package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }
    public void onButtonPress(View view){
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        if(btnText.equals("C")) {
            Calculator.setFirstNum("");
            Calculator.setSecondNum("");
            Calculator.setLastOperation("");
            Calculator.setLastInputWasOperation(false);
        } else if (Calculator.canUseOperation()) {
            handleOperations(btnText);
            Calculator.setLastInputWasOperation(true);
        }
        updateDisplays();
    }
    void handleOperations(String operation) {
        boolean usedOperatorInsteadOfEqual = false;
        switch (operation) {
            case "=" :
                if (Calculator.getSecondNum().isEmpty()) break;
                int first = Integer.parseInt(Calculator.getFirstNum());
                int second = Integer.parseInt(Calculator.getSecondNum());

                switch(Calculator.getLastOperation()) {
                    case "+":
                        first += second;
                        break;
                    case "-":
                        first -= second;
                        break;
                    case "*":
                        first *= second;
                        break;
                    case "÷":
                        first /= second;
                        break;
                }
                Calculator.setFirstNum(String.valueOf(first));
                Calculator.setSecondNum("");
                if (!usedOperatorInsteadOfEqual) {
                    Calculator.setLastOperation("");
                }
                break;
            case "." :
                break;
            default:
                usedOperatorInsteadOfEqual = !Calculator.getSecondNum().isEmpty();
                handleOperations("=");
                Calculator.setLastOperation(operation);
                break;

        }
    }
    public void onNumberPress(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();

        if(!Calculator.getLastOperation().isEmpty()) {
            Calculator.setSecondNum(Calculator.getSecondNum() + btnText);
        }else {
            Calculator.setFirstNum(Calculator.getFirstNum() + btnText);
        }
        Calculator.setLastInputWasOperation(false);
        updateDisplays();
    }
    void updateDisplays() {
        TextView display0 = (findViewById(R.id.txtDisplay0));
        TextView display1 = (findViewById(R.id.txtDisplay1));
        if (!Calculator.getSecondNum().isEmpty()) {
            display0.setText(String.format("%s %s", Calculator.getFirstNum(),Calculator.getLastOperation()));
            display1.setText(Calculator.getSecondNum());
        }else {
            display0.setText(Calculator.getLastOperation());
            display1.setText(Calculator.getFirstNum());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

}

