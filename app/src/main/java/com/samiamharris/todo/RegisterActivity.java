package com.samiamharris.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by samharris on 2/23/14.
 */
public class RegisterActivity extends Activity {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private TextView mErrorField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameField = (EditText) findViewById(R.id.register_username);
        mPasswordField = (EditText) findViewById(R.id.register_password);
        mErrorField = (TextView) findViewById(R.id.error_messages);

    }

    public void register(final View v) {
        if(mUsernameField.getText().length() == 0 ||
                mPasswordField.getText().length() ==0) {
            return;
        }

        v.setEnabled(false);
        ParseUser user = new ParseUser();
        user.setUsername(mUsernameField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(RegisterActivity.this, Todo.class);
                    startActivity(intent);
                    finish();
                } else {
                    switch(e.getCode()){
                        case ParseException.USERNAME_TAKEN:
                            mErrorField.setText("Sorry, this username has already been taken.");
                            break;
                        case ParseException.USERNAME_MISSING:
                            mErrorField.setText("Sorry, you must supply a username to register.");
                            break;
                        case ParseException.PASSWORD_MISSING:
                            mErrorField.setText("Sorry, you must supply a password to register.");
                            break;
                        default:
                            mErrorField.setText(e.getLocalizedMessage());
                    }
                    v.setEnabled(true);
                    //sign up didn't succeed. Look at the ParseException
                    //to figure out what went wrong
                    v.setEnabled(true);
                }
            }
        });
    }
}
