package andres.cl.matchingteam.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;

import java.util.Arrays;

import andres.cl.matchingteam.BuildConfig;
import andres.cl.matchingteam.MainActivity;
import andres.cl.matchingteam.R;


public class LoginActivity extends AppCompatActivity implements LoginCallback{

    private int RC_SIGN_IN=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        new LoginValidation(this).validate();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode){
            if (resultCode == ResultCodes.OK){
                logged();
            } else{
                singIn();
            }
        }
    }

    @Override
    public void singIn(){

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .build(),
                RC_SIGN_IN);
    }
    @Override
    public void logged(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
