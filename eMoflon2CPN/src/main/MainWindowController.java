package main;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import simulation.DisconnectedException;
import simulation.Server;
import translation.NothingChosenException;
import translation.Translation;

public class MainWindowController {
	@FXML private Label classLabel;
	@FXML private Label methodLabel;
	@FXML private Label portLabel;
	@FXML private Button startButton;
	@FXML private Button chooseDirectoryButton;
	@FXML private Button chooseInstanceButton;
	@FXML private Button translateButton;
	@FXML private ChoiceBox<String> classChoiceBox;
	@FXML private ChoiceBox<String> methodChoiceBox;
	@FXML private TextArea console;
	@FXML private TextField portField;
	
	private Translation translation;
	private DirectoryChooser directoryChooser = new DirectoryChooser();
	private FileChooser instanceChooser = new FileChooser();
	
	public void initialize() {
		portField.setText("9000");
		
		classChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				try {
					String chosenClass = classChoiceBox.getItems().get((Integer) newValue);
					translation.chooseClass(chosenClass);
					methodChoiceBox.setItems(FXCollections.observableArrayList(translation.getMethods()));
				}
				catch(RuntimeException e) {
					exceptionOccured();
				}
			}
		});
		
		methodChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				try {
					String chosenMethod = methodChoiceBox.getItems().get((Integer) newValue);
					translation.chooseMethod(chosenMethod);
				}
				catch(RuntimeException e) {
					exceptionOccured();
				}
			}
		});
	}
	
	@FXML
	private void chooseDirectory() {
		File directory = directoryChooser.showDialog(Main.stage);
		if(directory == null) {
			write("Invalid directory. Try again.");
		}
		else {
			try {
				translation.load(directory);
				classChoiceBox.setItems(FXCollections.observableArrayList(translation.getClasses()));
			}
			catch(RuntimeException e) {
				write("Failed to read the eMoflon project.");
			}
		}
	}
	
	@FXML
	private void translate() {
		try {
			translation.translate(Integer.parseInt(portField.getText()));
		}
		catch(NothingChosenException e) {
			write(e.getMessage());
		}
		catch(RuntimeException e) {
			write("Failed to translate eMoflon code into CPN code");
		}
	}
	
	@FXML
	private void chooseInstance() {
		instanceChooser.setInitialDirectory(translation.getDirectoryHandler().getXmi());
		instanceChooser.setTitle("Open Instance File");
		instanceChooser.getExtensionFilters().addAll(new ExtensionFilter("Xmi Files", "*.xmi"));
		File instance = instanceChooser.showOpenDialog(Main.stage);
		translation.chooseInstance(instance);
	}
	
	@FXML
	private void start() {
		Integer port = translation.getPort();
		if(port == null) {
			write("eMoflon project have not been translated yet.");
		}
		else {
			Server server = new Server(port, this::write);
			try {
				server.communicate();
			}
			catch(DisconnectedException e) {
				write(e.getMessage());
			}
			catch(RuntimeException e) {
				exceptionOccured();
			}
		}
	}
	
	private void write(String msg) {
		console.appendText(msg + "\n");
	}
	
	private void exceptionOccured() {
		console.appendText("Someting went wrong\n");
	}
}
