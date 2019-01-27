package com.l134046zain.mentalhealthcare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    Button _loginButn;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();


        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open Home activity
            startActivity(new Intent(getApplicationContext(), Home.class));
        }


        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.EmailWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.PasswordWrapper);


        usernameWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");



        _loginButn= (Button) findViewById(R.id.LoginButton);



        _loginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard();
                boolean credentialsOkay=true;

                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();

                usernameWrapper.setError(null);
                usernameWrapper.setErrorEnabled(false);
                passwordWrapper.setError(null);
                passwordWrapper.setErrorEnabled(false);

                if (!validateEmail(username))
                {
                    usernameWrapper.setErrorEnabled(true);
                    usernameWrapper.setError("Not a valid email address!");
                    credentialsOkay=false;
                }
                if (!validatePassword(password))
                {
                    passwordWrapper.setErrorEnabled(true);
                    passwordWrapper.setError("Not a valid Password!");
                    credentialsOkay=false;
                }

                if(credentialsOkay==true)
                {
                    usernameWrapper.setError(null);
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setError(null);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin(username,password);
                }
            }
        });

    }


    private void doLogin(String email,String password)
    {

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);
        (firebaseAuth.signInWithEmailAndPassword(email,password))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Intent i = new Intent(LoginActivity.this, Home.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(i);
                        } else {
                            progressDialog.dismiss();
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
    public boolean validateEmail(String email)
    {
        String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
         Pattern pattern = Pattern.compile(EMAIL_PATTERN);
         Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password)
    {
        if(password.length()<6){return false;}
        return true;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        hideKeyboard();
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
//                INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.towards_top);
        super.onPause();
    }
}
