package com.haibuzou.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hywin on 2017/6/30.
 * 权限申请工具类
 */

public class PermissionTool {

    private String[] permissions;
    private List<String> deniedPermssions;
    private Context context;
    private Activity activity;
    private Fragment fragment;
    public static final int REQUEST_PERMISSION_CODE = 111;

    private PermissionTool(Activity activity){
        context = activity;
        this.activity = activity;
        deniedPermssions = new ArrayList<>();
    }

    private PermissionTool(Fragment fragment){
        context = fragment.getContext();
        this.fragment = fragment;
        deniedPermssions = new ArrayList<>();
    }

    public static PermissionTool init(Activity activity){
        return new PermissionTool(activity);
    }

    public static PermissionTool init(Fragment fragment){
        return new PermissionTool(fragment);
    }

    private void findDenyPermission(){
        if(permissions == null || permissions.length < 1){
            return;
        }

        for(String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                deniedPermssions.add(permission);
            }
        }
    }

    public void requestPermission(String... permissions){
        this.permissions = permissions;
        findDenyPermission();
        if(activity == null) {
            fragment.requestPermissions(permissions,REQUEST_PERMISSION_CODE);
        }else {
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSION_CODE);
        }
    }

    public void handlerPermissionResult(int requestCode,int[] grantResults){
        if(requestCode==REQUEST_PERMISSION_CODE) {

        }
    }


}
