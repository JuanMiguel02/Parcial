package org.demo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import org.demo.Models.Inmueble;
import org.demo.Models.TipoInmueble;
import org.demo.Repositories.InmuebleRepository;


public class DashboardController {

    @FXML private AnchorPane vistaTabla;
    @FXML private AnchorPane vistaFormulario;
    @FXML private AnchorPane vistaEdicion;

    @FXML private TableView<Inmueble> tablaInmuebles;

    @FXML private TableColumn<Inmueble, String> colTipoInmueble;
    @FXML private TableColumn<Inmueble, String> colCiudad;
    @FXML private TableColumn<Inmueble, String> colHabitaciones;
    @FXML private TableColumn<Inmueble, String> colPisos;
    @FXML private TableColumn<Inmueble, String> colPrecio;

    @FXML private TextField txtCiudad;
    @FXML private ComboBox<TipoInmueble> cmbTipo;
    @FXML private TextField txtNumHabitaciones;
    @FXML private TextField txtNumPisos;
    @FXML private TextField txtPrecio;


    @FXML private TextField txtTipoDetalle;
    @FXML private TextField txtCiudadDetalle;
    @FXML private TextField txtNumHabDetalle;
    @FXML private TextField txtNumPisosDetalle;
    @FXML private TextField txtPrecioDetalle;


    private InmuebleRepository repo;

    private ObservableList<Inmueble> listaInmuebles;

    @FXML
    public void initialize() {
        repo = InmuebleRepository.getInstance();

        cmbTipo.getItems().setAll(TipoInmueble.values());

        colTipoInmueble.setCellValueFactory(new PropertyValueFactory<>("tipoInmueble"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("numHabitaciones"));
        colPisos.setCellValueFactory(new PropertyValueFactory<>("numPisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        cargarInmuebles();

       tablaInmuebles.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                mostrarDetallesInmueble(newValue);
            }
        });


    }


    @FXML
    private void mostrarInicio(){
        vistaEdicion.setVisible(false);
        vistaEdicion.setManaged(false);

        vistaFormulario.setVisible(false);
        vistaFormulario.setManaged(false);

        vistaTabla.setVisible(true);
        vistaTabla.setManaged(true);
        vistaTabla.toFront();

    }

    @FXML
    private void cancelarEdicion(){
        vistaEdicion.setVisible(false);
        vistaEdicion.setManaged(false);

    }

    @FXML
    private void mostrarFormulario(){

        vistaFormulario.setVisible(true);
        vistaFormulario.setManaged(true);

        vistaTabla.setVisible(false);
        vistaTabla.setManaged(false);
    }

    @FXML
    private void guardarInmueble(){
        if(validarCampos(txtCiudad, txtNumHabitaciones, txtNumPisos, txtPrecio, cmbTipo)){
            return;
        }
        try {

            String ciudad = txtCiudad.getText();
            TipoInmueble tipoInmueble = cmbTipo.getValue();
            String numHabitaciones = txtNumHabitaciones.getText();
            String numPisos =txtNumPisos.getText();
            int precio = Integer.parseInt(txtPrecio.getText());


            Inmueble nuevoInmueble = new Inmueble(ciudad, numHabitaciones, numPisos, precio, tipoInmueble);

            repo.agregarInmueble(nuevoInmueble);
            listaInmuebles.add(nuevoInmueble);

            limpiarCampos();

            mostrarAlerta("Éxito", "Inmueble añadido", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarInmueble(){
        Inmueble inmuebleSeleccionado = tablaInmuebles.getSelectionModel().getSelectedItem();

        if(inmuebleSeleccionado == null){
            mostrarAlerta("Advertencia", "Por favor seleccione un inmueble para eliminar", Alert.AlertType.ERROR);
            return;
        }

        Alert confirmacion =  new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confrimar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar este inmueble?");
        confirmacion.setContentText("Inmueble: " + inmuebleSeleccionado);

        confirmacion.showAndWait().ifPresent(respone ->{
            if(respone == ButtonType.OK){
                repo.eliminarInmueble(inmuebleSeleccionado);
                cargarInmuebles();
                mostrarAlerta("Éxito", "Inmueble Eliminado", Alert.AlertType.INFORMATION);
            }
        });
    }


    private void mostrarDetallesInmueble(Inmueble inmueble){
        if(inmueble != null){
            txtNumHabDetalle.setText(inmueble.getNumHabitaciones());
            txtNumPisosDetalle.setText(inmueble.getNumPisos());
            txtCiudadDetalle.setText(inmueble.getCiudad());
            txtPrecioDetalle.setText(String.valueOf(inmueble.getPrecio()));
            txtTipoDetalle.setText(String.valueOf(inmueble.getTipoInmueble()));
        }

    }

    private void cargarInmuebles(){
        listaInmuebles = FXCollections.observableArrayList(repo.getInmuebles());
       tablaInmuebles.setItems(listaInmuebles);
    }

    @FXML
    private void limpiarCampos(){
        txtCiudad.clear();
        txtNumHabitaciones.clear();
        txtNumPisos.clear();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos(TextField txtCiudad, TextField txtNumHabitaciones, TextField txtNumPisos,TextField txtPrecio, ComboBox<TipoInmueble> cmbTipo) {
        if (txtCiudad.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La ciudad es obligatoria", Alert.AlertType.WARNING);
            return true;
        }
        if (txtNumHabitaciones.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Las habitaciones son obligatorias", Alert.AlertType.WARNING);
            return true;
        }
        if (txtNumPisos.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Los pisos son obligatorios", Alert.AlertType.WARNING);
            return true;
        }
        if (txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El precio es obligatorio", Alert.AlertType.WARNING);
            return true;
        }
        if(cmbTipo.getValue() == null){
            mostrarAlerta("Error de validación", "Debe seleccionar un tipo de inmueble", Alert.AlertType.WARNING);
            return true;
        }

        return false;
    }
}
