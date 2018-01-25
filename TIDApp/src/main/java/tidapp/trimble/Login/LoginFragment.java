package tidapp.trimble.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import tidapp.trimble.R;
import tidapp.trimble.databinding.FragmentLoginBinding;
import tidapp.trimble.home.HomeActivity;
import tidapp.trimble.utils.Util;

/**
 * Allows user to login using username and password
 * <p>
 * Created by Shashadhar on 27 Dec 2017.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    //region member variables

    /**
     * Used for progress indicator
     */
    private ProgressDialog progressDialog;

    /**
     * Presenter for this view
     */
    private LoginContract.Presenter mPresenter;

    /**
     * Used to store reference
     */
    private FragmentLoginBinding binding;
    //endregion member variables

    //region constructor

    /**
     * Default constructor
     */
    public LoginFragment() {

    }
    //endregion constructor


    //region life cycle methods

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.login.setOnClickListener(view -> loginButtonTap(view));
        return binding.getRoot();
    }
    //endregion lifecycle methods


    //region private methods

    /**
     * Handles login button tap
     * @param view view taped
     */
    public void loginButtonTap(View view) {
        if(validateCredentials()) {
            progressDialog = ProgressDialog.show(getActivity(), "Login", "Signing in...", true);
            mPresenter.loginUser(binding.editDriverID.getText().toString(), binding.editPassword.getText().toString());
        }
    }

    /**
     * validates the credentials entered
     * @return true or false
     */
    private boolean validateCredentials(){
        //return value
        boolean validCredential=false;

        if(null!= binding){
            String email=binding.editDriverID.getText().toString();
            String password=binding.editPassword.getText().toString();
            if(Util.checkValidEmail(email)){
                if(TextUtils.isEmpty(password)){
                 binding.editPassword.setError("Password is empty");
                }else{
                    validCredential=true;
                }

            }else{
                binding.editDriverID.setError("Not a valid email");
            }
        }
        return validCredential;
    }



    //region LoginContract.View interface method implementation

    /**
     * show message to user
     *
     * @param message message to be shown
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message).setTitle("Signin Failed");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * Sets the presenter
     * @param presenter Presenter
     */
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
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
     * Launch home activity
     *
     * @param loginResponse login response
     */
    @Override
    public void launchHomeActivity(@NonNull LoginResponse loginResponse) {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.putExtra(HomeActivity.EXTRA_LOGIN_INFO, loginResponse);
        startActivity(intent);
        getActivity().finish();
    }
    //endregion LoginContract.View interface method implementation

}
