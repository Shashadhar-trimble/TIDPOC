package tidapp.trimble.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tidapp.trimble.R;
import tidapp.trimble.network.NetworkService;

/**
 * Login Activity class
 * Hosts login activity
 * <p>
 * Created by Shashadhar 25-Jan-2018
 */
public class LoginActivity extends AppCompatActivity {

    //region lifecycle methods

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginFragment fragment = new LoginFragment();
        findViewById(R.id.fragment_container);
        new LoginPresenter(NetworkService.getInstance(), fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

    }
    //endregion lifecycle methods
}
