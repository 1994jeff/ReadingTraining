package com.wj.training.readingtraining.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mName;
    private EditText mPwd;
    private Button mLogin;
    private Button mRegisiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(hasLogin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
            return;
        }
        initView();
    }


    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPwd = (EditText) findViewById(R.id.pwd);
        mLogin = (Button) findViewById(R.id.login);
        mRegisiter = (Button) findViewById(R.id.regisiter);

        mLogin.setOnClickListener(this);
        mRegisiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                submit();
                break;
            case R.id.regisiter:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
            Toast.makeText(this, "用户密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        login(nameString,pwdString);

    }

    private boolean hasLogin() {
        Log.i("jeff",""+Constants.getName(this)+","+Constants.getPsd(this)+","+Constants.getStatus(this));
        if(TextUtils.isEmpty(Constants.getName(this)) || TextUtils.isEmpty(Constants.getPsd(this))){
            return false;
        }
        if(!"2".equals(Constants.getStatus(this)))
        {
            return false;
        }
        return true;
    }

    private void login(String nameString,String pwdString) {
        String url  = Constants.BASE_URL+Constants.LOGIN_API+Constants.TOKEN+"&name="+nameString+"&psd="+pwdString;
        VolleyRequest.requestGet(this,url,"",null,new VolleyInterface(this, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    JSONArray jsonArray = object.getJSONArray(RET_DATA);
                    if(jsonArray!=null && jsonArray.size()>0){
                        int size = jsonArray.size();
                        User user = jsonArray.getObject(0, User.class);
                        if(user!=null)
                        {
                            if(user.getStatus().equals("1")){
                                showFragmentToast(getString(R.string.account_status_error ));
                                Constants.clearUser(LoginActivity.this);
                            }else if(user.getStatus().equals("2")){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                                Constants.setUser(LoginActivity.this,user);
                            }
                        }else {
                            showFragmentToast(getString(R.string.login_error ));
                        }
                    }
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                    Constants.clearUser(LoginActivity.this);
                }
            }
            @Override
            public void onError(VolleyError error) {
                showFragmentToast(R.string.unknown_error);
            }
        });
    }
}
