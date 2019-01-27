package com.l134046zain.mentalhealthcare;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Zan on 11/16/2016.
 */
public class ProfileFragment extends Fragment
{

    private int PICK_IMAGE_REQUEST=1;
    private String y;
    private String m;
    private String d;
    private SimpleDateFormat dateFormatter;
    ProgressDialog dialog;

    private DatePickerDialog pickerDialog;
    private String orignalemail;
private String dob;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    EditText _email;
    EditText _password;

    EditText _FullName;
    EditText _dob;
    Bitmap bitmap;
    ImageView _uploadPicButn;
    ImageView _uploadpic2;
    Button _SignUPButn;
    private String image;
    private DataSnapshot dataSnapshot;

    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        String result= Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setDateTimeField();

        _email= (EditText) getView().findViewById(R.id.EmailSignUPEdit2);

       _password= (EditText) getView().findViewById(R.id.PasswordSignUPEdit2);

        _dob= (EditText) getView().findViewById(R.id.dob2);



        _uploadPicButn= (ImageView) getView().findViewById(R.id.uploadpicbutn2);
        _uploadpic2= (ImageView) getView().findViewById(R.id.profilepic2);
        _FullName= (EditText) getView().findViewById(R.id.FullNameSignUPEdit2);


        _SignUPButn= (Button) getView().findViewById(R.id.SignUPButton2);


        dialog = ProgressDialog.show(getActivity(), "UserDetails",
                "Loading...", true);
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference data=db.getReference();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
               // Toast.makeText(getActivity(),"fv",Toast.LENGTH_LONG).show();
                String name=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").getValue().toString();
                _FullName.setText(name);
                orignalemail=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                _email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail() );
                String year=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("year").getValue().toString();
String month=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("month").getValue().toString();
String day=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("day").getValue().toString();

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));


                            _dob.setHint(dateFormatter.format(newDate.getTime()));

                y = (year);
                m =(month);
                d = (day);


//                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                // _password.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("password").getValue().toString());
                String image2=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("image").getValue().toString();
Bitmap b=StringToBitMap(image2);
                _uploadpic2.setImageBitmap(b);
                //Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                if(dialog.isShowing())
                    dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

                //CheckUserCredentials

                if(checkEmail(_email) && checkFullName(_FullName)  && (_dob.getHint().equals("Date of Birth")==false))
                {
                    bitmap=((BitmapDrawable)_uploadpic2.getDrawable()).getBitmap();

                    image= BitMapToString(bitmap);

                   //Save Changes and display message
                 if(_password.getText().length()>0)
                 {
                     FirebaseUser user = firebaseAuth.getCurrentUser();
                     Toast.makeText(getActivity(),_password.getText().toString(),Toast.LENGTH_LONG).show();
                     user.updatePassword(_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Password Updated successfully",Toast.LENGTH_LONG).show();

                         }
                         else {

                             Log.e("ERROR", task.getException().toString());
                             Toast.makeText(getActivity(),"Password Error:"+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                         }


                     }

                 }
                     );

                 }


                    FirebaseUser user = firebaseAuth.getCurrentUser();


                    UserInformation userInformation = new UserInformation(image,_FullName.getText().toString(),y,m,d);
                    databaseReference.child(user.getUid()).setValue(userInformation);
                    Toast.makeText(getActivity(), "Profile Update successful", Toast.LENGTH_LONG).show();
                }
                else
                {

                        Toast.makeText(getView().getContext(),"Please Enter correct Value !",Toast.LENGTH_SHORT).show();

                        if (checkEmail(_email) == false) {
                            _email.getText().clear();
                        } else if (checkFullName(_FullName) == false) {
                            _FullName.getText().clear();
                        }
                    }

            }
        });


    }


    public boolean checkEmail(EditText mail)
    {

        String _EmailAddress=mail.getText().toString().trim();


        String _emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (_EmailAddress.matches(_emailPattern))
        {

            return true;
        }

        Toast.makeText(getActivity().getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();


        return false;
    }

    public boolean checkFullName(EditText name){

        if(name.length()<=0) return false;

        String regx = "^[\\p{L} .'-]+$";
        String FullName=name.getText().toString();

        if(FullName.matches(regx))
        {
            return true;
        }

        Toast.makeText(getActivity().getApplicationContext(),"Invalid Name",Toast.LENGTH_SHORT).show();

        return false;}

    public void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        pickerDialog = new DatePickerDialog(getView().getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                if (year > 1947 && year < 2001) {
                    _dob.setHint(dateFormatter.format(newDate.getTime()));

                    y = Integer.toString(year);
                    m = Integer.toString(monthOfYear);
                    d = Integer.toString(dayOfMonth);
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                ImageView imageView = (ImageView) getView().findViewById(R.id.profilepic2);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
