package tidapp.trimble;

import android.app.Application;

/**
 * Application class for the application
 * Created by Shashadhar on 28-Dec-17.
 */

public class TIDApplication extends Application {

    //region constants

    /**
     * Application id used for TID integration
     */
    // TODO: 29-Jan-18 Move to build config
    public static String applicationID = "Snhy2tWj2Nf8boxA9UzZdNQmwiAa";

    /**
     * Application secret used for TID integration
     */
    // TODO: 29-Jan-18 Mover to build config
    public static String clientSecret = "dkXKjfBTJpZfhvytf_ICORZ8V88a";
    //endregion constants


    //region life cycle methods
    /**
     * Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using lazy initialization of state) since the time spent in this function directly impacts the performance of
     * starting the first activity, service, or receiver in a process. If you override this method, be sure to call super.onCreate().
     *If you override this method you must call through to the superclass implementation.
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }
    //endregion life cycle methods

}
