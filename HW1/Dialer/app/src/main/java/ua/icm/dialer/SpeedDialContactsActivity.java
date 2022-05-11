package ua.icm.dialer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpeedDialContactsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "ua.icm.dialer.extra.REPLY";
    private static final String LOG_TAG = SpeedDialContactsActivity.class.getSimpleName();


    protected EditText contactName;
    protected EditText phoneNumber;
    protected Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial_contacts);

        contactName = findViewById(R.id.contactName);
        phoneNumber = findViewById(R.id.phoneNumber);
        updateBtn = findViewById(R.id.updateContactBtn);

        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(LOG_TAG, "Update Button Clicked");
                        String reply = contactName.getText().toString() + ":" + phoneNumber.getText().toString();
                        Log.d(LOG_TAG, reply);
                        Intent replyIntent = new Intent();
                        replyIntent.putExtra(EXTRA_REPLY, reply);
                        setResult(RESULT_OK, replyIntent);
                        finish();
                    }
                }
        );
    }
}