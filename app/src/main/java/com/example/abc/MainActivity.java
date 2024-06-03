package com.example.abc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 69;
    EditText eD1,eD2,eD3;
    Button randBtn,btn1;
    TextView tv1,tv4;
    Random rand = new Random();
    ImageView iV;
    int rand_num;
    double a,b,c;
    double ans1,ans2;
    boolean b4 = false, b3 = false;
    String st1,st2,st3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eD1 = findViewById(R.id.eD1);
        eD2 = findViewById(R.id.eD2);
        eD3 = findViewById(R.id.eD3);
        randBtn = findViewById(R.id.randBtn);
        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);
        tv4 = findViewById(R.id.tv4);
        iV =  findViewById(R.id.iV);
    }



    public void rand(View view) {
        st1 = eD1.getText().toString();
        st2 = eD2.getText().toString();
        st3 = eD3.getText().toString();
        if (st1.isEmpty()) {
            rand_num  = rand.nextInt(41)-20;
            eD1.setText("" + rand_num);
        }
        if (st2.isEmpty() && !st1.isEmpty()) {
            rand_num  = rand.nextInt(41)-20;
            eD2.setText("" + rand_num);
        }
        if (st3.isEmpty() && !st2.isEmpty() && !st1.isEmpty())
        {
            rand_num  = rand.nextInt(41)-20;
            eD3.setText("" + rand_num);
        }
    }

    public void Calculated(View view) {
        st1 = eD1.getText().toString();
        st2 = eD2.getText().toString();
        st3 = eD3.getText().toString();
        if(check_Input(st1) && check_Input(st2) && check_Input(st3))
        {
            a = Double.parseDouble(st1);
            b = Double.parseDouble(st2);
            c = Double.parseDouble(st3);
            Intent si = new Intent(this, Calculated_page.class);
            si.putExtra("a",a);
            si.putExtra("b",b);
            si.putExtra("c",c);
            startActivityForResult(si,REQUEST_CODE);
        }
        else
        {
            Toast.makeText(this, "Enter in all a b c", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int source, int result, @Nullable Intent data_back){
        super.onActivityResult(source, result, data_back);
        if (source == REQUEST_CODE) {
            if (result == Activity.RESULT_OK) {
                if (data_back != null) {
                    b3 = data_back.getBooleanExtra("b1",false);
                    b4 = data_back.getBooleanExtra("b2",false);
                    tv1.setText("");
                    tv4.setText("");

                    if(b4)
                    {
                        ans2 = data_back.getDoubleExtra("X2", -1);
                        tv4.setText(String.format("X: %.1f",ans2));
                    }


                    if(b3)
                    {
                        ans1 = data_back.getDoubleExtra("X1", -1);
                        tv1.setText(String.format("X: %.1f",ans1));
                    }

                    }
                    else
                    {
                        iV.setVisibility(View.INVISIBLE);
                    }
                }

        }
    }
    public boolean check_Input(String input)
    {
        if(!input.isEmpty())
        {
            if(input.length() == 1)
            {
                if(input.charAt(0) >= '0' && input.charAt(0) <= '9')
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if(input.equals("-.") || input.equals("+."))
                {
                    return false;
                }
                if(input.indexOf('-') != -1 && input.indexOf('+') != -1)
                {
                    if(input.indexOf('-')+1 >= '0' && input.indexOf('-')+1 <= '9')
                    {
                        return true;
                    }
                    else if(input.indexOf('+')+1 >= '0' && input.indexOf('+')+1 <= '9')
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }
    public double check_variation(double a,double b,double c)
    {
        double num = 0;
        num = (b*b +(-4*a*c));
        return num;
    }
}