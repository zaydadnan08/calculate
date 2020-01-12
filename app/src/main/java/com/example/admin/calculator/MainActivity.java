package com.example.admin.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {



    private GestureDetectorCompat gestureObject;
    String DigitsToNumber = "";
    List<Double> numberlist = new ArrayList();
    List<Integer> operatorlist = new ArrayList();
    TextView Display;
    TextView DisplayAns;
    int overall =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display = findViewById(R.id.text);
        DisplayAns = findViewById(R.id.texttwo);
        Display.setMovementMethod(new ScrollingMovementMethod());


        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

    }

    //region swipe
    @Override
    public boolean onTouchEvent (MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY){

            if(event2.getX() > event1.getX()){
                //left to right swipe
                Intent intent = new Intent (MainActivity.this, functions.class);
                finish();
                startActivity(intent);
            }

            else
                if (event2.getX() < event1.getX()){
                    //right to left swipe
                }
            return true;
        }
    }
    //endregion


    //region btnclick
    public void zero(View view) {
        AddNumber("0");
    }
    public void one(View view) {AddNumber("1");
    }
    public void two(View view) {
        AddNumber("2");
    }
    public void three(View view) {
        AddNumber("3");
    }
    public void four(View view) {
        AddNumber("4");
    }
    public void five(View view) {
        AddNumber("5");
    }
    public void six(View view) {
        AddNumber("6");
    }
    public void seven(View view) {
        AddNumber("7");
    }
    public void eight(View view) {
        AddNumber("8");
    }
    public void nine(View view) {AddNumber("9");}
    public void period(View view) {AddNumber(".");}
    public void pie(View View) {
        overall = 0;
        for (int i = 0; i < operatorlist.size(); i++) {
            if (operatorlist.get(i) <= 4) {
                overall++;
            }
        }

        if (numberlist.size()<=overall  || numberlist.get(numberlist.size()-1)!=3.141592654) {

            if (DigitsToNumber == "") {
                Display.setText(Display.getText() + "π");
                numberlist.add(3.141592654);
            }
        }
    }
    public void add(View View) {
        changetext();
        FormNumber();
        operatorlist.add(1);
        Display.setText(Display.getText() + "+");
        OppException();
    }
    public void subtract(View View) {
        changetext();
        FormNumber();
        operatorlist.add(2);
        Display.setText(Display.getText() + "-");
        OppException();
    }
    public void multiply(View View) {
        changetext();
        FormNumber();
        operatorlist.add(3);
        Display.setText(Display.getText() + "*");
        OppException();

    }
    public void divide(View View) {
        changetext();
        FormNumber();
        operatorlist.add(4);
        Display.setText(Display.getText() + "÷");
        OppException();
    }
    public void AC(View View) {
        numberlist.clear();
        operatorlist.clear();
        Display.setText("");
        DisplayAns.setText("");
        DigitsToNumber = "";
    }
    public void percent (View view) {

        changetext();
        double PercentNum = Double.parseDouble(DigitsToNumber)/100;

        String FullStr = Display.getText().toString();
        if (FullStr.length() > 0) {
            FullStr = FullStr.substring(0, FullStr.length() - DigitsToNumber.length());
        }

        Display.setText(FullStr + PercentNum);
        DigitsToNumber = String.valueOf(PercentNum);
    }
    // endregion

    public void changetext(){
        if (DisplayAns.getText()!=""){
            if ((numberlist.get(0) % 1)== 0){
                double num = numberlist.get(0);
                int intnum = (int)num;
                Display.setText("" + intnum);
            }
            else {
                Display.setText("" + numberlist.get(0));}
            DisplayAns.setText("");
            DigitsToNumber = String.valueOf(numberlist.get(0));
            numberlist.clear();
        }
    }
    public void FormNumber() {
        if (DigitsToNumber != "") {
            double x = Double.parseDouble(DigitsToNumber);
            numberlist.add(x);
            DigitsToNumber = "";
        }
    }
    public void OppException() {

        String FullStr =  Display.getText().toString();
        int checker = 0;
        overall = 0;

        for (int i=0;i<operatorlist.size();i++){
            if (operatorlist.get(i)<=4) {
                overall++;}
            }

        if(numberlist.size()==0 || overall>numberlist.size()) {

            checker = operatorlist.get(operatorlist.size()-1);


                operatorlist.remove(operatorlist.size() - 1);

                if (FullStr.length() > 0) {
                    FullStr = FullStr.substring(0, FullStr.length() - 1);
                    Display.setText(FullStr);}

                    if (operatorlist.size()>0) {
                        if (checker == 2 && operatorlist.get(operatorlist.size() - 1) != 5) {
                            NegativeException();
                        }
                    }
                else {
                        if (checker == 2) { NegativeException();}
                    }
            }
    }
    public void NegativeException(){
        operatorlist.add(5);
        Display.setText(Display.getText() + "(-)");
    }
    public void AddNumber(String digit) {

        overall = 0;
        for (int i = 0; i < operatorlist.size(); i++) {
            if (operatorlist.get(i) <= 4) {
                overall++; }}

            if (numberlist.size()>0 && numberlist.get(numberlist.size() - 1) == 3.141592654 && numberlist.size() > overall) {
                // do nothing
            } else {

                if (DisplayAns.getText() != "") {
                    numberlist.clear();
                    operatorlist.clear();
                    Display.setText("");
                    DisplayAns.setText("");
                }

                Display.setText(Display.getText() + digit);
                DigitsToNumber = DigitsToNumber + digit;
        }
    }
    public void remove(View view) {

        int overall = 0;
        for (int i = 0; i < operatorlist.size(); i++) {
            if (operatorlist.get(i) <= 4) {
                overall++; } }

        String FullStr = Display.getText().toString();

        if (DisplayAns.getText()!= ""){
            //do nothing
            }

        else {
        if (FullStr.length() > 0) {
            FullStr = FullStr.substring(0, FullStr.length() - 1);
            Display.setText(FullStr);

            if (overall < numberlist.size() && numberlist.get(numberlist.size() - 1) == 3.141592654) {
                numberlist.remove(numberlist.size() - 1); // this snippet is exception for pi value!
            } else {

                if (overall == numberlist.size() && DigitsToNumber == "") { // removing operator
                    // check for if oppnext is (-)
                    if (operatorlist.get(operatorlist.size() - 1) == 5) { // checks for negative sign
                        operatorlist.remove(operatorlist.size() - 1);
                        FullStr = FullStr.substring(0, FullStr.length() - 2);
                        Display.setText(FullStr);

                    } else {
                        operatorlist.remove(operatorlist.size() - 1); // normal removal
                        DigitsToNumber = String.valueOf(numberlist.get(numberlist.size() - 1));
                        numberlist.remove(numberlist.size() - 1);
                        double NumCheck = Double.parseDouble(DigitsToNumber);

                        if (NumCheck % 1 == 0) {
                            DigitsToNumber = DigitsToNumber.substring(0, DigitsToNumber.length() - 2);
                        }
                    }
                }

                else { // removing digits
                    if (DigitsToNumber.length() != 1) {
                        DigitsToNumber = DigitsToNumber.substring(0, DigitsToNumber.length() - 1);
                    } else {
                        DigitsToNumber = "";
                    }
                }
            }
        }
    }}
    public int Bedmass(){

        for (int i=0;i<operatorlist.size();i++){
         if (operatorlist.get(i)==5){return i;}
          else if (operatorlist.get(i)>2 && operatorlist.get(i)<5 ){return i;}}

        return 0;
}
    public void equal(View view) {

        FormNumber();
        int index;
        overall = 0;
        double num = 0;

        for (int i = 0; i < operatorlist.size(); i++) {
            if (operatorlist.get(i) <= 4) {
                overall++;
            }
        }

        if (overall >= numberlist.size()) {
            DisplayAns.setText("Error");
        } else {
            while (operatorlist.size() > 0) {
                index = Bedmass();

                if (operatorlist.get(index) == 5) {
                    num = numberlist.get(index) * (-1);
                } else {
                    switch (operatorlist.get(index)) {
                        case 1:
                            num = numberlist.get(index) + numberlist.get(index + 1);
                            break;
                        case 2:
                            num = numberlist.get(index) - numberlist.get(index + 1);
                            break;
                        case 3:
                            num = numberlist.get(index) * numberlist.get(index + 1);
                            break;
                        case 4:
                            num = numberlist.get(index) / numberlist.get(index + 1);
                            break;
                    }
                    numberlist.remove(index + 1);
                }
                numberlist.set(index, num);
                operatorlist.remove(index);
            }

            num = Math.round(numberlist.get(0) * 10000) / 10000.0;
                numberlist.set(0, num);
                DisplayAns.setText("=" + numberlist.get(0));
                DigitsToNumber = "";
                Fontchecker();

    }
    }
    public void Fontchecker() {
        String FullStr = DisplayAns.getText().toString();
        int length = FullStr.length();
        if (length<6){DisplayAns.setTextSize(70);}
        else if (length >= 6 && length<=10){DisplayAns.setTextSize(60);}
        else if (length >= 10){DisplayAns.setTextSize(40);}
            else {DisplayAns.setTextSize(30);}
    }
}


