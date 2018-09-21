package layout;

import function.ButtonFunctions;
import function.HDU_Logger;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class Controller {


    public static String storeNumber = "****";

    /************************
     * FXML Initializations *
     ************************/

    @FXML
    Label ping_sn, reboot_sn, term_sn;

    @FXML
    Button pingBO, pingConstant;

    // Device group
    @FXML
    RadioButton pingATG, pingCommander, pingSC, ping315;

    @FXML
    ToggleGroup pingDevices;

    // NCR Nodes group
    @FXML
    RadioButton node1, node2, node3, node45, node46, node47;

    @FXML
    ToggleGroup pingNodes;

    // Reboot groups
    @FXML
    RadioButton rebootPOS1, rebootPOS2, rebootPOS3, rebootK45, rebootK46, rebootK47, rebootOFC, rebootKPS;

    @FXML
    ToggleGroup rebootPOS, rebootKiosk, rebootOther;

    // Processes group
    @FXML
    RadioButton pdiDT, openOffice, chrome, firefox, ie;

    @FXML
    ToggleGroup reporting, browsers;

    /******************
     * Button hookups *
     ******************/


    /* Ping tab button group */
    @FXML
    void execConstantPing() throws IOException {
        String msg = " User initiated a constant ping on site: ";
        HDU_Logger.appendLog(msg, storeNumber);
        ButtonFunctions.constantPing(storeNumber);
    }

    @FXML
    void execRegularPing() throws IOException {
        String msg = " User initiated a ping on site: ";
        HDU_Logger.appendLog(msg, storeNumber);
        ButtonFunctions.pingBackOffice(storeNumber);
    }

    @FXML
    void checkRBTNDevices() throws IOException {
        pingATG.setUserData("atg");
        pingCommander.setUserData("commander");
        pingSC.setUserData("radiant");
        ping315.setUserData("cybera");

        if (pingDevices.getSelectedToggle() != null) {
            String rval = (pingDevices.getSelectedToggle().getUserData().toString());
            String msg = " User initiated a ping on " + rval + "-";
            HDU_Logger.appendLog(msg, storeNumber);
            ButtonFunctions.pingDevice(rval, storeNumber);
        }

    }

    @FXML
    void checkRBTNNodes() throws IOException {
        node1.setUserData("10.96.22.1");
        node2.setUserData("10.96.22.2");
        node3.setUserData("10.96.22.3");
        node45.setUserData("10.96.22.45");
        node46.setUserData("10.96.22.46");
        node47.setUserData("10.96.22.47");

        if (pingNodes.getSelectedToggle() != null) {
            String rval = (pingNodes.getSelectedToggle().getUserData().toString());
            String msg = " User initiated a ping on node " + rval;
            HDU_Logger.appendLog(msg);
            ButtonFunctions.pingNode(rval, storeNumber);
        }

    }

    /* Reboot tab button group */

    @FXML
    void rebootBPOC() throws IOException {
        String msg = " User initiated a reboot at: FCE-" + storeNumber;
        HDU_Logger.appendLog(msg);
        ButtonFunctions.rebootBackOffice(storeNumber);
    }

    @FXML
    void rebootSC() throws IOException {
        String msg = " User initiated a reboot at: Radiant-" + storeNumber;
        HDU_Logger.appendLog(msg);
        ButtonFunctions.rebootSiteController(storeNumber);
    }

    @FXML
    void checkRBTNRebootPOS() throws IOException {
        rebootPOS1.setUserData("1");
        rebootPOS2.setUserData("2");
        rebootPOS3.setUserData("3");

        if(rebootPOS.getSelectedToggle() != null) {
            String rval = (rebootPOS.getSelectedToggle().getUserData().toString());
            String msg = " User initiated a reboot on node " + rval + " at: ";
            HDU_Logger.appendLog(msg, storeNumber);
            ButtonFunctions.rebootPOS(rval, storeNumber);
        }
    }

    @FXML
    void checkRBTNRebootKiosk() throws IOException {
        rebootK45.setUserData("45");
        rebootK46.setUserData("46");
        rebootK47.setUserData("47");

        if(rebootKiosk.getSelectedToggle() != null) {
            String rval = (rebootKiosk.getSelectedToggle().getUserData().toString());
            String msg = " User initiated a reboot on node " + rval + " at: ";
            HDU_Logger.appendLog(msg, storeNumber);
            ButtonFunctions.rebootKiosk(rval, storeNumber);
        }
    }

    @FXML
    void checkRBTNRebootOther() throws IOException {
        rebootOFC.setUserData("100");
        rebootKPS.setUserData("200");


        if(rebootOther.getSelectedToggle() != null) {
            String rval = (rebootOther.getSelectedToggle().getUserData().toString());
            String msg = " User initiated a reboot on node " + rval + " at: ";
            HDU_Logger.appendLog(msg, storeNumber);
            ButtonFunctions.rebootOther(rval, storeNumber);
        }
    }

    /* Processes tab button group */
    @FXML
    void checkRBTNTermBrowser() throws IOException {
        chrome.setUserData("chrome.exe");
        firefox.setUserData("firefox.exe");
        ie.setUserData("iexplore.exe");


        if(browsers.getSelectedToggle() != null) {
            String rval = (browsers.getSelectedToggle().getUserData().toString());
            String msg = " User terminated the process " + rval + " at: " + storeNumber;
            HDU_Logger.appendLog(msg);
            ButtonFunctions.termSoftware(rval, storeNumber);
        }
    }

    @FXML
    void checkRBTNTermReporting() throws IOException {
        pdiDT.setUserData("PDIDesktop.exe");
        openOffice.setUserData("soffice.exe");


        if(reporting.getSelectedToggle() != null) {
            String rval = (reporting.getSelectedToggle().getUserData().toString());
            String msg = " User terminated the process " + rval + " at: " + storeNumber;
            HDU_Logger.appendLog(msg);
            ButtonFunctions.termSoftware(rval, storeNumber);
        }
    }

    @FXML
    void execCleanUp() throws IOException {
        String msg = " User initiated the clean_up.bat script at: " + storeNumber;
        HDU_Logger.appendLog(msg);
        ButtonFunctions.cleanUp(storeNumber);
    }
}
