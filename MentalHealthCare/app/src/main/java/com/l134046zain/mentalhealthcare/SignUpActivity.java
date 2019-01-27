package com.l134046zain.mentalhealthcare;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends Activity {


    private int PICK_IMAGE_REQUEST=1;

    private SimpleDateFormat dateFormatter;

    private DatePickerDialog pickerDialog;
private String image;
private String y="e"; // e = empty
    private String m;
    private String d;


    EditText _email;
    EditText _password;

    EditText _FullName;
    EditText _dob;

    Bitmap bitmap;
ImageView _profile;
    ImageView _uploadPicButn;
    Button _SignUPButn;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    public String dob;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        String result= Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open Home activity
            startActivity(new Intent(getApplicationContext(), Home.class));
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setDateTimeField();


        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.EmailWrapper2);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.PasswordWrapper2);
        final TextInputLayout nameWrapper = (TextInputLayout) findViewById(R.id.FullNameWrapper);



        usernameWrapper.setHint("Email Address");
        passwordWrapper.setHint("Password");
        nameWrapper.setHint("FullName");




        _dob= (EditText) findViewById(R.id.dob);


        _uploadPicButn= (ImageView) findViewById(R.id.uploadpicbutn);
        _profile= (ImageView) findViewById(R.id.profilepic);


        _SignUPButn= (Button) findViewById(R.id.SignUPButton);


        _dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDialog.show();
            }
        });


        _uploadPicButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });



_SignUPButn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        hideKeyboard();

        boolean credentialsOkay=true;
        String username = usernameWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();
        String name=nameWrapper.getEditText().getText().toString();

        usernameWrapper.setError(null);
        usernameWrapper.setErrorEnabled(false);
        passwordWrapper.setError(null);
        passwordWrapper.setErrorEnabled(false);
        nameWrapper.setError(null);
        nameWrapper.setErrorEnabled(false);

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

        if(!validateFullName(name))
        {
            nameWrapper.setErrorEnabled(true);
            nameWrapper.setError("Not a valid Name!");
            credentialsOkay=false;
        }

        if(y.equals("e"))
        {
            credentialsOkay=false;
        }

        if(credentialsOkay)
        {
            usernameWrapper.setError(null);
            usernameWrapper.setErrorEnabled(false);
            passwordWrapper.setError(null);
            passwordWrapper.setErrorEnabled(false);
            nameWrapper.setError(null);
            nameWrapper.setErrorEnabled(false);

            bitmap=((BitmapDrawable)_profile.getDrawable()).getBitmap();
            image= BitMapToString(bitmap);

            signup(image,username,password,name,y,m,d);
        }




    }
});


    }

    private void signup(final String image_Bitmap, String email, String password, final String fullname, final String year, final String month, final String day)
    {


        (firebaseAuth.createUserWithEmailAndPassword(email, password))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            UserInformation userInformation = new UserInformation(image_Bitmap,fullname,year,month,day);

                            //getting the current logged in user
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
                            databaseReference.child(user.getUid()).setValue(userInformation);
                            Intent i = new Intent(getApplicationContext(),Home.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
        return true;
    }

    public boolean validateFullName(String name){

        if(name.length()<=0) return false;

        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher= pattern.matcher(name);
        return matcher.matches();
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                if(year>1947 && year<2001) {
                    _dob.setText(dateFormatter.format(newDate.getTime()));
                    _dob.setError(null);
                    y = Integer.toString(year);
                    m = Integer.toString(monthOfYear);
                    d = Integer.toString(dayOfMonth);
                }
                else
                {
                    y="e";
                    _dob.setError("Year should be between 1947 and 2001");
                    Toast.makeText(SignUpActivity.this,"Year should be between 1947 and 2001 !",Toast.LENGTH_SHORT).show();

                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

         }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView imageView = (ImageView) findViewById(R.id.profilepic);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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



