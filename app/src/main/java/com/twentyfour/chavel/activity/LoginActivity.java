package com.twentyfour.chavel.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twentyfour.chavel.R;
import com.twentyfour.chavel.api.Apis;
import com.twentyfour.chavel.model.Test;
import com.twentyfour.chavel.service.ServiceApi;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.button;

public class LoginActivity extends AppCompatActivity {

    TextView accout;
    TextView forget;
    LinearLayout login;
    LinearLayout sign_up;
    LinearLayout ls_facebook;
    Button longButton;

    EditText ed_mail;
    EditText ed_pass;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION }, 1);


        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ls_facebook = (LinearLayout) findViewById(R.id.ls_facebook);

        longButton = (Button) findViewById(R.id.longButton);

        forget = (TextView) findViewById(R.id.forget);
        accout = (TextView) findViewById(R.id.accout);
        ed_mail = (EditText) findViewById(R.id.ed_mail);
        ed_pass = (EditText) findViewById(R.id.ed_pass);

        Drawable drawable = ed_mail.getBackground(); // get current EditText drawable
        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP); // change the drawable color

        if (Build.VERSION.SDK_INT > 16) {
            ed_mail.setBackground(drawable); // set the new drawable to EditText
            ed_pass.setBackground(drawable);
        } else {
            ed_mail.setBackgroundDrawable(drawable); // use setBackgroundDrawable because setBackground required API 16
            ed_pass.setBackgroundDrawable(drawable);
        }

        accout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SingInNextActivity.class);
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SetPasswordActivity.class);
                startActivity(i);
            }
        });

        ed_mail.addTextChangedListener(new TextWatcher() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){

                    longButton.setBackground(getResources().getDrawable(R.drawable.bg_unselected,null));
                } else {

                    longButton.setBackground(getResources().getDrawable(R.drawable.bg_selected,null));

                }


            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        longButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FollowUserActivity.class);
                startActivity(i);
            }
        });

        ls_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),FollowUserActivity.class);
                startActivity(i);
            }
        });

        clickLike();
    }

    public void toggleStatusSelect(Button v) {

        // change state
        v.setSelected(true);
        v.setPressed(false);

    }

    public void toggleUnSelect(Button v) {

        // change state
        v.setSelected(false);
        v.setPressed(false);

    }

    private void clickLike() {

        ServiceApi service = Apis.getClient().create(ServiceApi.class);

        Call<Test> userCall = service.getAllFilms();

        userCall.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
//               for(int i=0; i < response.body().getItems().size();i++) {
//
//
//               }
                Log.e("Size",response.body().getItems().size()+"");
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                Log.e("getLocalizedMessage",t.getLocalizedMessage());
                Log.e("getMessage",t.getMessage());

            }
        });

    }


}
