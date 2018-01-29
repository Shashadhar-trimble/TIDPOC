package tidapp.trimble.home;


import tidapp.trimble.Login.LoginResponse;
import tidapp.trimble.base.BasePresenter;
import tidapp.trimble.base.BaseView;

/**
 * HomeContract class provides the interface for login view and login presenter
 * Created by Shashadhar on 27 Dec 2017.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        /**
         * Hides the progress dialog
         */
        void hideProgressDialog();

        /**
         * show message to user
         *
         * @param message message to be shown
         */
        @SuppressWarnings("all")
        void showMessage(String message);

        /**
         * Sets user info to view
         *
         * @param loginResponse loginResponse
         */
        void setUserInfo(LoginResponse loginResponse);

        /**
         * Launch login activity
         */
        void launchLoginActivity();
    }

    /**
     * Interface for Home presenter
     * this interface should be implemented by the presenter
     */
    interface Presenter extends BasePresenter {

        /**
         * Retrieves the refresh token
         *
         * @param refreshToken refresh token
         */
        void refreshAccessToken(String refreshToken);

        /**
         * Logs out user
         *
         * @param accessToken access token of the user
         */

        void logoutUser(String accessToken);
    }

}
