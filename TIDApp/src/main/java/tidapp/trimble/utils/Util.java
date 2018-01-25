package tidapp.trimble.utils;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class has the utils methods which are most commonly used
 * <p>
 * * Created by Shashadhar on 22-Jan-18.
 */
public class Util {
    //region constants

    /**
     * Tag used for logging
     */
    private static String TAG = Util.class.getSimpleName();

    /**
     * Pattern used to check valid email
     */
    @SuppressWarnings("all")
    private static final String EMAIL_PATTERN = String.format("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    //endregion constants


    //region public methods

    /**
     * Encodes a serializable object into a Base64 string
     *
     * @param object Serializable object to encode to Base64 string
     * @return Base64 encoded string of the serialized object
     */
    @Nullable
    @CheckResult
    @SuppressWarnings("unused")
    public static String toBase64String(@NonNull Serializable object) {
        // return value
        String encodedData = null;

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(object);
            } catch (Exception ex) {
                Log.w(TAG, ex);
            }

            encodedData = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        } catch (Exception ex) {
            Log.w(TAG, ex);
        }

        // return to caller
        return encodedData;
    }

    /**
     * Encodes a serializable object into a Base64 string
     *
     * @param object String object to encode to Base64 string
     * @return Base64 encoded string of the serialized object
     */
    @Nullable
    @CheckResult
    public static String toBase64String(@NonNull String object) {
        // return value
        String encodedData = null;
        try {
            encodedData = Base64.encodeToString(object.getBytes(), Base64.NO_WRAP);
        } catch (Exception ex) {
            Log.w(TAG, ex);
        }

        // return to caller
        return encodedData;
    }

    /**
     * Checks if email is a valid or not
     *
     * @param email email to be validated
     * @return true if valid else false
     */
    @SuppressWarnings("unused")
    public static boolean checkValidEmail(@NonNull String email) {
        //return value
        boolean validEmail = false;
        try {
            if (!TextUtils.isEmpty(email)) {
                validEmail = email.matches(EMAIL_PATTERN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validEmail;
    }

    //endregion public methods

}
