package com.monstertechno.moderndashbord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    ImageView imageView,imageView1,imageView2;
    LinearLayout linearLayout_edittext;
    TextView text_login,text_forget,signup;

    ////////////////////////
    EditText editTextemail,editTextpass;
    Button buttonlogin;
    String  txtLogEmal  , txtLogpassword ;
   ProgressBar progressBar;


    private FirebaseAuth mAuth;
    String Emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.theme_color));

        editTextemail=findViewById(R.id.edtSignInEmail);
        editTextpass=findViewById(R.id.edtSignInPassword);

        imageView = findViewById(R.id.imageView1);
        imageView1 = findViewById(R.id.imageView2);
        imageView2= findViewById(R.id.imageView3);
        linearLayout_edittext=findViewById(R.id.linearLayout4);
        buttonlogin=findViewById(R.id.Button_login);
        text_login=findViewById(R.id.text_login);
        text_forget=findViewById(R.id.fpass);
        progressBar=findViewById(R.id.signInProgressBar);

        signup=findViewById(R.id.textViewRegester);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

       run_anim(imageView1);
       //   run_anim(imageView);
      // run_anim(imageView2);
        run_animtwo(linearLayout_edittext);
        run_animtwo(buttonlogin);
        run_anim(text_login);
        run_anim(text_forget);
        run_anim(signup);

        editTextemail.addTextChangedListener(this);
        editTextpass.addTextChangedListener(this);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/////////////////////////////////////////////////////////////////////////////////////////////////
                txtLogEmal = editTextemail.getText().toString();
                txtLogpassword=editTextpass.getText().toString();

                if (!TextUtils.isEmpty(txtLogEmal)){
                    if (txtLogEmal.matches(Emailpattern)){
                        if (!TextUtils.isEmpty(txtLogpassword)){

                           // SignInUser();
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else {editTextpass.setError(" Password  can't be empty !");}

                    }else {editTextemail.setError(" Enter a valid address please  !");}

                }else {editTextemail.setError(" Email can't be empty !");}

            }
        });
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void SignInUser() {
        progressBar.setVisibility(View.VISIBLE);
        buttonlogin.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(editTextemail.getText().toString(),editTextpass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error"+ e.getMessage() , Toast.LENGTH_SHORT).show();
            buttonlogin.setVisibility(View.VISIBLE);
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // animation methode
    void run_anim(View view){
        view.animate().alpha(1).setDuration(1600).translationY(0);
    }
    // Second animation methode
    void run_animtwo(View view){
        view.animate().alpha(1).setDuration(1600).translationY(-180);
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // acvtivation of button
        if (!editTextemail.getText().toString().isEmpty() && !editTextpass.getText().toString().isEmpty()){
            buttonlogin.setEnabled(true);
        }
    }
    @Override
    public void afterTextChanged(Editable editable) {
    }
}