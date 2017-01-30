package littletoknow.comrishabh.littletoknow;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FloatingLabels extends AppCompatActivity implements View.OnClickListener {

    Toolbar mtoolbar;
    EditText mName_ET, mEmail_ET, mPassword_ET;
    TextInputLayout mName_TIL, mEmail_TIL, mPassword_TIL;
    Button mSignup_BTN;
    private static final String TAG = FloatingLabels.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: fired");
        setContentView(R.layout.activity_floating_labels);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mName_ET = (EditText) findViewById(R.id.name_ET);
        mEmail_ET = (EditText) findViewById(R.id.email_ET);
        mPassword_ET = (EditText) findViewById(R.id.password_ET);
        mSignup_BTN = (Button) findViewById(R.id.signUp_Button);
        mtoolbar = (Toolbar) findViewById(R.id.toolBar);

        mName_TIL = (TextInputLayout) findViewById(R.id.name_textInputLayout);
        mEmail_TIL = (TextInputLayout) findViewById(R.id.Email_textInputLayout);
        mPassword_TIL = (TextInputLayout) findViewById(R.id.Password_textInputLayout);

        mSignup_BTN.setOnClickListener(this);
        setSupportActionBar(mtoolbar);

        mName_ET.addTextChangedListener(new MyTextWatcher(mName_ET));
        mEmail_ET.addTextChangedListener(new MyTextWatcher(mEmail_ET));
        mPassword_ET.addTextChangedListener(new MyTextWatcher(mPassword_ET));

    }

    private void submitForm() {
        Log.i(TAG, "submitForm: fired");

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Registration Done", Toast.LENGTH_LONG).show();
    }

    private boolean validatePassword() {
        Log.i(TAG, "validatePassword: fired");

        if (mPassword_ET.getText().toString().trim().isEmpty() || mPassword_ET.getText().toString().trim().length() < 8) {
            mPassword_TIL.setErrorEnabled(Boolean.TRUE);
            mPassword_TIL.setError(getResources().getString(R.string.err_msg_password));
            requestFocus(mPassword_ET);
            return false;

        } else {
            mPassword_TIL.setErrorEnabled(Boolean.FALSE);
        }
        return true;
    }

    private boolean validateEmail() {
        Log.i(TAG, "validateEmail: fired");

        String email = mEmail_ET.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            mEmail_TIL.setErrorEnabled(Boolean.TRUE);
            mEmail_TIL.setError(getResources().getString(R.string.err_msg_email));
            requestFocus(mEmail_ET);
            return false;
        } else {
            mEmail_TIL.setErrorEnabled(Boolean.FALSE);
        }

        return true;
    }

    private boolean validateName() {
        Log.i(TAG, "validateName: fired");

        if (mName_ET.getText().toString().trim().isEmpty()) {
            mName_TIL.setError(getResources().getString(R.string.err_msg_name));
            requestFocus(mName_ET);
            return false;
        } else {
            mName_TIL.setErrorEnabled(Boolean.FALSE);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        Log.i(TAG, "requestFocus: fired");
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        submitForm();
    }

    public void hideKB(View view) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    private class MyTextWatcher implements TextWatcher {

        View view;

        private MyTextWatcher(View view) {
            Log.i(TAG, "MyTextWatcher Constructor: fired");
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i(TAG, "beforeTextChanged: fired");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i(TAG, "onTextChanged: fired");

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.i(TAG, "afterTextChanged: fired");

            switch (view.getId()) {

                case R.id.name_ET:
                    validateName();
                    break;
                case R.id.password_ET:
                    validatePassword();
                    break;
                case R.id.email_ET:
                    validateEmail();
                    break;
            }

        }
    }
}
