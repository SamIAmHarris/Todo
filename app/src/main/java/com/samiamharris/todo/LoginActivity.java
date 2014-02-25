package com.samiamharris.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import com.parse.ParseException;

/**
 * Created by samharris on 2/23/14.
 */
public class LoginActivity extends Activity {

    private EditText mUsernameLogin;
    private EditText mPasswordLogin;
    private TextView mErrorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameLogin = (EditText) findViewById(R.id.login_username);
        mPasswordLogin = (EditText) findViewById(R.id.login_password);
        mErrorLogin = (TextView) findViewById(R.id.login_error_messages);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    public void signIn(final View v){
        v.setEnabled(false);
        ParseUser.logInInBackground(mUsernameLogin.getText().toString(), mPasswordLogin.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, Todo.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    switch (e.getCode()) {
                        case ParseException.USERNAME_TAKEN:
                            mErrorLogin.setText("Sorry, this username has already been taken.");
                            break;
                        case ParseException.USERNAME_MISSING:
                            mErrorLogin.setText("Sorry, you must supply a username to register.");
                            break;
                        case ParseException.PASSWORD_MISSING:
                            mErrorLogin.setText("Sorry, you must supply a password to register.");
                            break;
                        case ParseException.OBJECT_NOT_FOUND:
                            mErrorLogin.setText("Sorry, those credentials were invalid.");
                            break;
                        default:
                            mErrorLogin.setText(e.getLocalizedMessage());
                            break;
                    }
                    v.setEnabled(true);
                }
            }
        });
    }

    public void showRegistration(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }}
