package umn.ac.id.muhammadakmalalhaqi_18087_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText etPass,etUN;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPass = (EditText) findViewById(R.id.etpass);
        etUN = (EditText) findViewById(R.id.etUN);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUN.getText().toString().equals("utsmobile") && etPass.getText().toString().equals("utsmobilegenap")){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Username/Password Salah!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
