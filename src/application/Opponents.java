package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Opponents {

	Bank bank;
	private ArrayList<Player> opponentsList = new ArrayList<Player>();
	private ArrayList<Bullet> bulletsList = new ArrayList<Bullet>();
	private ArrayList<Circle> opponentsCircleList = new ArrayList<Circle>();
	private ArrayList<Circle> opponentsBulletList = new ArrayList<Circle>();
	ImagePattern imageLookLeft;
	ImagePattern imageLookRight;

	public Opponents(Bank bank) {
		imageLookLeft = new ImagePattern(new Image("Sprite-00010.png"));
		imageLookRight = new ImagePattern(new Image("Sprite-0009.png"));
		
		for (int i = 0; i < 4; i++) {
			opponentsCircleList.add(new Circle(-30,-30,20));
		}
		for (int i = 0; i < 4; i++) {
			opponentsBulletList.add(new Circle(-30,-30,3));
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
			bank.getOpponents().getOpponentsBulletList().get(i).setCenterX(bank.getOpponents().getBulletsList().get(i).getCurrentCordsX());
			bank.getOpponents().getOpponentsBulletList().get(i).setCenterY(bank.getOpponents().getBulletsList().get(i).getCurrentCordsY());
			
				}
			}
//			for(int i = bank.getOpponents().getOpponentsList().size();i<4;i++){
//				bank.getOpponents().getOpponentsCircleList().get(i).setCenterX(-30);
//				bank.getOpponents().getOpponentsCircleList().get(i).setCenterY(-30);
//						}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
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

	public ArrayList<Circle> getOpponentsBulletList() {
		return opponentsBulletList;
	}

	public void setOpponentsBulletList(ArrayList<Circle> opponentsBulletList) {
		this.opponentsBulletList = opponentsBulletList;
	}

	public ArrayList<Bullet> getBulletsList() {
		return bulletsList;
	}

	public void setBulletList(ArrayList<Bullet> bulletList) {
		this.bulletsList = bulletList;
	}

}
