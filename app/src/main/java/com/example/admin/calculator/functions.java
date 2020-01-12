package com.example.admin.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class functions extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    String DigitsToNumber = "";
    String DigToNum ="";
    int inputbox =1;
    List<Double> numberlist1 = new ArrayList();
    List<Integer> operatorlist1 = new ArrayList();
    List<Double> numberlist2 = new ArrayList();
    List<Integer> operatorlist2 = new ArrayList();
    TextView Display1, Display2, DisplayAns;
    Button value1, value2;

    int overall =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        Display1 = findViewById(R.id.text1);
        Display2 = findViewById(R.id.text2);
        DisplayAns = findViewById(R.id.texttwo);
        value1 = findViewById(R.id.num1);
        value2 = findViewById(R.id.num2);
        Display1.setMovementMethod(new ScrollingMovementMethod());
        Display2.setMovementMethod(new ScrollingMovementMethod());


        gestureObject = new GestureDetectorCompat(this, new functions.LearnGesture());

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
                Intent intent = new Intent (functions.this, MainActivity.class);
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
    public void one(View view) {
        AddNumber("1");
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
    public void input1 (View view) {
        inputbox = 1;
        value1.setBackgroundColor(Color.rgb(255, 70, 77));
        value2.setBackgroundColor(Color.rgb(207, 207, 207));
   }
    public void input2 (View view) {
        inputbox= 2;
        value1.setBackgroundColor(Color.rgb(207, 207, 207));
        value2.setBackgroundColor(Color.rgb(255, 70, 77));
    }

    public void power(View View) {AddOpp("^", 6);}
    public void add(View View) {AddOpp("+", 1);}
    public void subtract(View View) {AddOpp("-", 2);}
    public void multiply(View View){AddOpp("*", 3);}
    public void divide(View View) {AddOpp("/", 4);}
    public void AC(View View) {

        DisplayAns.setText("");

        if(inputbox==1){
        numberlist1.clear();
        operatorlist1.clear();
        Display1.setText("");
        DigitsToNumber = ""; }

        else {
            numberlist2.clear();
            operatorlist2.clear();
            Display2.setText("");
            DigToNum = ""; }
    }
    // endregion

    public void AddNumber(String digit) {

        if(inputbox==1){
            Display1.setText(Display1.getText() + digit);
            DigitsToNumber = DigitsToNumber + digit;
            }
        else {
            Display2.setText(Display2.getText() + digit);
            DigToNum = DigToNum + digit;}
    }
    public void AddOpp(String operator, int opp) {

        if(inputbox==1){
            FormNumber();
            operatorlist1.add(opp);
            Display1.setText(Display1.getText() + operator);
            OppException1();
        }
        else {
            FormNumber();
            operatorlist2.add(opp);
            Display2.setText(Display2.getText() + operator);
            OppException2();}
    }



    public void FormNumber() {

        if (inputbox==1){
        if (DigitsToNumber != "") {
            double x = Double.parseDouble(DigitsToNumber);
            numberlist1.add(x);
            DigitsToNumber = "";
        }
        }
        else {
            if (DigToNum != "") {
                double x = Double.parseDouble(DigToNum);
                numberlist2.add(x);
                DigToNum = "";
            }
        }
        }
    public void OppException1() {

        String FullStr =  Display1.getText().toString();
        int checker = 0;
        overall = 0;

        for (int i=0;i<operatorlist1.size();i++){
            if (operatorlist1.get(i)<=4) {
                overall++;}
        }

        if(numberlist1.size()==0 || overall>numberlist1.size()) {

            checker = operatorlist1.get(operatorlist1.size()-1);

            operatorlist1.remove(operatorlist1.size() - 1);

            if (FullStr.length() > 0) {
                FullStr = FullStr.substring(0, FullStr.length() - 1);
                Display1.setText(FullStr);}

            if (operatorlist1.size()>0) {
                if (checker == 2 && operatorlist1.get(operatorlist1.size() - 1) != 5) {
                    NegativeException();
                }
            }
            else {
                if (checker == 2) { NegativeException();}
            }
        }
    }
    public void OppException2() {

        String FullStr =  Display2.getText().toString();
        int checker = 0;
        overall = 0;

        for (int i=0;i<operatorlist2.size();i++){
            if (operatorlist2.get(i)<=4) {
                overall++;}
        }

        if(numberlist2.size()==0 || overall>numberlist2.size()) {

            checker = operatorlist2.get(operatorlist2.size()-1);

            operatorlist2.remove(operatorlist2.size() - 1);

            if (FullStr.length() > 0) {
                FullStr = FullStr.substring(0, FullStr.length() - 1);
                Display2.setText(FullStr);}

            if (operatorlist2.size()>0) {
                if (checker == 2 && operatorlist2.get(operatorlist2.size() - 1) != 5) {
                    NegativeException();
                }
            }
            else {
                if (checker == 2) { NegativeException();}
            }
        }
    }
    public void NegativeException(){
        if(inputbox==1){
            operatorlist1.add(5);
            Display1.setText(Display1.getText() + "(-)");}
        else {
            operatorlist2.add(5);
            Display2.setText(Display2.getText() + "(-)");}
    }

    public void remove(View view) {
        if(inputbox==1){
            remove1();
        }
        else {remove2();}
        }
    public void remove1() {

        int overall = 0;
        for (int i = 0; i < operatorlist1.size(); i++) {
            if (operatorlist1.get(i) <= 4) {
                overall++; } }

        String FullStr = Display1.getText().toString();

        if (FullStr.length() > 0) {
            FullStr = FullStr.substring(0, FullStr.length() - 1);
            Display1.setText(FullStr);


                if (overall == numberlist1.size() && DigitsToNumber == "") { // removing operator
                    // check for if oppnext is (-)
                    if (operatorlist1.get(operatorlist1.size() - 1) == 5) { // checks for negative sign
                        operatorlist1.remove(operatorlist1.size() - 1);
                        FullStr = FullStr.substring(0, FullStr.length() - 2);
                        Display1.setText(FullStr);

                    } else {
                        operatorlist1.remove(operatorlist1.size() - 1); // normal removal
                        DigitsToNumber = String.valueOf(numberlist1.get(numberlist1.size() - 1));
                        numberlist1.remove(numberlist1.size() - 1);
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
    public void remove2() {

        int overall = 0;
        for (int i = 0; i < operatorlist2.size(); i++) {
            if (operatorlist2.get(i) <= 4) {
                overall++; } }

        String FullStr = Display2.getText().toString();

        if (FullStr.length() > 0) {
            FullStr = FullStr.substring(0, FullStr.length() - 1);
            Display2.setText(FullStr);


            if (overall == numberlist2.size() && DigToNum == "") { // removing operator
                // check for if oppnext is (-)
                if (operatorlist2.get(operatorlist1.size() - 1) == 5) { // checks for negative sign
                    operatorlist2.remove(operatorlist2.size() - 1);
                    FullStr = FullStr.substring(0, FullStr.length() - 2);
                    Display2.setText(FullStr);

                } else {
                    operatorlist2.remove(operatorlist2.size() - 1); // normal removal
                    DigToNum = String.valueOf(numberlist2.get(numberlist2.size() - 1));
                    numberlist2.remove(numberlist2.size() - 1);
                    double NumCheck = Double.parseDouble(DigToNum);

                    if (NumCheck % 1 == 0) {
                        DigToNum = DigToNum.substring(0, DigToNum.length() - 2);
                    }
                }
            }

            else { // removing digits
                if (DigToNum.length() != 1) {
                    DigToNum = DigToNum.substring(0, DigToNum.length() - 1);
                } else {
                    DigToNum = "";
                }
            }
        }
    }



    public int Bedmass1(){

        for (int i=0;i<operatorlist1.size();i++){
            if (operatorlist1.get(i)>4){return i;}
            else if (operatorlist1.get(i)>2 && operatorlist1.get(i)<5 ){return i;}}

        return 0;
    }
    public int Bedmass2(){

        for (int i=0;i<operatorlist2.size();i++){
            if (operatorlist2.get(i)>4){return i;}
            else if (operatorlist2.get(i)>2 && operatorlist2.get(i)<5 ){return i;}}

        return 0;
    }



    public void equal(View view) {
        inputbox =1;
        equal1();
        inputbox = 2;
        equal2();
        double x = Double.parseDouble(DigitsToNumber);
        double y = Double.parseDouble(DigToNum);

        if ((x%1)==0 && (y%1)==0 ){
        DisplayAns.setText("" + gcd(x,y) );}
        else {
            DisplayAns.setText("Error: press AC to restart");
        }
    }

    public double gcd (double a, double b){
        if (b ==0){ return Math.abs(a);
        }
    else
        return gcd(b, (a % b)); }

    public void equal1() {

        FormNumber();
        int index;
        overall = 0;
        double num = 0;

        for (int i = 0; i < operatorlist1.size(); i++) {
            if (operatorlist1.get(i) <= 4) {
                overall++;
            }
        }

        if (overall >= numberlist1.size()) {
            DisplayAns.setText("Error");
        } else {
            while (operatorlist1.size() > 0) {
                index = Bedmass1();

                if (operatorlist1.get(index) == 5) {
                    num = numberlist1.get(index) * (-1);
                }

                else {
                    switch (operatorlist1.get(index)) {
                        case 1:
                            num = numberlist1.get(index) + numberlist1.get(index + 1);
                            break;
                        case 2:
                            num = numberlist1.get(index) - numberlist1.get(index + 1);
                            break;
                        case 3:
                            num = numberlist1.get(index) * numberlist1.get(index + 1);
                            break;
                        case 4:
                            num = numberlist1.get(index) / numberlist1.get(index + 1);
                            break;
                        case 6 :
                            num = Math.pow(numberlist1.get(index), numberlist1.get(index + 1));
                    }
                    numberlist1.remove(index + 1);
                }
                numberlist1.set(index, num);
                operatorlist1.remove(index);
            }
            num = numberlist1.get(0);
            numberlist1.set(0, num);
            Display1.setText("" + numberlist1.get(0));
            DigitsToNumber = "" + num;
        }
    }
    public void equal2() {

        FormNumber();
        int index;
        overall = 0;
        double num = 0;

        for (int i = 0; i < operatorlist2.size(); i++) {
            if (operatorlist2.get(i) <= 4) {
                overall++;
            }
        }

        if (overall >= numberlist2.size()) {
            DisplayAns.setText("Error");
        } else {
            while (operatorlist2.size() > 0) {
                index = Bedmass2();

                if (operatorlist2.get(index) == 5) {
                    num = numberlist2.get(index) * (-1);
                }

                else {
                    switch (operatorlist2.get(index)) {
                        case 1:
                            num = numberlist2.get(index) + numberlist2.get(index + 1);
                            break;
                        case 2:
                            num = numberlist2.get(index) - numberlist2.get(index + 1);
                            break;
                        case 3:
                            num = numberlist2.get(index) * numberlist2.get(index + 1);
                            break;
                        case 4:
                            num = numberlist2.get(index) / numberlist2.get(index + 1);
                            break;
                        case 6 :
                            num = Math.pow(numberlist2.get(index), numberlist2.get(index + 1));
                    }
                    numberlist2.remove(index + 1);
                }
                numberlist2.set(index, num);
                operatorlist2.remove(index);
            }
            num = numberlist2.get(0);
            numberlist2.set(0, num);
            Display2.setText("" + numberlist2.get(0));
            DigToNum = "" + num;
        }
    }






}
