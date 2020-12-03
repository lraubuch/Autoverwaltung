package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * JavaFX controller for the "Edit Employee" view.
 *
 * @author Jens Thewes
 */
@Component
public class EmployeeEditViewController extends GenericEditViewController<Employee> {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField positionTextField;

    @Override
    public void initialize(Employee employee) {
        entity = employee;

        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        positionTextField.setText(entity.getPosition());
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            entity.setFirstName(firstNameTextField.getText());
            entity.setLastName(lastNameTextField.getText());
            entity.setPosition(positionTextField.getText());
            closeModalWithApply();
        }
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isBlank(firstNameTextField.getText())) {
            sb.append(I18nUtils.getEmployeeNoValidFirstName());
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(lastNameTextField.getText())) {
            sb.append(I18nUtils.getEmployeeNoValidLastName());
            sb.append(System.lineSeparator());
        }

        if (StringUtils.isBlank(positionTextField.getText())) {
            sb.append(I18nUtils.getEmployeeNoValidPosition());
            sb.append(System.lineSeparator());
        }

        String errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtils.createErrorDialog(I18nUtils.getDialogErrorInvalidFieldsTitle(),
                    I18nUtils.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
