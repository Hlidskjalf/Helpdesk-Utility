package function;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ButtonFunctions {

    /**
     * Simple method to spawn a command prompt and run the specified command.
     * @param cmd
     */
    private static void runCMD(String cmd) {
        try {
            Process rt = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Temporary method to create and display the command to ensure it will be
     * correct when moved to the target environment.
     * @param cmd
     */
    private static void commandBuilder(String cmd) {
        Alert cmdBuilder = new Alert(Alert.AlertType.INFORMATION);
        cmdBuilder.setTitle("Command Builder");
        cmdBuilder.setHeaderText("The command you are trying to run is: ");
        String info = cmd;
        cmdBuilder.setContentText(info);
        cmdBuilder.show();
    }

    public static void loadAboutInfoAlpha() {
        // Set the image to use here
        Image logo = new Image("res/d.png");

        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setGraphic(new ImageView(logo));
        about.setHeaderText("FCE Helpdesk Utility Version 1.8.0");
        String info = "Welcome to the Alpha Test of the FCE Helpdesk Utility. "
                + "This application is designed to make the basic tasks of the "
                + "helpdesk a tiny bit easier by combining common tasks into "
                + "clickable activities. Many additional functions are still "
                + "under development and should be in upcoming releases.";
        about.setContentText(info);
        about.show();
    }

    public static void constantPing(String storeNumber) {
        String cmd = primer + "ping fce-" + storeNumber + " -t";

        commandBuilder(cmd);
    }

    public static void pingBackOffice(String storeNumber) {
        String cmd = primer + "ping fce-" + storeNumber;

        commandBuilder(cmd);
    }

    public static void pingDevice(String value, String storeNumber) {
        String cmd = primer + "ping " + value + "-" + storeNumber + " -t";

        commandBuilder(cmd);
    }

    public static void pingNode(String value, String storeNumber) {
        String cmd = primer + psexec + sc + storeNumber + scuser + scpw + "-h ping " + value + " -t";

        commandBuilder(cmd);
    }

    public static void rebootBackOffice (String storeNumber) {
        String cmd = primer + psexec + bo + storeNumber + bouser + bopw + "shutdown -r -f";

        commandBuilder(cmd);
    }

    public static void rebootSiteController (String storeNumber) {
        String cmd = primer + psexec + sc + storeNumber + scuser + scpw + "shutdown -r -f";

        commandBuilder(cmd);
    }

    public static void rebootPOS (String rval, String storeNumber) {
        String cmd = primer + psexec + sc + storeNumber + scuser + scpw + "mcreset " + rval;

        commandBuilder(cmd);
    }

    public static void rebootKiosk (String rval, String storeNumber) {
        String cmd = primer + psexec + sc + storeNumber + scuser + scpw + "mcreset " + rval;

        commandBuilder(cmd);
    }

    public static void rebootOther (String rval, String storeNumber) {
        String cmd = primer + psexec + sc + storeNumber + scuser + scpw + "mcreset " + rval;

        commandBuilder(cmd);
    }

    public static void termSoftware (String rval, String storeNumber) {
        String cmd = primer + psexec + bo + storeNumber + bouser + bopw + "taskkill /f /im " + rval;

        commandBuilder(cmd);
    }

    public static void cleanUp (String storeNumber) {
        String cmd = primer + psexec + bo + storeNumber + bouser + bopw + "C:\\Install\\clean_up.bat";

        commandBuilder(cmd);
    }

    public static void close_app() {
        System.exit(0);
    }
}
