package application;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;

public class Bank {

	Player player;
	Move move;
	KeyInput keyInput;
	Gravity gravity;
	Collision collision;
	Game game;
	Thread thread;
	Stage stage;

	private List<Platform2D> platforms = new ArrayList<Platform2D>();

	public Bank(Stage stage) {
		this.stage=stage;
		player = new Player(this);
		move = new Move(this);
		keyInput = new KeyInput(this);
		gravity = new Gravity(this);
		collision = new Collision(this);
		game = new Game(this);
		thread = new Thread(game);
		thread.start();
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyInput keyInput) {
		this.keyInput = keyInput;
	}

	public Gravity getGravity() {
		return gravity;
	}

	public void setGravity(Gravity gravity) {
		this.gravity = gravity;
	}

	public Collision getCollision() {
		return collision;
	}

	public void setCollision(Collision collision) {
		this.collision = collision;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public List<Platform2D> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform2D> platforms) {
		this.platforms = platforms;
	}
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}