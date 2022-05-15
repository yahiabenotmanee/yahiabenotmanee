package com.monstertechno.moderndashbord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtFullname , edtEmal , edtMobil , edtpassword , edtpasswordconfirme;
    //ProgressBar progressBar;

    Button signtomain;
    String txtedtFullname , txtedtEmal , txtedtMobil , txtpassword , txtpasswordconfirme;

    private FirebaseAuth mAuth;
    String Emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //////////////                   rabt               ///////////////////
        login=findViewById(R.id.textViewlogin);
        signtomain=findViewById(R.id.buttonsigintohome);
        edtFullname = findViewById(R.id.edtSignUpFullName);
        edtEmal = findViewById(R.id.edtSignUpEmail);
        edtMobil = findViewById(R.id.edtSignUpMobile);
        edtpassword = findViewById(R.id.edtSignUpPassword);
        //edtpasswordconfirme = findViewById(R.id.edtSignUpConfirmPassword);
        //progressBar = findViewById(R.id.pr)
        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();

        ////////////////////////////////    status bar color    /////////////////////////////////////
        getWindow().setStatusBarColor(ContextCompat.getColor(RegisterActivity.this,R.color.theme_color));

        signtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  get on text
                txtedtFullname = edtFullname.getText().toString();
                txtedtEmal = edtEmal.getText().toString().trim();
                txtedtMobil= edtMobil.getText().toString().trim();
                txtpassword=edtpassword.getText().toString().trim();
                txtpasswordconfirme=txtpassword;
                        //edtpasswordconfirme.getText().toString().trim();

                // check conditions
                if (!TextUtils.isEmpty(txtedtFullname)){
                    if (!TextUtils.isEmpty(txtedtEmal)){
                        if (txtedtEmal.matches(Emailpattern)){
                            if (!TextUtils.isEmpty(txtedtMobil)){
                                if (txtedtMobil.length()==10){
                                    if (!TextUtils.isEmpty(txtpassword)){
                                        if (!TextUtils.isEmpty(txtpasswordconfirme)){
                                            if (txtpasswordconfirme.equals(txtpassword)){
                                                // Call methode
                                                //SignUpUser();

                                                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();


                                            }else{edtMobil.setError(" Password confirme and password should be not the same  !");}

                                        }else{edtMobil.setError(" Password confirme can't be empty !");}

                                    }else{edtMobil.setError(" Password can't be empty !");}

                                }else{edtMobil.setError(" Enter a valid number phone "); }

                            }else{ edtMobil.setError(" Mobil number can't be empty !");}

                        }else{ edtEmal.setError(" Enter a valid email Address "); }

                    }else{ edtEmal.setError(" email can't be empty !"); }

                }else{ edtFullname.setError(" Full name can't be empty !"); }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

 //Siggnup user methode

    private void SignUpUser() {
        signtomain.setVisibility(View.INVISIBLE);
        mAuth.createUserWithEmailAndPassword(txtedtEmal,txtpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(RegisterActivity.this, "Sign up Successful ", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                signtomain.setVisibility(View.VISIBLE);
            }
        });
    }

}