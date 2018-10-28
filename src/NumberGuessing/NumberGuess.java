package NumberGuessing;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Having a class but extending to Application (pre-made interface) gives the
 * functionality.
 * 
 * @author patelsmee
 */
public class NumberGuess extends Application {

	private int randomNumber = (int) (Math.random() * 100 + 1); // Generates random number to guess
	private ArrayList<Integer> yourNumbersHigh = new ArrayList<Integer>();// Stores the current games guess high input
	private ArrayList<Integer> yourNumbersLow = new ArrayList<Integer>();// Stores the current games guess Low input
	private ArrayList<HighScore> highScore = new ArrayList<HighScore>();// Stores the current games guess Low input
	public int totalScore = 100;// Starting score starts at 100 and reduces per guess
	public int hintNum = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("The Number Guess Game from Smeet");
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(25, 25, 25, 25));// adding padding to each side, can be changed
		pane.setHgap(10);
		pane.setVgap(10);// the gap between rows
		pane.setAlignment(Pos.CENTER);// aligning the pane to the center
		primaryStage.setScene(new Scene(pane, 360, 740));
		pane.setStyle("-fx-background-image: url('background.jpg'); ");
		// primaryStage.setFullScreen(true);
		primaryStage.show();// show the window
		// Text title = new Text();
		/** Game Title */
		Text title = new Text();
		title.setText("The Number Guessing Game");
		title.setFill(Color.WHITE);
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		pane.add(title, 0, 0, 2, 1);
		final Text player = new Text();
		/** player name */
		player.setText("Player Name");
		player.setFill(Color.WHITE);
		player.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		pane.add(player, 0, 1);
		TextField playerName = new TextField();
		playerName.setPromptText("Enter Name to record Score");
		pane.add(playerName, 1, 1, 1, 1);
		/** Player score and hints used */
		Text score = new Text();
		score.setText("Score: " + totalScore);
		score.setFill(Color.GREEN);
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		pane.add(score, 0, 2);
		Text hint = new Text();
		hint.setText("Hints used " + hintNum);
		hint.setFill(Color.ORANGE);
		hint.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		GridPane.setHalignment(hint, HPos.RIGHT);
		pane.add(hint, 1, 2, 1, 1);
		/** Guessing Field */
		TextField guessNum = new TextField();
		guessNum.setPromptText("Enter Number from 1 to 100");
		guessNum.setPrefWidth(100);
		pane.add(guessNum, 0, 3, 2, 1);
		/** Feed back row */
		final Text feedBack = new Text();
		feedBack.setText("Welcome to a New Game, guess the number");
		feedBack.setFill(Color.RED);
		feedBack.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
		GridPane.setHalignment(feedBack, HPos.CENTER);
		pane.add(feedBack, 0, 4, 2, 1);
		/** Button or press enter */
		Button btn = new Button("Guess Number OR Press Enter after Guess");
		btn.setTextFill(Color.WHITE);
		btn.setStyle("-fx-background-color : #29B6F6;\r\n; -fx-font-weight: 900;");
		GridPane.setHalignment(btn, HPos.CENTER);
		pane.add(btn, 0, 5, 2, 1);
		/** player guessed numbers */
		Text yourNum = new Text();
		yourNum.setText("Your High Guesses " + yourNumbersHigh.toString());
		yourNum.setFill(Color.ORANGE);
		yourNum.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
		GridPane.setHalignment(yourNum, HPos.CENTER);
		pane.add(yourNum, 0, 6, 2, 1);
		Text yourNumLow = new Text();
		yourNumLow.setText("Your Low Guesses " + yourNumbersLow.toString());
		yourNumLow.setFill(Color.TEAL);
		yourNumLow.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
		GridPane.setHalignment(yourNumLow, HPos.CENTER);
		pane.add(yourNumLow, 0, 7, 2, 1);
		/** player guessed numbers */
		Button btnNew = new Button("New Game");
		GridPane.setHalignment(btnNew, HPos.CENTER);
		btnNew.setPrefSize(80, 30);
		btnNew.setTextFill(Color.WHITE);
		btnNew.setStyle("-fx-background-color : #29B6F6;\r\n; -fx-font-weight: 900;");
		pane.add(btnNew, 0, 8);
		Button btnHint = new Button("Use hint");
		btnHint.setPrefSize(80, 30);
		btnHint.setTextFill(Color.WHITE);
		btnHint.setStyle("-fx-background-color : #29B6F6;\r\n; -fx-font-weight: 900;");
		GridPane.setHalignment(btnHint, HPos.CENTER);
		pane.add(btnHint, 1, 8, 2, 1);
		/** Score list Title */
		Text listScore = new Text();
		listScore.setText("Guess game score board");
		listScore.setFill(Color.BLUE);
		listScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
		GridPane.setHalignment(listScore, HPos.CENTER);
		pane.add(listScore, 0, 9, 2, 1);
		/** Score list */
		HighScore sam = new HighScore("Sam Smith", 20);
		HighScore ann = new HighScore("Ann Ng", -40);
		HighScore rose = new HighScore("Rose Mary", 80);
		addHs(sam);
		addHs(ann);
		addHs(rose);
		/** Score list print */
		Text t = new Text(10, 50, "This is a test");
		t.setWrappingWidth(240);
		t.setText(highScore.toString());
		t.setFill(Color.BLUE);
		t.setFont(Font.font("verdana", FontPosture.REGULAR, 13));
		GridPane.setHalignment(t, HPos.CENTER);
		pane.add(t, 0, 10, 2, 1);

		// https://stackoverflow.com/questions/23040531/how-to-disable-button-when-textfield-is-empty
		// making the button disabled when empty
		btn.disableProperty().bind(Bindings.isEmpty(guessNum.textProperty()));
		/**
		 * https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		 */
		guessNum.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					guessNum.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Arg0) {

				totalScore = totalScore - 10;
				score.setText("Score: " + totalScore);
				if (Integer.valueOf(guessNum.getText()) == randomNumber) {
					feedBack.setFill(Color.LIME);
					feedBack.setText("Correct guess! congratulations!!");
				} else if (Integer.valueOf(guessNum.getText()) < randomNumber) {
					feedBack.setText("Guess number Too low");
					yourNumbersLow.add(Integer.valueOf(guessNum.getText()));
					yourNumLow.setText("Your Low Guesses " + yourNumbersLow.toString());
				} else if (Integer.valueOf(guessNum.getText()) > randomNumber) {
					feedBack.setText("Guess number Too high");
					yourNumbersHigh.add(Integer.valueOf(guessNum.getText()));
					yourNum.setText("Your High Guesses " + yourNumbersHigh.toString());
				}
				if (Integer.valueOf(guessNum.getText()) > 100 || Integer.valueOf(guessNum.getText()) < 1) {
					feedBack.setText("Enter Number from 1 to 100 above");
				}
				guessNum.clear();
			}
		});
		pane.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (guessNum.getText() == null || guessNum.getText().trim().isEmpty()) {
					feedBack.setText("Enter Number from 1 to 100 above");
					return;
				} else if (e.getCode() == KeyCode.ENTER) {
					totalScore = totalScore - 10;
					// yourNum.setText("Your Numbers " + yourNumbersHigh.toString());
					score.setText("Score: " + totalScore);
					if (Integer.valueOf(guessNum.getText()) == randomNumber) {
						feedBack.setFill(Color.LIME);
						feedBack.setText("Correct guess! congratulations!!");
					} else if (Integer.valueOf(guessNum.getText()) < randomNumber) {
						feedBack.setText("Guess number Too low");
						yourNumbersLow.add(Integer.valueOf(guessNum.getText()));
						yourNumLow.setText("Your Low Guesses " + yourNumbersLow.toString());
					} else if (Integer.valueOf(guessNum.getText()) > randomNumber) {
						feedBack.setText("Guess number Too high");
						yourNumbersHigh.add(Integer.valueOf(guessNum.getText()));
						yourNum.setText("Your High Guesses " + yourNumbersHigh.toString());
					}
					if (Integer.valueOf(guessNum.getText()) > 100 || Integer.valueOf(guessNum.getText()) < 1) {
						feedBack.setText("Enter Number from 1 to 100 above");
					}
					guessNum.clear();
				}
			}
		});
		// final int hintNum=0;
		btnHint.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Arg0) {
				int randLow = (int) (randomNumber - Math.random() * 6);
				int randHi = (int) (Math.random() * 6 + randomNumber);
				totalScore = totalScore - 20;
				score.setText("Score: " + totalScore);
				feedBack.setText("Hint: bewteen " + randLow + " and " + randHi);
				hintNum = hintNum + 1;
				hint.setText("Hints used " + hintNum);
				playerName.clear();
			}
		});

		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Arg0) {
				HighScore high = new HighScore(playerName.getText(), totalScore);
				addHs(high);
				for (HighScore i : highScore) {
					System.out.println(i.toString());
				}
				// highScore.add(totalScore, playerName.getText()));
				yourNumbersLow.clear();
				yourNumbersHigh.clear();
				yourNum.setText("Your Numbers " + yourNumbersHigh.toString());
				yourNumLow.setText("Your Low Guesses " + yourNumbersLow.toString()); 
				totalScore = 100;
				score.setText("Score: " + totalScore);
				randomNumber = (int) (Math.random() * 100 + 1);
				t.setText(highScore.toString());
				hintNum = 0;
				guessNum.clear();
			}
		});
	}

	public void print() {
		for (HighScore i : highScore) {
			i.toString();
		}
	}

	public void addHs(HighScore h) {
		highScore.add(h);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
