package com.wj.training.readingtraining.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.base.BaseActivity;
import com.wj.training.readingtraining.bean.User;
import com.wj.training.readingtraining.common.VolleyInterface;
import com.wj.training.readingtraining.common.VolleyRequest;
import com.wj.training.readingtraining.util.Constants;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mName;
    private EditText mPwd;
    private EditText mPwdConfirm;
    private Button mRegisiter;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPwd = (EditText) findViewById(R.id.pwd);
        mPwdConfirm = (EditText) findViewById(R.id.pwdConfirm);
        mRegisiter = (Button) findViewById(R.id.regisiter);
        mBack = (Button) findViewById(R.id.back);

        mRegisiter.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regisiter:
                submit();
                break;
            case R.id.back:
                onBackPressed();
                this.finish();
                break;
        }
    }

    private void submit() {
        // validate
        String nameString = mName.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwdString = mPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwdString)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwdConfirmString = mPwdConfirm.getText().toString().trim();
        if (TextUtils.isEmpty(pwdConfirmString)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pwdString.equals(pwdConfirmString)){
            showFragmentToast(R.string.pwd_not_same);
            return;
        }

        registerUser(nameString,pwdString);
    }

    private void registerUser(String nameString, String pwdString) {
        String url  = Constants.BASE_URL+Constants.REGISTER_API+Constants.TOKEN+"&name="+nameString+"&psd="+pwdString;
        VolleyRequest.requestGet(this,url,"",null,new VolleyInterface(this, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    showFragmentToast(getString(R.string.register_ok ));
                    mName.setText("");
                    mPwd.setText("");
                    mPwdConfirm.setText("");
                }else if(code.equals(FAILED)){
                    showFragmentToast(getString(R.string.register_error ));
                }
            }
            @Override
            public void onError(VolleyError error) {
                showFragmentToast(R.string.unknown_error);
            }
        });
    }
}
