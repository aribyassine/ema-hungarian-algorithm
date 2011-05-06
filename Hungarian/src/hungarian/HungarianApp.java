/*
 * HungarianApp.java
 */

package hungarian;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class HungarianApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new HungarianView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of HungarianApp
     */
    public static HungarianApp getApplication() {
        return Application.getInstance(HungarianApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(HungarianApp.class, args);
    }
}
