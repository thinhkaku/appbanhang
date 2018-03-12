package com.example.administrator.phanmemqlbhonline.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 10/14/2017.
 */

public class LoginSystem extends BaseActivity {
    private Button btnLogin, btnRegister;
    private LinearLayout linearLogin;
    private ImageView imgBackground;
    private EditText edtUserName, edtPassWord;
    private Dialog dialog;
    private String MY_PREFS_NAME="thinhkaku";
    private CheckBox checkBox ;
    private int requester =50;
    private int count=0;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
        addEvent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.login;
    }

    private void addEvent() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.anim_translate1);
        //Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.anim_translate_layout);
        //linearLogin.startAnimation(animation2);
        //imgBackground.startAnimation(animation2);
        btnLogin.startAnimation(animation);
        btnRegister.startAnimation(animation1);
        edtPassWord.startAnimation(animation1);
        edtUserName.startAnimation(animation);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassWord.getText().toString();
                if (userName.isEmpty() ||password.isEmpty()){
                    Snackbar snackbar =Snackbar.make(v,"Bạn chưa nhập đủ thông tin",Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }else {
                    if (checkBox.isChecked()==true){
                        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("Taikhoan",edtUserName.getText()+"");
                        editor.putString("Matkhau",edtPassWord.getText()+"");
                        editor.apply();
                    }else {
                        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("Taikhoan",edtUserName.getText()+"");
                        editor.putString("Matkhau","");
                        editor.apply();
                    }
                    String  api=LoginConfig.login(userName,password);
                    loginAPI(api);
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSystem.this,Register.class);
                startActivityForResult(intent, requester);
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("Taikhoan",edtUserName.getText()+"");
                    editor.putString("Matkhau",edtPassWord.getText()+"");
                    editor.apply();
                }else {
                    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("Taikhoan",edtUserName.getText()+"");
                    editor.putString("Matkhau","");
                    editor.apply();

                }

            }
        });
    }
    private boolean load1() {
        SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences1.getBoolean("checked", false);
    }
    private void save1(final boolean isChecked) {

        SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putBoolean("checked", isChecked);
        editor.commit();
    }
    protected void initView() {
        linearLogin = (LinearLayout) findViewById(R.id.lnearLogin);
        imgBackground = (ImageView) findViewById(R.id.imgBackground);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnregister);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassWord = (EditText) findViewById(R.id.edtPassword);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        edtUserName.setText(prefs.getString("Taikhoan","emty"));
        edtPassWord.setText(prefs.getString("Matkhau","emty"));
        checkBox = (CheckBox)  findViewById(R.id.ckNhoMatKhau);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (checkBox.isChecked()) {
                    Toasty.Config.getInstance()
                            .setTextColor(Color.GREEN)
                            .apply();
                    Toasty.custom(LoginSystem.this, "Nhớ mật khẩu", getResources().getDrawable(R.drawable.rgb),
                            Color.BLACK, Toast.LENGTH_SHORT, true, true).show();
                    SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("checked", true);
                    editor.commit();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==requester||resultCode ==RESULT_OK){
            edtUserName.setText(data.getStringExtra("Username")+"");
            edtPassWord.setText(data.getStringExtra("PassWord")+"");
        }
    }
    private  void initDialogQuitApp(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_quit_app);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Button btnCo = (Button) dialog.findViewById(R.id.btnCo);
        Button btnKhong = (Button) dialog.findViewById(R.id.btnKhong);
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        btnKhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(LoginSystem.this,Act_tuy_chon_cho_nguoidung.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }

    @Override
    public void onSucess(String result) {
        if (result.equals("đăng nhập thành công")){
            Intent intent = new Intent(LoginSystem.this,Act_QuanLyCuaHang.class);
            intent.putExtra("userName",edtUserName.getText()+"");
            startActivity(intent);
            overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
        }else {
            Toast.makeText(this,"Đăng nhập thất bài! kiểm tra lại tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkBox.setChecked(load1());
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBox.setChecked(load1());
    }

    @Override
    protected void onPause() {
        super.onPause();
        save1(checkBox.isChecked());
    }
}
