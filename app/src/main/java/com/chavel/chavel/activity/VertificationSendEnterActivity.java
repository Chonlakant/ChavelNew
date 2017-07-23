package com.chavel.chavel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chavel.chavel.R;

public class VertificationSendEnterActivity extends AppCompatActivity {

    EditText ed_code;
    LinearLayout ls_send_code_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertifi_again);

        ls_send_code_again = (LinearLayout) findViewById(R.id.ls_send_code_again);

        ed_code = (EditText) findViewById(R.id.ed_code);

        Drawable drawable = ed_code.getBackground(); // get current EditText drawable
        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP); // change the drawable color

        if (Build.VERSION.SDK_INT > 16) {
            ed_code.setBackground(drawable); // set the new drawable to EditText

        } else {
            ed_code.setBackgroundDrawable(drawable);
        }

        ls_send_code_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), WhatsYourEmailActivity.class);
                startActivity(i);

            }
        });


    }

    public static int getSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            Log.e("sum", sum + "");
        }
        return sum;
    }
}
