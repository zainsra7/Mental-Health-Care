package com.l134046zain.mentalhealthcare;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String email;
    String name;
    NavigationView navigationView;
    String bitmap;
    String image;
    ProgressDialog dialog;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager=getFragmentManager();



        setTitle("MHC | About");

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            bitmap=b.getString("Bitmap","");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_about);

//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        dialog = ProgressDialog.show(this, "UserDetails",
//                "Loading...", true);
//
//
//        FirebaseDatabase db=FirebaseDatabase.getInstance();
//        DatabaseReference data=db.getReference();
//        data.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(final DataSnapshot dataSnapshot) {
//
//                // Toast.makeText(getActivity(),"fv",Toast.LENGTH_LONG).show();
//                name=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").getValue().toString();
//
//                email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
//
//                image=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("image").getValue().toString();
//                Bitmap b=StringToBitMap(image);
//
//
//                View header=navigationView.getHeaderView(0);
//
//                ImageView imageView=(ImageView)header.findViewById(R.id.nav_header_ImageView);
//                TextView textView1=(TextView)header.findViewById(R.id.nav_header_email);
//                TextView textView2=(TextView)header.findViewById(R.id.nav_header_FullName);
//
//
//                imageView.setImageBitmap(b);
//                textView1.setText(email);
//                textView2.setText(name);
//                //Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
//
//                if(dialog.isShowing())
//                    dialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        navigationView.setCheckedItem(R.id.nav_about);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about)
        {
            navigationView.setCheckedItem(R.id.nav_about);
            setTitle("MHC | About");
            Intent intent = getIntent();
            finish();
            startActivity(intent);

        }
        if(id==R.id.nav_howto)
        {
            Intent intent=new Intent(getApplicationContext(),IntroActivity.class);
            startActivity(intent);
            navigationView.setCheckedItem(R.id.nav_about);
        }

        else if (id == R.id.nav_progress)
        {
            Intent intent=new Intent(getApplicationContext(),ProgressActivity.class);
            startActivity(intent);
            navigationView.setCheckedItem(R.id.nav_about);


        } else if (id == R.id.nav_profile)
        {
            int count=fragmentManager.getBackStackEntryCount();

            navigationView.setCheckedItem(R.id.nav_profile);
            setTitle("MHC | Profile");

            if(count==0)
            {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profile = new ProfileFragment();
                fragmentTransaction.add(R.id.ParentHome, profile, "PROFILE_Frag");
                fragmentTransaction.addToBackStack("Profile");
                fragmentTransaction.commit();
            }
            else
            {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profile = new ProfileFragment();
                fragmentTransaction.replace(R.id.ParentHome, profile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }
        else if (id == R.id.nav_exercise)
        {


            Intent intent=new Intent(getApplicationContext(),Exercise.class);
            startActivity(intent);
            navigationView.setCheckedItem(R.id.nav_about);

        }
        else if (id == R.id.nav_forum) {


            Intent intent=new Intent(getApplicationContext(),Forum_main.class);
            startActivity(intent);
            navigationView.setCheckedItem(R.id.nav_about);

        }
        else if (id == R.id.nav_logout)
        {
            FirebaseAuth.getInstance().signOut();
          Intent intent=new Intent(getApplicationContext(),StartActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
            finish();
        }

        else if (id == R.id.nav_test)
        {
            Intent intent=new Intent(getApplicationContext(),TakeTest.class);
            startActivity(intent);
            navigationView.setCheckedItem(R.id.nav_about);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
