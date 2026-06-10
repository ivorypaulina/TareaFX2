package com.example.tareafx2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    public void initialize() {
        cbRol.getItems().addAll("Administrador", "Cajero");
    }

    @FXML
    private void ingresar() {

        try {

            String usuario = txtUsuario.getText();
            String password = txtPassword.getText();
            String rol = cbRol.getValue();

            if (rol == null) {
                mostrarError("Seleccione un rol.");
                return;
            }

            FXMLLoader loader = null;

            // ADMINISTRADOR
            if (rol.equals("Administrador")
                    && usuario.equals("admin")
                    && password.equals("1234")) {

                loader = new FXMLLoader(
                        MainApp.class.getResource("Administrador.fxml"));

            }
            // CAJERO
            else if (rol.equals("Cajero")
                    && usuario.equals("cajero")
                    && password.equals("1234")) {

                loader = new FXMLLoader(
                        MainApp.class.getResource("Cajero.fxml"));

            } else {

                mostrarError("Usuario o contraseña incorrectos.");
                return;
            }

            Stage actual =
                    (Stage) txtUsuario.getScene().getWindow();

            Stage nuevo = new Stage();
            nuevo.setScene(new Scene(loader.load()));
            nuevo.show();

            actual.close();

        } catch (Exception e) {
            e.printStackTrace();

            mostrarError("No se pudo abrir la ventana.");
        }
    }

    private void mostrarError(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    @FXML
    private void salir() {
        System.exit(0);
    }
}