package tidapp.trimble.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import tidapp.trimble.Login.LoginResponse;
import tidapp.trimble.LoginActivity;
import tidapp.trimble.R;
import tidapp.trimble.databinding.ActivityHomeBinding;
import tidapp.trimble.network.NetworkService;

/**
 * Home Activity class displays user after successful login
 * <p>
 * Created by Shashadhar on 24-Jan-2018
 */
public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    //region constants

    public static String EXTRA_LOGIN_INFO = "LOGIN_INFO";
    //endregion constants


    //region member variables

    /**
     * Used to store binding reference
     */
    ActivityHomeBinding homeBinding;

    /**
     * Used to store login response
     */
    LoginResponse mLoginResponse;

    /**
     * Used to store presenter reference
     */
    HomeContract.Presenter mPresenter;

    /**
     * Used for progress indicator
     */
    private ProgressDialog progressDialog;
    //endregion member variables


    //region life cycle methods

    /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling setContentView(int) to inflate the activity's UI, using findViewById(int) to
     * programmatically interact with widgets in the UI, calling managedQuery(android.net.Uri,
     * String[], String, String[], String) to retrieve cursors for data being displayed, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
            Intent intent;
            if (null != (intent = getIntent())) {
                mLoginResponse = intent.getParcelableExtra(EXTRA_LOGIN_INFO);
                setUser(mLoginResponse);
            }
            new HomePresenter(NetworkService.getInstance(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion life cycle methods

    //region private methods

    @SuppressLint("SetTextI18n")
    private void setUser(@NonNull LoginResponse loginResponse) {
        try {
            homeBinding.accessToken.setText(loginResponse.getAccessToken());
            homeBinding.refreshToken.setText(loginResponse.getRefreshToken());
            homeBinding.email.setText("Welcome\n" + loginResponse.getEmail());
            homeBinding.login.setOnClickListener(view -> {
                progressDialog = ProgressDialog.show(HomeActivity.this, "Login", "Refreshing Token...", true);
                mPresenter.refreshAccessToken(loginResponse.getRefreshToken());
            });
            homeBinding.logout.setOnClickListener(view -> {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion private methods


    //region interface HomeContract.View implementation

    /**
     * Sets the presenter to the view
     *
     * @param presenter Presenter
     */
    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Hides the progress dialog
     */
    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * show message to user
     *
     * @param message message to be shown
     */
    @Override
    public void showMessage(String message) {
        try {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message).setTitle("Refresh Failed");
            builder.setPositiveButton("OK", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets user info to view
     *
     * @param loginResponse loginResponse
     */
    @Override
    public void setUserInfo(LoginResponse loginResponse) {
        try {
            if (null != loginResponse) {
                mLoginResponse = loginResponse;
                homeBinding.accessToken.setText(loginResponse.getAccessToken());
                homeBinding.refreshToken.setText(loginResponse.getRefreshToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion interface HomeContract.View implementation
}
