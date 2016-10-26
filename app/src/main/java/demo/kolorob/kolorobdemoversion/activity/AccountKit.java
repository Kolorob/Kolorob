package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by israt.jahan on 10/23/2016.
 */

public class AccountKit extends AppCompatActivity {
    AccessToken accessToken = null;
    public static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        com.facebook.accountkit.AccountKit.initialize(getApplicationContext());
        setContentView(R.layout.activity_acc);

        accessToken = com.facebook.accountkit.AccountKit.getCurrentAccessToken();

        if (accessToken != null) {
Intent intent=new Intent(this,PlaceSelectionActivity.class);
            startActivity(intent);
            finish();
        } else {
            goToLogin(true);
        }
    }

    public void goToLogin(boolean isSMSLogin) {

        LoginType loginType = isSMSLogin ? LoginType.PHONE : LoginType.EMAIL;

        final Intent intent = new Intent(this, AccountKitActivity.class);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.TOKEN);


        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        this.startActivityForResult(intent, APP_REQUEST_CODE);

    }

    @Override
    public void onBackPressed() {

    }
}

