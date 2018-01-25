package tidapp.trimble.base;

/**
 * BasePresenter class, provides the base methods for the presenter
 *
 * Created by Shashadhar on 27th Dec 2017
 */
public interface BasePresenter {
    /**
     * Method to subscribe to presenter
     */
    @SuppressWarnings("unused")
    void subscribe();

    /**
     * Method to unsubscribe from presenter
     */
    @SuppressWarnings({"SpellCheckingInspection","unused"})
    void unsubscribe();

}
