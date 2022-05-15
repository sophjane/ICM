package ua.icm.dialer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int TEXT_REQUEST = 1;

    EditText phoneNumberInput;
    ImageButton dialBtn;
    Button speedDialBtn1, speedDialBtn2, speedDialBtn3;
    Button delBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, plusBtn, zeroBtn, hashtagBtn;

    String number;
    int btnClicked = 0;
    HashMap<Integer, String[]> speedDialContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberInput = findViewById(R.id.phoneNumber);
        dialBtn = findViewById(R.id.dialButton);
        speedDialBtn1 =  findViewById(R.id.speedDialBtn1);
        speedDialBtn2 = findViewById(R.id.speedDialBtn2);
        speedDialBtn3 = findViewById(R.id.speedDialBtn3);
        speedDialContacts = new HashMap<>(3);
        delBtn = findViewById(R.id.delete);
        oneBtn = findViewById(R.id.btn1); twoBtn = findViewById(R.id.btn2); threeBtn = findViewById(R.id.btn3);
        fourBtn = findViewById(R.id.btn4); fiveBtn = findViewById(R.id.btn5); sixBtn = findViewById(R.id.btn6);
        sevenBtn = findViewById(R.id.btn7); eightBtn = findViewById(R.id.btn8); nineBtn = findViewById(R.id.btn9);
        plusBtn = findViewById(R.id.btnPlus); zeroBtn = findViewById(R.id.btn0); hashtagBtn = findViewById(R.id.btnHashtag);

        delBtn.setOnClickListener(
                v -> changeInput("del")
        );

        oneBtn.setOnClickListener(
                v -> changeInput("1")
        );

        twoBtn.setOnClickListener(
                v -> changeInput("2")
        );

        threeBtn.setOnClickListener(
                v -> changeInput("3")
        );

        fourBtn.setOnClickListener(
                v -> changeInput("4")
        );

        fiveBtn.setOnClickListener(
                v -> changeInput("5")
        );

        sixBtn.setOnClickListener(
                v -> changeInput("6")
        );

        sevenBtn.setOnClickListener(
                v -> changeInput("7")
        );

        eightBtn.setOnClickListener(
                v -> changeInput("8")
        );

        nineBtn.setOnClickListener(
                v -> changeInput("9")
        );

        plusBtn.setOnClickListener(
                v -> changeInput("+")
        );

        zeroBtn.setOnClickListener(
                v -> changeInput("0")
        );

        hashtagBtn.setOnClickListener(
                v -> changeInput("#")
        );


        dialBtn.setOnClickListener(
                v -> {
                    Log.d(LOG_TAG, "Call Button Clicked");
                    number = phoneNumberInput.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(number)));
                    startActivity(intent);
                }
        );

        speedDialBtn1.setOnLongClickListener(
                view -> {
                    btnClicked = 1;
                    Log.d(LOG_TAG, "Speed Dial Button 1 Long Clicked");
                    return handleLongClick();
                }
        );
        speedDialBtn2.setOnLongClickListener(
                view -> {
                    btnClicked = 2;
                    Log.d(LOG_TAG, "Speed Dial Button 2 Long Clicked");
                    return handleLongClick();
                }
        );
        speedDialBtn3.setOnLongClickListener(
                view -> {
                    btnClicked = 3;
                    Log.d(LOG_TAG, "Speed Dial Button 3 Long Clicked");
                    return handleLongClick();
                }
        );

        speedDialBtn1.setOnClickListener(
                v -> {
                    Log.d(LOG_TAG, "Speed Dial Button 1 Clicked");
                    if(speedDialContacts.containsKey(1)) {
                        phoneNumberInput.setText(Objects.requireNonNull(speedDialContacts.get(1))[1]);
                    }
                }
        );
        speedDialBtn2.setOnClickListener(
                v -> {
                    Log.d(LOG_TAG, "Speed Dial Button 2 Clicked");
                    if(speedDialContacts.containsKey(2)) {
                        phoneNumberInput.setText(Objects.requireNonNull(speedDialContacts.get(2))[1]);
                    }
                }
        );
        speedDialBtn3.setOnClickListener(
                v -> {
                    Log.d(LOG_TAG, "Speed Dial Button 3 Clicked");
                    if(speedDialContacts.containsKey(3)) {
                        phoneNumberInput.setText(Objects.requireNonNull(speedDialContacts.get(3))[1]);
                    }
                }
        );
    }

    protected boolean handleLongClick() {
        Intent intent = new Intent(this, SpeedDialContactsActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        data.getStringExtra(SpeedDialContactsActivity.EXTRA_REPLY);
                Log.d(LOG_TAG, reply);
                String[] contactInfo = reply.split(":");
                switch (btnClicked) {
                    case 1:
                        speedDialBtn1.setText(contactInfo[0]);
                        speedDialContacts.put(1, contactInfo);
                        Log.d(LOG_TAG, "Added Speed Dial Contact 1 with name=" + contactInfo[0] + "and number=" + contactInfo[1]);
                        break;
                    case 2:
                        speedDialBtn2.setText(contactInfo[0]);
                        speedDialContacts.put(2, contactInfo);
                        Log.d(LOG_TAG, "Added Speed Dial Contact 2 with name=" + contactInfo[0] + "and number=" + contactInfo[1]);
                        break;
                    case 3:
                        speedDialBtn3.setText(contactInfo[0]);
                        speedDialContacts.put(3, contactInfo);
                        Log.d(LOG_TAG, "Added Speed Dial Contact 3 with name=" + contactInfo[0] + "and number=" + contactInfo[1]);
                        break;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    protected void changeInput(String input) {
        String text = phoneNumberInput.getText().toString();
        if(input.equals("del")) {
            phoneNumberInput.setText(text.substring(0, text.length() - 1));
        } else {
            phoneNumberInput.setText(text+input);
        }
    }
}