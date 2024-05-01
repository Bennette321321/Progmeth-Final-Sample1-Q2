package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//You might need to do something to the following line
public class ControlPane extends VBox {
	
	private Text drawnNumberText;
	private Text drawCountText;
	private Text bingoText;
	private Button drawButton;
	private Button newRoundButton;
	private NumberGrid numberGrid;

	
	public ControlPane(NumberGrid numberGrid) {
		// TODO
		this.numberGrid = numberGrid;
		setAlignment(Pos.CENTER);
		setPrefWidth(300);
		setSpacing(20);
		setBorder(new Border(new BorderStroke(
				Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, BorderWidths.DEFAULT
		)));
		drawnNumberText = new Text();
		getDrawnNumberText().setFont(Font.font(20));
		drawCountText = new Text();
		initializeBingoText();
		initializeDrawButton();
		initializeNewRoundButton();
		getChildren().addAll(getDrawnNumberText(), getDrawButton(),
				getNewRoundButton(), getBingoText(), getDrawCountText());
		BingoUtil.setTextsBeginning(getDrawnNumberText(), getDrawCountText());
	}
	
	private void initializeBingoText() {
		// TODO
		bingoText = new Text("Bingo!!");
		getBingoText().setFont(Font.font(40));
		getBingoText().setVisible(false);
	}

	private void initializeDrawButton() {
		// TODO
		drawButton = new Button("Draw a number");
		getDrawButton().setPrefWidth(100);
		getDrawButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				drawButtonHandler();
			}
		});
	}

	private void initializeNewRoundButton() {
		// TODO
		newRoundButton = new Button("New Round");
		getNewRoundButton().setPrefWidth(100);
		getNewRoundButton().setDisable(true);
		getNewRoundButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				newRoundButtonHandler();
			}
		});
	}
	
	private void drawButtonHandler() {
		// TODO
		int number = BingoUtil.drawNumber();
		getNumberGrid().highlightNumber(number);
		if (BingoUtil.isBingo(getNumberGrid())) {
			getBingoText().setVisible(true);
			getDrawButton().setDisable(true);
			getNewRoundButton().setDisable(false);
		}
		BingoUtil.updateTextsAfterDrawn(number, getDrawnNumberText(), getDrawCountText());
	}
	
	private void newRoundButtonHandler() {
		// TODO
		BingoUtil.assignRandomNumbers(getNumberGrid().getNumberSquares());
		getBingoText().setVisible(false);
		getDrawButton().setDisable(false);
		getNewRoundButton().setDisable(true);
		BingoUtil.setTextsBeginning(getDrawnNumberText(), getDrawCountText());
	}

	public Text getDrawnNumberText() {
		return drawnNumberText;
	}

	public Text getDrawCountText() {
		return drawCountText;
	}

	public Text getBingoText() {
		return bingoText;
	}

	public Button getDrawButton() {
		return drawButton;
	}

	public Button getNewRoundButton() {
		return newRoundButton;
	}

	public NumberGrid getNumberGrid() {
		return numberGrid;
	}
}
