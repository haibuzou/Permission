package com.haibuzou.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionRequest(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        });

        findViewById(R.id.goto_new_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PermissionActivity.class));
            }
        });
    }

    public void permissionRequest(String permission){
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            //已经拒绝过
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                Toast.makeText(this,"请允许权限",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{permission},101);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{permission},101);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 101) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"授权成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
