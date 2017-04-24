package com.yandex.auriwield.yandextranslate.presentation.presenters;

/**
 * @author g.zaripov
 */
public interface BasePresenter {

    /**
     * Method that control the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onCreate()/OnViewCreated method.
     */
    void create();

    /**
     * Method that control the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onStart() method.
     */

    void start();

    /**
     * Method that control the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */

    void resume();

    /**
     * Method that controls the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    void stop();

    /**
     * Method that control the lifecycle lang the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);


    void userVisible();
}
