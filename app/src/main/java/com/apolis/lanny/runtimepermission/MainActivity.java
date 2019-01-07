package com.apolis.lanny.runtimepermission;

import android.Manifest.permission;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }else{
                    takingPermission();
                }
            }
        });
    }

    private void takingPermission() {
        //try to educate the user to accept the user
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission.SEND_SMS)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("SMS state Dialog");
            builder.setMessage("If you deny, you cannot send the SMS");
            builder.setCancelable(false);

            builder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            permission.SEND_SMS
                    }, 1);

                }
            });

            builder.setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    permission.SEND_SMS
            }, 1);
        }
    }
}
