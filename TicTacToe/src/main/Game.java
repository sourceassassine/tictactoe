package main;

public class Game {

	private Start view;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private String[][] field;
	private boolean running = false;

	Game(Start _view) {
		this.view = _view;
		init();
	}
	
	public void init(){
		this.view.reset();
		this.field = new String[3][3];
		this.player1 = new Player("Player1", "X", "Player");
		this.player2 = new Player("Player2", "Y", "Player");
		this.currentPlayer = player1;
		switchPlayer();
		this.running = true;
	}
	public void click(int x, int y) {
		if (this.running && !currentPlayer.isCPU() && isFieldFree(x, y)) {
			setField(currentPlayer.getSymbol(), x, y);
			if(isPlayerWon()){
				this.running = false;
				view.setLabelText(currentPlayer.getName() + " hat gewonnen.");
			}
			else{
				switchPlayer();
			}
		}
	}

	
	private boolean isFieldFree(int x, int y) {
		return field[x][y] == null;
	}
	private void setField(String symbol, int x, int y) {
		field[x][y] = symbol;
		view.setButtonText(symbol, x, y);
	}
	private boolean isPlayerWon(){
		String symbol = currentPlayer.getSymbol();
		return (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol) ||
				(field[1][0] == symbol && field[1][ 1] == symbol && field[1][ 2] == symbol) ||
				(field[2][ 0] == symbol && field[2][ 1] == symbol && field[2][ 2] == symbol) ||
				(field[0][0] == symbol && field[1][ 0] == symbol && field[2][ 0] == symbol) ||
				(field[0][ 1] == symbol && field[1][ 1] == symbol && field[2][ 1] == symbol) ||
				(field[0][ 2] == symbol && field[1][ 2] == symbol && field[2][ 2] == symbol) ||
				(field[0][ 0] == symbol && field[1][1] == symbol && field[2][ 2] == symbol) ||
				(field[2][ 0] == symbol && field[1][ 1] == symbol && field[0][ 2] == symbol);
	}
	
	private void switchPlayer() {
		currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
		view.setLabelText(currentPlayer.getName() + " ist dran.");
		
		if(currentPlayer.isCPU()){
			int[] choice = currentPlayer.getChoice();
			setField(currentPlayer.getSymbol(), choice[0], choice[1]);
			switchPlayer();
		}
	}
}
