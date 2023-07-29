package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText a, b;
    Button add, sub, mul, div;
    TextView ans, op;

    EditText selected;

    double num1, num2, answer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.num1);
        b = findViewById(R.id.num2);
        selected = a;

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.div);
        mul = findViewById(R.id.mul);

        ans = findViewById(R.id.textView_result);
        op = findViewById(R.id.textView_operation);

        add.setOnClickListener(view -> { parseNums(); answer = num1 + num2; op.setText("+"); ans.setText(String.valueOf(answer));});
        sub.setOnClickListener(view -> { parseNums(); answer = num1 - num2; op.setText("-"); ans.setText(String.valueOf(answer));});
        div.setOnClickListener(view -> { parseNums(); answer = num1 / num2; op.setText("/"); ans.setText(String.valueOf(answer));});
        mul.setOnClickListener(view -> { parseNums(); answer = num1 * num2; op.setText("*"); ans.setText(String.valueOf(answer));});

        createNumberPad();
    }

    private void parseNums(){
        try{
            num1 = Double.parseDouble(a.getText().toString());
            num2 = Double.parseDouble(b.getText().toString());
        } catch (NumberFormatException ex){
            Toast.makeText(this, "Give me some numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNumberPad(){
        TableLayout numpad = findViewById(R.id.tableLayout_numpad);
        Button[] buttons = new Button[9];
        TableRow[] numpadrow = new TableRow[3];
        int npi = 0;

        for(int i = 0; i < 3; i++){
            numpadrow[i] = new TableRow(this);
            numpadrow[i].setId(i+1);
            numpadrow[i].setBackgroundColor(Color.WHITE);
            numpadrow[i].setGravity(Gravity.CENTER_HORIZONTAL);
            numpadrow[i].setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for(int j = 0; j < 3; j++){
                npi = i*3 + j;
                buttons[npi] = new Button(this);
                buttons[npi].setId(npi + 111);
                buttons[npi].setGravity(Gravity.CENTER_HORIZONTAL);
                buttons[npi].setTextSize(24);
                buttons[npi].setText(getString(R.string.number, npi));
                int finalNpi = npi;
                buttons[npi].setOnClickListener(view -> {
                    int num = 0;
                    try{
                        num = Integer.parseInt(selected.getText().toString())*10 + finalNpi;
                    }
                    catch(NumberFormatException nfe){
                        num = finalNpi;
                    }
                    selected.setText(getString(R.string.number, num));
                });
                numpadrow[i].addView(buttons[npi]);
            }
            numpad.addView(numpadrow[i], new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    public void setSelectedEditTextID(View v){
        if(R.id.num1 == v.getId()){
            Log.d("EditText:", "Selected num1");
            selected = a;
        }
        else{
            Log.d("EditText:", "Selected num2");
            selected = b;
        }
    }

}