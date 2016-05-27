package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Opponents {

	Bank bank;
	private ArrayList<Player> opponentsList = new ArrayList<Player>();
	private ArrayList<Circle> opponentsCircleList = new ArrayList<Circle>();
	ImagePattern imageLookLeft;
	ImagePattern imageLookRight;

	public Opponents(Bank bank) {
		imageLookLeft = new ImagePattern(new Image("application/Sprite-00010.png"));
		imageLookRight = new ImagePattern(new Image("application/Sprite-0009.png"));
		
		for (int i = 0; i < 4; i++) {
			opponentsCircleList.add(new Circle(-30,-30,20));
		}
		this.bank = bank;
	}

	public void updateOpponents() {
		try{
			if(!(bank.getOpponents().getOpponentsList().size()==0)){
		for (int i = 0; i < bank.getOpponents().getOpponentsList().size(); i++) {
			if(bank.getOpponents().getOpponentsList().get(i).isLeft()){
				bank.getOpponents().getOpponentsCircleList().get(i).setFill(imageLookLeft);
			}else{
				bank.getOpponents().getOpponentsCircleList().get(i).setFill(imageLookRight);
			}
			bank.getOpponents().getOpponentsCircleList().get(i).setCenterX(bank.getOpponents().getOpponentsList().get(i).getxPos());
			bank.getOpponents().getOpponentsCircleList().get(i).setCenterY(bank.getOpponents().getOpponentsList().get(i).getyPos());
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		

		// for (int i = 0; i < bank.getOpponents().getOpponentsList().size();
		// i++) {
		// bank.getGui().getRoot().getChildren().remove(bank.getOpponents().getOpponentsCircleList().get(i));
		// }
		// for (int i = 0; i < bank.getOpponents().getOpponentsList().size();
		// i++) {
		// bank.getOpponents().getOpponentsCircleList().get(i)
		// .setCenterX(bank.getOpponents().getOpponentsList().get(i).getxPos());
		// bank.getOpponents().getOpponentsCircleList().get(i)
		// .setCenterY(bank.getOpponents().getOpponentsList().get(i).getyPos());
		//
		// bank.getGui().getRoot().getChildren().add(bank.getOpponents().getOpponentsCircleList().get(i));
		//
		// }
		// }
	}

	public ArrayList<Player> getOpponentsList() {
		return opponentsList;
	}

	public void setOpponentsList(ArrayList<Player> opponentsList) {
		this.opponentsList = opponentsList;
	}

	public ArrayList<Circle> getOpponentsCircleList() {
		return opponentsCircleList;
	}

	public void setOpponentsCircleList(ArrayList<Circle> opponentsCircleList) {
		this.opponentsCircleList = opponentsCircleList;
	}

}
