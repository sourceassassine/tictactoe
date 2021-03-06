package main;

import javax.swing.ImageIcon;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	// Fields
	private Game game;
	private Stage stage;
	private Scene sceneGame;
	private Scene sceneOptions;

	// Properties
	private Button[][] buttons = new Button[3][3];
	private Label label;
	
	private TextField Player1Name = new TextField("Spieler 1");
	private TextField Player2Name = new TextField("Spieler 2");
	private ChoiceBox Player1KI = new ChoiceBox();
	private ChoiceBox Player2KI = new ChoiceBox();

	// Lifecycle Events
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.setTitle("Tic-Tac-Toe");
		stage.getIcons().add(new Image(("/Resources/TicTacToe_Icon.png")));

		VBox vBox = new VBox(10);
		MenuBar menuBar = setMenu();
		vBox.getChildren().add(menuBar);
		GridPane grid = setGridpane();
		vBox.getChildren().add(grid);
		label = new Label("");
		vBox.getChildren().add(label);
		sceneGame = new Scene(vBox, 300, 275);

		VBox vBoxOptions = new VBox(10);
		MenuBar menuBarOptions = setMenu();
		vBoxOptions.getChildren().add(menuBarOptions);
		GridPane gridOptions = setGridOptions();
		vBoxOptions.getChildren().add(gridOptions);
		sceneOptions = new Scene(vBoxOptions, 300, 275);

		stage.setScene(sceneGame);
		stage.show();
		game = new Game(this);
	}

	// Methods
	public void reset() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				buttons[x][y].setText("");
			}
		}
	}

	public void setButtonText(String text, int x, int y) {
		buttons[x][y].setText(text);
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();

		// --- Menu File
		Menu menuGame = new Menu("Game");
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				stage.setScene(sceneGame);
				stage.show();
				game.init();
			}
		});
		newGame.setAccelerator(new KeyCodeCombination(KeyCode.F2));
		
		MenuItem options = new MenuItem("Options");
		options.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				stage.setScene(sceneOptions);
				stage.show();
				game.init();
			}
		});
		options.setAccelerator(new KeyCodeCombination(KeyCode.F3));
		
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});
		exit.setAccelerator(new KeyCodeCombination(KeyCode.F4));
		
		menuGame.getItems().addAll(newGame, options, exit);
		menuBar.getMenus().add(menuGame);

		return menuBar;

	}

	private GridPane setGridpane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(1);
		grid.setVgap(1);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final int x = i;
				final int y = j;
				buttons[x][y] = new Button("");
				buttons[x][y].setPrefSize(60, 60);
				buttons[x][y].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						game.click(x, y);
					}
				});

				grid.add(buttons[x][y], x, y);
			}
		}

		return grid;
	}

	private GridPane setGridOptions() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(1);
		grid.setVgap(1);

		grid.add(Player1Name, 0, 0);
		
		grid.add(Player2Name, 1, 0);
		
		Player1KI = new ChoiceBox();
		Player1KI.getItems().addAll("Player", "Easy", "Hard");
		Player1KI.setValue("Player");
		grid.add(Player1KI, 0, 1);
		
		Player2KI = new ChoiceBox();
		Player2KI.getItems().addAll("Player", "Easy", "Hard");
		Player2KI.setValue("Player");
		grid.add(Player2KI, 1, 1);

		return grid;
	}

	public String GetPlayerName(int id) {
		switch (id) {
		case 1:
			return Player1Name.getText();
		case 2:
			return Player2Name.getText();
		default:
			return null;
		}
	}
	public String GetPlayerKI(int id) {
		switch (id) {
		case 1:
			return Player1KI.getValue().toString();
		case 2:
			return Player2KI.getValue().toString();
		default:
			return null;
		}
	}

}
