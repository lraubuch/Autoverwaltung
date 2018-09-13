package de.htwsaar.prog3.carrental.view;

import de.htwsaar.prog3.carrental.controller.EmployeeEditViewController;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Entry Point of the "Edit Employee" Dialog.
 *
 * @author Jens Thewes
 */
public class EmployeeEditView {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeEditView.class);

    /**
     * Start the Edit Employee Dialog in a modal Window.
     *
     * @param parentStage
     * @param employee
     * @return
     */
    public boolean start(Stage parentStage, Employee employee) {
        // Load FXML document for the employee configuration view wit the needed resource bundle
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource(I18nStringsUtil.getEmployeeEditViewFxml()),
                            I18nUtil.getResourceBundleComponents());
            Parent page = fxmlLoader.load();

            // create the modal Stage
            Stage modalStage = new Stage();
            modalStage.setTitle(I18nComponentsUtil.getStageTitle());
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            modalStage.setScene(scene);
            modalStage.setMaxHeight(200);
            modalStage.setMaxWidth(600);
            modalStage.setResizable(false);

            // set the employee into the controller
            EmployeeEditViewController controller = fxmlLoader.getController();
            controller.setModalStage(modalStage);
            controller.setEmployee(employee);

            // show the dialog and wait until the user closes it
            modalStage.showAndWait();

            return controller.isApplyClicked();
        } catch (IOException e) {
            logger.error("Failed loading FXML!", e);
            return false;
        }
    }
}      
