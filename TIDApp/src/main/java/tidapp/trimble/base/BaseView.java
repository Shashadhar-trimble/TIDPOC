package tidapp.trimble.base;

/**
 * Base view interface,provides base methods for View
 * Created by Shashadhar on 26th Dec 2017.
 */
public interface BaseView<T> {

    /**
     * Sets the presenter to the view
     * @param presenter Presenter
     */
    void setPresenter(T presenter);

}
