package com.sugarseries.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegis;
    EditText inputEmail, inputPassword, inputPassword2;
    String email,password1,password2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegis =findViewById(R.id.btn_register);
        inputEmail = findViewById(R.id.et_email);
        inputPassword = findViewById(R.id.et_password);
        inputPassword2 = findViewById(R.id.et_repassword);
        mAuth = FirebaseAuth.getInstance();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekRegis();
            }
        });

        View labelLogin = findViewById(R.id.Logintext);
        labelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
    private void cekRegis() {
        email = inputEmail.getText().toString();
        password1 = inputPassword.getText().toString();
        password2 = inputPassword2.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(email.isEmpty() || password1.isEmpty() || password2.isEmpty()){
                            Toast.makeText(RegisterActivity.this, "Mohon isi semua data",Toast.LENGTH_LONG).show();
                        }else {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registrasi Berhasil",Toast.LENGTH_LONG).show();

                                inputEmail.setText("");
                                inputPassword.setText("");
                                inputPassword2.setText("");
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                            } else {
                                Toast.makeText(RegisterActivity.this, "Registrasi Gagal",Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }
}