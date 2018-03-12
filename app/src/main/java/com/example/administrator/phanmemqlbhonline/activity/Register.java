package com.example.administrator.phanmemqlbhonline.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;

public class Register extends BaseActivity {
    private Button btnRegister;
    private LinearLayout linearRegister;
    private Dialog dialog;
    private ImageView imgBackgrond;
    private  EditText edtUserName, edtPassword,edtRetype, edtFirstName,edtLassName,edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
        addEvent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    private void addEvent() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.anim_translate1);
        //Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.anim_translate_layout);
        //linearRegister.startAnimation(animation2);
        //imgBackgrond.startAnimation(animation2);
        btnRegister.startAnimation(animation);
        edtUserName.startAnimation(animation);
        edtLassName.startAnimation(animation1);
        edtFirstName.startAnimation(animation);
        edtPassword.startAnimation(animation1);
        edtRetype.startAnimation(animation);
        edtEmail.startAnimation(animation1);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String passWord = edtPassword.getText().toString();
                String retypePass = edtRetype.getText().toString();
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLassName.getText().toString();
                String email = edtEmail.getText().toString();
                if (userName.isEmpty() ||passWord.isEmpty()||retypePass.isEmpty() ||lastName.isEmpty()||firstName.isEmpty()||email.isEmpty()){
                    Snackbar snackbar=Snackbar.make(v,"Bạn chưa nhập đủ thông tin",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    if (passWord.equals(retypePass+"")){
                        String api= LoginConfig.singup(userName,passWord,email,firstName,lastName);
                        loginAPI(api);
                    }else {
                        Snackbar snackbar=Snackbar.make(v,"Bạn nhập sai mật khẩu",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }

                }
            }
        });
    }

    protected void initView() {
        linearRegister = (LinearLayout) findViewById(R.id.lineaRegister);
        imgBackgrond = (ImageView) findViewById(R.id.imgBackground1);
        btnRegister = (Button) findViewById(R.id.btnRegister1);
        edtPassword = (EditText) findViewById(R.id.edtPassword1);
        edtUserName = (EditText) findViewById(R.id.edtUserName1);
        edtRetype = (EditText) findViewById(R.id.edtRequester);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtFirstName = (EditText) findViewById(R.id.edtFisrtName);
        edtLassName = (EditText) findViewById(R.id.edtLastName);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Register.this,LoginSystem.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }

    @Override
    public void onSucess(String result) {
        if (result.equals("1")){
            initDialogThongBaoTaoThanhCong();
            dialog.show();
        }else {
            Toast.makeText(this,"Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
        }
    }
    private void initDialogThongBaoTaoThanhCong(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_thongbao_taotaikhoan_thanhcong);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Button btnOK= (Button) dialog.findViewById(R.id.btnOKK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String userName = edtUserName.getText().toString();
                String passWord = edtPassword.getText().toString();
                Intent intent = new Intent(Register.this,LoginSystem.class);
                intent.putExtra("Username",userName);
                intent.putExtra("PassWord",passWord);
                setResult(RESULT_OK,intent);
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                finish();
            }
        });
    }
}
