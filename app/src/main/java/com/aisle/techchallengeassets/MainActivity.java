package com.aisle.techchallengeassets;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aisle.techchallengeassets.service.ILoginService;
import com.aisle.techchallengeassets.service.model.Login;
import com.aisle.techchallengeassets.service.model.LoginRquest;
import com.aisle.techchallengeassets.util.ApiClient;
import com.aisle.techchallengeassets.util.Constants;
import com.aisle.techchallengeassets.util.CountryCode;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhone, editTextPhone2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        button = findViewById(R.id.button);
        TelephonyManager tm =  (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);

        String countryIso = tm.getSimCountryIso();
        String code = CountryCode.getPhone(countryIso);
        editTextPhone.setText(code);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editTextPhone2.getText().toString();
                if (phone != null && !phone.isEmpty()) {
//                    LoginRquest loginRquest = new LoginRquest();
//                    loginRquest.setNumber(code+phone);
                    fetchLogin(new LoginRquest(code+phone));
//                    Intent in = new Intent(MainActivity.this, OTP_Activity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    in.putExtra(Constants.EXTRA_PHONE, code + phone);
//                    startActivity(in);
                }
            }
        });
    }


    public void fetchLogin(LoginRquest login){

        ILoginService iLoginService = ApiClient.getClient().create(ILoginService.class);
        Call<Login> call = iLoginService.fetchLogin(login);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.code() == 200) {

                    Login messegeStatus = response.body();
                    boolean status = messegeStatus.getStatus();
                    if (status) {
                        //take to next
                        Intent in = new Intent(MainActivity.this, OTP_Activity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        in.putExtra(Constants.EXTRA_PHONE, login.getNumber());
                        startActivity(in);
                    }else {
                       //notify failure
                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_LONG).show();
                    }

                } else Toast.makeText(MainActivity.this, "response code "+ response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Login Failed:", Toast.LENGTH_LONG).show();
                Log.e("Login Failed: ", t.toString());
            }
        });

    }
}