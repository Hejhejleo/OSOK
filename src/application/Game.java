package application;

public class Game implements Runnable {
	
	private Move move;
	private Gravity gravity;
	private Collision collision;
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	boolean running = true;

	public Game(Move move,Gravity gravity, Collision collision) {
		this.move=move;
		this.gravity=gravity;
		this.collision=collision;
	}

	public void run() {
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	

	private void render() {

	}

	private void tick() {
		collision.checkCollision();
		gravity.fall();
		move.movePlayer();
	}
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
