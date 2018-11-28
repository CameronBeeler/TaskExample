package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {

    @FXML private ListView    listy; // the first value is the type of edit window, the 2nd is the name of the data window in the fxml file...both must be accurate...
    @FXML private ProgressBar progressBar;
    @FXML private Label       progressLabel;
    @FXML private Service<ObservableList<String>> service;
    @FXML public void initialize()
    {

        service = new EmployeeService();
        progressLabel.textProperty().bind(service.messageProperty());
        progressBar.progressProperty().bind(service.progressProperty());
       listy.itemsProperty().bind(service.valueProperty());
    }

    @FXML public void PushButton()
    {
        service.start();
    }
}
