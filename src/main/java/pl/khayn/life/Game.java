package pl.khayn.life;

import java.util.Random;

public class Game {
	private final int maxWidth;
	private final int maxHeight;
	private byte[][] board;
	private int step;

	public Game(int width, int height) {
		this.maxWidth = width;
		this.maxHeight = height;

		this.step = 0;

		this.board = createBoard(width, height);
		printWelcomeMsg();
	}

	public void reset() {
		this.step = 0;
		this.board = createBoard(maxWidth, maxHeight);

		System.out.println("Board has been reset and randomized.");
		printWelcomeMsg();
	}

	public void run(int count) {
		for (int i = 0; i < count; i++) {
			System.out.println("Running step " + ++step + "/" + count+ ":");
			step();
		}
	}

	public void step() {
		drawBoard();

		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxWidth; j++) {
				Tile tile = new Tile(i, j);

				int neighbours = getNumberOfNeighbours(tile);
				processTile(tile, neighbours);
			}
		}

		System.out.println("Living tiles: " + countLivingTiles());
		System.out.println();
	}

	public int countLivingTiles() {
		int living = 0;

		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxHeight; j++) {
				if (board[i][j] == 1) {
					living++;
				}
			}
		}

		return living;
	}

	private void printWelcomeMsg() {
		System.out.println("Welcome to The Game of life! Living tiles: " + countLivingTiles());
	}

	public int getNumberOfNeighbours(Tile tile) {
		int neighbours = 0;

		if (tile.getX() > 0) {
			//left
			neighbours += checkCoords(new Tile(tile.getX() - 1, tile.getY()));
		}

		if (tile.getX() < maxWidth - 1) {
			//right
			neighbours += checkCoords(new Tile(tile.getX() + 1, tile.getY()));
		}

		if (tile.getY() > 0) {
			//down
			neighbours += checkCoords(new Tile(tile.getX(), tile.getY() - 1));
		}

		if (tile.getY() < maxHeight - 1) {
			//up
			neighbours += checkCoords(new Tile(tile.getX(), tile.getY() + 1));
		}

		if (tile.getX() > 0 && tile.getY() > 0) {
			//lower left
			neighbours += checkCoords(new Tile(tile.getX() - 1, tile.getY() - 1));
		}

		if (tile.getX() < maxWidth - 1 && tile.getY() > 0) {
			//lower right
			neighbours += checkCoords(new Tile(tile.getX() + 1, tile.getY() - 1));
		}

		if (tile.getX() < maxWidth - 1 && tile.getY() > 0) {
			//upper left
			neighbours += checkCoords(new Tile(tile.getX() + 1, tile.getY() - 1));
		}

		if (tile.getX() < maxWidth - 1 && tile.getY() < maxHeight - 1) {
			//upper right
			neighbours += checkCoords(new Tile(tile.getX() + 1, tile.getY() + 1));
		}

		return neighbours;
	}

	public int checkCoords(Tile tile) {
		return board[tile.getX()][tile.getY()] > 0 ? 1 : 0;
	}

	private void processTile(final Tile tile, int neighbours) {
		byte current = board[tile.getX()][tile.getY()];

		if (current == 1 && (neighbours < 2 || neighbours > 3)) {
			System.out.printf("%s with %d neighbours dies!\n", tile, neighbours);
			board[tile.getX()][tile.getY()] = 0;
		}

		if (current == 0 && neighbours == 3) {
			System.out.printf("%s with %d neighbours blossoms to life!\n", tile,
					neighbours);
			board[tile.getX()][tile.getY()] = 1;
		}


	}

	public byte[][] createBoard(int width, int height) {
		byte[][] board = new byte[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				Random random = new Random();
				board[i][j] = (byte) random.nextInt(2);
			}
		}

		return board;
	}

	public void drawBoardReverse() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxHeight; j++) {
				sb.append(board[i][j]).append(" ");
			}

			sb.append(System.lineSeparator());
		}

		System.out.println(sb);
	}

	public void drawBoard() {
		StringBuilder sb = new StringBuilder();

		for (int i = maxWidth-1; i >=0; i--) {
			for (int j = maxHeight-1; j >=0; j--) {
				sb.append(board[i][j]).append(" ");
			}

			sb.append(System.lineSeparator());
		}

		System.out.println(sb);
	}

}
