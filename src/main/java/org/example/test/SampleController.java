package org.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {
    private final static String ROCK = "rock";
    private final static String PAPER = "paper";
    private final static String SCISSORS = "scissors";
    private Image image;
    static int counter=0;

    @FXML
    private ImageView aiChoice;

    @FXML
    protected Label aiScore;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button paper;

    @FXML
    private Label result;

    @FXML
    private Button rock;

    @FXML
    private Button scissors;

    @FXML
    protected Label youScore;

    @FXML
    private ImageView yourChoice;



    @FXML
    private void youTurn(ActionEvent event) throws IOException {
        String youChoice = null;
        switch (((Button) event.getSource()).getId()) {
            case "rock":
                image = new Image("/the rock.png");
                youChoice = ROCK;
                break;
            case "paper":
                image = new Image("/paper.png");
                youChoice = PAPER;
                break;
            case "scissors":
                image = new Image("/scissors.png");
                youChoice = SCISSORS;
                break;
        }
        yourChoice.setImage(image);
        winner(youChoice, aiTurn());
        counter = (Integer.parseInt(youScore.getText())) + (Integer.parseInt(aiScore.getText()));
        int yourScore = Integer.parseInt(youScore.getText());
        int aiScorer = Integer.parseInt(aiScore.getText());
        if (counter==3){
            Parent root;
            if (yourScore > aiScorer ){
                root = FXMLLoader.load(Main.class.getResource("youWin.fxml"));
            }else{
                root = FXMLLoader.load(Main.class.getResource("aiWin.fxml"));
            }
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Final result");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



    @FXML
    private String aiTurn(){
        String aiiChoice = null;
        int index = (int) (Math.random()*3);
        switch (index){
            case 0:
                image = new Image("/the rock.png");
                aiiChoice = ROCK;
                break;
            case 1:
                image = new Image("/paper.png");
                aiiChoice = PAPER;
                break;
            case 2:
                image = new Image("/scissors.png");
                aiiChoice = SCISSORS;
                break;
        }
        aiChoice.setImage(image);
        return aiiChoice;
    }



    public void youWin(){
        result.setText("You won");
        youScore.setText(String.valueOf(Integer.parseInt(youScore.getText())+1));
    }



    public void aiWin(){
        result.setText("AI won");
        aiScore.setText(String.valueOf(Integer.parseInt(aiScore.getText())+1));
    }



    public void draw(){
        result.setText("Tie");
    }



    private void winner(String youChoice, String aiChoice){
        if(youChoice.equals(aiChoice)){
            draw();
            return;
        }
        if (youChoice.equals(ROCK)) {
            if (aiChoice.equals(PAPER)){
                aiWin();
            }else {
                youWin();
            }
        } else if (youChoice.equals(PAPER)) {
            if (aiChoice.equals(SCISSORS)){
                aiWin();
            }else {
                youWin();
            }
        } else if (youChoice.equals(SCISSORS)) {
            if (aiChoice.equals(ROCK)){
                aiWin();
            }else {
                youWin();
            }
        }
    }



    }
