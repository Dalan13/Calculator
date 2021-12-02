package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener
{
    private int openParenthesis = 0;

    private boolean dotUsed = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;
    private final static int IS_PI = 5;


    Button buttonNumber0;
    Button buttonNumber1;
    Button buttonNumber2;
    Button buttonNumber3;
    Button buttonNumber4;
    Button buttonNumber5;
    Button buttonNumber6;
    Button buttonNumber7;
    Button buttonNumber8;
    Button buttonNumber9;

    Button buttonClear;
    Button buttonParentheses;
    Button buttonPow;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubtraction;
    Button buttonAddition;
    Button buttonEqual;
    Button buttonDot;

    Button buttonClearLast;
    Button buttonSin;
    Button buttonCos;
    Button buttonTan;
    Button buttonCtg;
    Button buttonPi;

    TextView textViewInputNumbers;
    TextView textViewHistory;
    TextView textViewHistory2;

    ScriptEngine scriptEngine;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("textViewInputNumbers",textViewInputNumbers.getText().toString());
        outState.putString("textViewHistory",textViewHistory.getText().toString());
        outState.putString("textViewHistory2",textViewHistory2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        textViewInputNumbers.setText(savedInstanceState.getString("textViewInputNumbers"));
        textViewHistory.setText(savedInstanceState.getString("textViewHistory"));
        textViewHistory2.setText(savedInstanceState.getString("textViewHistory2"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scriptEngine = new ScriptEngineManager().getEngineByName("rhino");

        initializeViewVariables();
        setOnClickListeners();
        setOnTouchListener();
    }

    private void initializeViewVariables()
    {
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonParentheses = (Button) findViewById(R.id.button_parentheses);
        buttonPow = (Button) findViewById(R.id.button_pow);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);
        textViewInputNumbers = (TextView) findViewById(R.id.textView_input_numbers);
        textViewHistory = (TextView) findViewById(R.id.textView_history);
        textViewHistory2 = (TextView) findViewById(R.id.textView_history_2);


            buttonClearLast = (Button) findViewById(R.id.button_clear_last);
            buttonPi = (Button) findViewById(R.id.button_pi);
            buttonSin = (Button) findViewById(R.id.button_sin);
            buttonCos = (Button) findViewById(R.id.button_cos);
            buttonTan = (Button) findViewById(R.id.button_tg);
            buttonCtg = (Button) findViewById(R.id.button_ctg);
    }

    private void setOnClickListeners()
    {
        buttonNumber0.setOnClickListener(this);
        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonParentheses.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonClearLast.setOnClickListener(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonPow.setOnClickListener(this);
            buttonPi.setOnClickListener(this);
            buttonSin.setOnClickListener(this);
            buttonCos.setOnClickListener(this);
            buttonTan.setOnClickListener(this);
            buttonCtg.setOnClickListener(this);
        }
    }

    private void setOnTouchListener()
    {
        buttonNumber0.setOnTouchListener(this);
        buttonNumber1.setOnTouchListener(this);
        buttonNumber2.setOnTouchListener(this);
        buttonNumber3.setOnTouchListener(this);
        buttonNumber4.setOnTouchListener(this);
        buttonNumber5.setOnTouchListener(this);
        buttonNumber6.setOnTouchListener(this);
        buttonNumber7.setOnTouchListener(this);
        buttonNumber8.setOnTouchListener(this);
        buttonNumber9.setOnTouchListener(this);

        buttonClear.setOnTouchListener(this);
        buttonParentheses.setOnTouchListener(this);
        buttonDivision.setOnTouchListener(this);
        buttonMultiplication.setOnTouchListener(this);
        buttonSubtraction.setOnTouchListener(this);
        buttonAddition.setOnTouchListener(this);
        buttonDot.setOnTouchListener(this);
        buttonClearLast.setOnTouchListener(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonPow.setOnClickListener(this);
            buttonPi.setOnTouchListener(this);
            buttonSin.setOnTouchListener(this);
            buttonCos.setOnTouchListener(this);
            buttonTan.setOnTouchListener(this);
            buttonCtg.setOnTouchListener(this);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_zero:
                if (addNumber("0")) equalClicked = false;
                break;
            case R.id.button_one:
                if (addNumber("1")) equalClicked = false;
                break;
            case R.id.button_two:
                if (addNumber("2")) equalClicked = false;
                break;
            case R.id.button_three:
                if (addNumber("3")) equalClicked = false;
                break;
            case R.id.button_four:
                if (addNumber("4")) equalClicked = false;
                break;
            case R.id.button_five:
                if (addNumber("5")) equalClicked = false;
                break;
            case R.id.button_six:
                if (addNumber("6")) equalClicked = false;
                break;
            case R.id.button_seven:
                if (addNumber("7")) equalClicked = false;
                break;
            case R.id.button_eight:
                if (addNumber("8")) equalClicked = false;
                break;
            case R.id.button_nine:
                if (addNumber("9")) equalClicked = false;
                break;
            case R.id.button_addition:
                if (textViewInputNumbers.length() > 0 && defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.length() - 1) + "") == IS_OPERAND) {
                    if (clrLast()) {
                        equalClicked = false;
                    }
                }
                if (addOperand("+")) equalClicked = false;
                break;
            case R.id.button_subtraction:
                if (textViewInputNumbers.length() > 0 && defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.length() - 1) + "") == IS_OPERAND) {
                    if (clrLast()) {
                        equalClicked = false;
                    }
                }
                if (addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplication:
                if (textViewInputNumbers.length() > 0 && defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.length() - 1) + "") == IS_OPERAND) {
                    if (clrLast()) {
                        equalClicked = false;
                    }
                }
                if (addOperand("x")) equalClicked = false;
                break;
            case R.id.button_division:
                if (textViewInputNumbers.length() > 0 && defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.length() - 1) + "") == IS_OPERAND) {
                    if (clrLast()) {
                        equalClicked = false;
                    }
                }
                if (addOperand("\u00F7")) equalClicked = false;
                break;
            case R.id.button_pow:
                if (textViewInputNumbers.length() > 0 && defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.length() - 1) + "") == IS_OPERAND) {
                    if (clrLast()) {
                        equalClicked = false;
                    }
                }
                if (addOperand("^")) equalClicked = false;
                break;
            case R.id.button_dot:
                if (addDot()) equalClicked = false;
                break;
            case R.id.button_parentheses:
                if (addParenthesis()) equalClicked = false;
                break;
            case R.id.button_clear:
                if (textViewInputNumbers.getText().equals("")) {
                    textViewHistory.setText("");
                    textViewHistory2.setText("");
                }
                textViewInputNumbers.setText("");
                openParenthesis = 0;
                dotUsed = false;
                equalClicked = false;
                break;
            case R.id.button_equal:
                if (!equalClicked) {
                    if (textViewInputNumbers.getText().toString() != null && !textViewInputNumbers.getText().toString().equals("")) {
                        String temp = textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "";
                        if ((temp.equals("+") || temp.equals("-") || temp.equals("x") || temp.equals("\u00F7") || temp.equals("%"))) {
                            clrLast();
                        }
                        if (openParenthesis > 0) {
                            while (openParenthesis > 0) {
                                addParenthesis();
                            }
                        }
                        calculate(textViewInputNumbers.getText().toString());
                    }
                    equalClicked = true;
                }
                break;
            case R.id.button_pi:
                if (addPi("P")) equalClicked = false;
                break;
            case R.id.button_sin:
                if (addTrig("sin(")) equalClicked = false;
                break;
            case R.id.button_cos:
                if (addTrig("cos(")) equalClicked = false;
                break;
            case R.id.button_tg:
                if (addTrig("tg(")) equalClicked = false;
                break;
            case R.id.button_ctg:
                if (addTrig("ctg(")) equalClicked = false;
                break;
            case R.id.button_clear_last:
                if (clrLast()) equalClicked = false;
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addDot()
    {
        boolean done = false;

        if (textViewInputNumbers.getText().length() == 0)
        {
            textViewInputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_OPERAND)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_NUMBER)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private static String countPow(String temp) {
        if (temp.indexOf("^") != -1) {
            char[] tempChar = temp.toCharArray();
            int j = 0;
            char[] newChar = new char[tempChar.length + 10];
            for (int i = 0; i < tempChar.length; i++) {
                if (tempChar[i] == '^') {
                    j = i;
                    break;
                }
            }

            int k = -1;
            if (tempChar[j-1] != ')') {
                for (int i = j; i >= 0; i--) {
                    if ((tempChar[i] == '(') || (tempChar[i] == ')') || (tempChar[i] == '%') || (tempChar[i] == '\u00F7')
                            || (tempChar[i] == 'x') || (tempChar[i] == 'n') || (tempChar[i] == 's') || (tempChar[i] == '\u221A')
                            || (tempChar[i] == '+') || (tempChar[i] == '-') || (tempChar[i] == '!')) {
                        k = i;
                        break;
                    }
                }
            } else {
                int openNum = 0;
                int closeNum = 0;
                    for (int i = j; i >= 0; i--) {
                        if (tempChar[i] == '(') {
                            openNum++;
                            k = i;
                        } else if (tempChar[i] == ')') {
                            closeNum++;
                        }
                        if (openNum == closeNum) break;
                    }
            }

            int l = tempChar.length;
            if (tempChar[j+1] != '(') {
                for (int i = j; i < tempChar.length; i++) {
                    if ((tempChar[i] == '(') || (tempChar[i] == ')') || (tempChar[i] == '%') || (tempChar[i] == '\u00F7')
                            || (tempChar[i] == 'x') || (tempChar[i] == 'n') || (tempChar[i] == 's') || (tempChar[i] == '\u221A')
                            || (tempChar[i] == '+') || (tempChar[i] == '-') || (tempChar[i] == '!')) {
                        l = i;
                        break;
                    }
                }
            } else {
                int openNum = 1;
                int closeNum = 0;
                for (int i = j + 2; i < tempChar.length; i++) {
                    if (tempChar[i] == '(') {
                        openNum++;
                    } else if (tempChar[i] == ')') {
                        closeNum++;
                        l = i;
                    }
                    if (openNum == closeNum) break;
                }
            }
            for (int i = 0; i <= k; i++) {
                newChar[i] = tempChar[i];
            }
            newChar[k + 1] = 'M';
            newChar[k + 2] = 'a';
            newChar[k + 3] = 't';
            newChar[k + 4] = 'h';
            newChar[k + 5] = '.';
            newChar[k + 6] = 'p';
            newChar[k + 7] = 'o';
            newChar[k + 8] = 'w';
            newChar[k + 9] = '(';
            for (int i = k + 10; i < j + 9; i++) {
                newChar[i] = tempChar[i - 9];
            }

            newChar[j + 9] = ',';
            for (int i = j + 10; i < l + 9; i++) {
                newChar[i] = tempChar[i - 9];
            }
            newChar[l + 9] = ')';
            for (int i = l + 10; i < newChar.length; i++) {
                newChar[i] = tempChar[i - 10];
            }
            temp = new String(newChar);

            for (char t : newChar) {
                if (t == '^') {
                    temp = new String(newChar);
                    temp = countPow(temp);
                }
            }
        }
        return temp;
    }

    private boolean addTrig(String trig)
    {

        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("0") || lastInput.equals("1") || lastInput.equals("2") || lastInput.equals("3") || lastInput.equals(".")
                    || lastInput.equals("4") || lastInput.equals("5") || lastInput.equals("6") || lastInput.equals("%")
                    || lastInput.equals("7") || lastInput.equals("8") || lastInput.equals("9") || lastInput.equals(")") || lastInput.equals("P")))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x" + trig);
                openParenthesis++;
            } else if (!trig.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + trig);
                openParenthesis++;
            }
        }
        else {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + trig);
            openParenthesis++;
        }

        return false;
    }

    private boolean clrLast()
    {
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String s = textViewInputNumbers.getText().toString();
            String sNew = "";
            if (s.length() > 3) {
                if (s.charAt(s.length() - 2) == 'n' || s.charAt(s.length() - 2) == 's' || s.charAt(s.length() - 2) == 'g') {
                    if (s.length() > 3) {
                        if (s.charAt(s.length() - 4) == 'c' || s.charAt(s.length() - 2) == 'n') {
                            sNew = s.substring(0, s.length() - 4);
                            openParenthesis--;
                        } else {
                            sNew = s.substring(0, s.length() - 3);
                            openParenthesis--;
                        }
                    } else {
                        sNew = s.substring(0, s.length() - 3);
                        openParenthesis--;
                    }

                } else if (s.charAt(s.length() - 1) == ')'){
                    openParenthesis++;
                    sNew = s.substring(0, s.length() - 1);
                } else if (s.charAt(s.length() - 1) == '('){
                    openParenthesis--;
                    sNew = s.substring(0, s.length() - 1);
                }
                else sNew = s.substring(0, s.length() - 1);
                textViewInputNumbers.setText(sNew);
            }
            else if (s.charAt(s.length() - 1) == ')'){
                openParenthesis++;
                sNew = s.substring(0, s.length() - 1);
            } else if (s.charAt(s.length() - 1) == '('){
                openParenthesis--;
                sNew = s.substring(0, s.length() - 1);
            }
            else sNew = s.substring(0, s.length() - 1);
            textViewInputNumbers.setText(sNew);
        }
        return false;
    }

    private boolean addParenthesis()
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();

        if (operationLength == 0)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
            dotUsed = false;
            openParenthesis++;
            done = true;
        } else if (openParenthesis > 0 && operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            switch (defineLastCharacter(lastInput))
            {
                case IS_NUMBER:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_OPERAND:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_OPEN_PARENTHESIS:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_CLOSE_PARENTHESIS:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_PI:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
            }
        } else if (openParenthesis == 0 && operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            if (defineLastCharacter(lastInput) == IS_OPERAND)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            } else
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            }
        }
        return done;
    }

    private boolean addPi(String pi)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textViewInputNumbers.setText(pi);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + pi);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%") || lastCharacterState == IS_NUMBER || lastCharacterState == IS_PI)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x" + pi);
                done = true;
            } else if (lastCharacterState == IS_OPERAND)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + pi);
                done = true;
            }
        } else
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + pi);
            done = true;
        }
        return done;
    }

    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("x") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if ((operand.equals("%") || operand.equals("^") && defineLastCharacter(lastInput) == IS_NUMBER) || (operand.equals("%") && defineLastCharacter(lastInput) == IS_PI))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%") || !operand.equals("^"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textViewInputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%") || lastCharacter.equals("P"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x" + number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            }
        } else
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
            done = true;
        }
        return done;
    }


    private void calculate(String input)
    {
        String result = "";
        String startLine = input;
        String endLine;
        try
        {
            String temp = input;
            if (equalClicked)
            {
                temp = input + lastExpression;
            } else
            {
                saveLastExpression(input);
            }
            temp =countPow(temp);

            result = scriptEngine.eval(temp.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/")
            .replaceAll("sin", "Math.sin").replaceAll("cos", "Math.cos").replaceAll("P", "3.14159265")
            .replaceAll("ctg", "1.0/Math.tan").replaceAll("tg", "Math.tan")).toString();
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;


        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Wrong Format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("Infinity"))
        {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            textViewInputNumbers.setText(input);

        } else if (result.contains("."))
        {

            result = result.replaceAll("\\.?0*$", "");
            endLine = startLine + "="+ result;

            if (textViewHistory.getText() != "") {
                textViewHistory2.setText(textViewHistory.getText());
            }
            textViewHistory.setText(endLine);

            int layoutTextSize = 0;

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                layoutTextSize = 24;
            } else layoutTextSize = 30;

            if (textViewHistory2.length() > 22) {
                if (textViewHistory2.length() > 26) {
                    textViewHistory2.setTextSize(16);
                } else textViewHistory2.setTextSize(24);
            } else textViewHistory2.setTextSize(layoutTextSize);
            if (textViewHistory.length() > 22) {
                if (textViewHistory.length() > 26) {
                    textViewHistory.setTextSize(16);
                } else textViewHistory.setTextSize(24);
            } else textViewHistory.setTextSize(layoutTextSize);

            textViewInputNumbers.setText(result);
        }
    }

    private void saveLastExpression(String input)
    {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1)
        {
            if (lastOfExpression.equals(")"))
            {
                lastExpression = ")";
                int numberOfCloseParenthesis = 1;

                for (int i = input.length() - 2; i >= 0; i--)
                {
                    if (numberOfCloseParenthesis > 0)
                    {
                        String last = input.charAt(i) + "";
                        if (last.equals(")"))
                        {
                            numberOfCloseParenthesis++;
                        } else if (last.equals("("))
                        {
                            numberOfCloseParenthesis--;
                        }
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(input.charAt(i) + "") == IS_OPERAND)
                    {
                        lastExpression = input.charAt(i) + lastExpression;
                        break;
                    } else
                    {
                        lastExpression = "";
                    }
                }
            } else if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER)
            {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--)
                {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT)
                    {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND)
                    {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0)
                    {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("^") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        if (lastCharacter.equals("P"))
            return IS_PI;

        return -1;
    }
}
