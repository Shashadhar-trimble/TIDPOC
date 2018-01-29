package tidapp.trimble.Login;


import android.support.annotation.NonNull;

import tidapp.trimble.base.BasePresenter;
import tidapp.trimble.base.BaseView;

/**
 * LoginContract class provides the interface for login view and login presenter
 * <p>
 * Created by Shashadhar on 27 Dec 2017.
 */

public interface LoginContract {

    /**
     * Interface for login view
     * this interface should be implemented by the view
     */
    interface View extends BaseView<Presenter> {

        /**
         * Hides the progress dialog
         */
        void hideProgressDialog();

        /**
         * Launch home activity
         *
         * @param loginResponse login response
         */
        void launchHomeActivity(@NonNull LoginResponse loginResponse);

        /**
         * show message to user
         *
         * @param message message to be shown
         */
        @SuppressWarnings({"unused", "SameParameterValue"})
        void showMessage(String message);

    }

    /**
     * Interface for login presenter
     * this interface should be implemented by the presenter
     */
    interface Presenter extends BasePresenter {

        /**
         * Logs in user by calling TID api and uses observable
         *
         * @param email    email to login
         * @param password password
         */
        void loginUser(String email, String password);

        /**
         * Logs in user by calling TID api and uses call
         */
        void login();
    }

}
