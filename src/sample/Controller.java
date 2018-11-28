package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
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
//       service.setonrunning(new eventhandler<workerstateevent>()
//       {
//           @override
//           public
//           void handle(workerstateevent workerstateevent)
//           {
//               progressbar.setvisible(true);
//               progresslabel.setvisible(true);
//           }
//       });
//       service.setonsucceeded(new eventhandler<workerstateevent>()
//       {
//           @override
//           public
//           void handle(workerstateevent workerstateevent)
//           {
//               progressbar.setvisible(false);
//               progresslabel.setvisible(false);
//           }
//       });
//       progressbar.setvisible(false);
//       progressLabel.setVisible(false);

        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
    }

    @FXML public void PushButton()
    {
        if(service.getState()==Service.State.SUCCEEDED)
        {
            service.reset();
            service.start();
        } else if (service.getState() == Service.State.READY)
        {
            service.start();
        }
        else
        {
            System.out.println("The service is in state: " + service.getState() + " and cannot be restarted");
        }
    }
}
