package com.aisle.techchallengeassets;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aisle.techchallengeassets.service.ILoginService;
import com.aisle.techchallengeassets.service.model.Login;
import com.aisle.techchallengeassets.service.model.LoginRquest;
import com.aisle.techchallengeassets.service.model.OTP_Resp;
import com.aisle.techchallengeassets.service.model.OtpRequest;
import com.aisle.techchallengeassets.util.ApiClient;
import com.aisle.techchallengeassets.util.Constants;

public class OTP_Activity extends AppCompatActivity {
    int time=59;
    private EditText enterOTP;
    private Button button;
    private TextView textPhone,timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        enterOTP = findViewById(R.id.editTextPhone);
        textPhone = findViewById(R.id.textGetOTP);
        timer = findViewById(R.id.timer);
        button = findViewById(R.id.button);
        String number = getIntent().getStringExtra(Constants.EXTRA_PHONE);
        textPhone.setText(number);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = enterOTP.getText().toString();
                if (otp != null && !otp.isEmpty()) {
                    verifyOtp(new OtpRequest(number, otp));
//                    Intent in = new Intent(OTP_Activity.this, HomeActivity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(in);
                }
            }
        });

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+checkDigit(time));
                time--;
            }

            public void onFinish() {
                timer.setText("try again");
            }

        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }


    public void verifyOtp(OtpRequest login){

        ILoginService iLoginService = ApiClient.getClient().create(ILoginService.class);
        Call<OTP_Resp> call = iLoginService.fetchOtp(login);

        call.enqueue(new Callback<OTP_Resp>() {
            @Override
            public void onResponse(Call<OTP_Resp> call, Response<OTP_Resp> response) {

                if (response.code() == 200) {

                    OTP_Resp messegeStatus = response.body();
                    String token = messegeStatus.getToken();
                    Intent in = new Intent(OTP_Activity.this, HomeActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    in.putExtra(Constants.EXTRA_TOKEN, token);
                    startActivity(in);

                } else Toast.makeText(OTP_Activity.this, "response code "+ response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<OTP_Resp> call, Throwable t) {
                Toast.makeText(OTP_Activity.this, "Login Failed:", Toast.LENGTH_LONG).show();
                Log.e("Login Failed: ", t.toString());
            }
        });

    }
}