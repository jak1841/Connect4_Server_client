import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

class MyTest {

	String [][] gameboard;
	Connect4GameBoard connect4GameBoard = new Connect4GameBoard();
	@Test
	void test() {

	}

	@Test
	void game_over_horizontal () {
		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(false, connect4GameBoard.is_gameover());
		assertEquals(null, connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"R", "R", "R", "R", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());

		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "Y", "Y", "Y", "Y"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("Y", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "R", "R", "R", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "Y", "Y", "Y", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(false, connect4GameBoard.is_gameover());
		assertEquals(null, connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "Y", "Y", "Y", "G", "Y"},
						{"R", "R", "R", "R", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());



	}

	@Test
	void game_over_vertical () {
		gameboard = new String[][]
				{		{"R", "G", "G", "G", "G", "G", "G"},
						{"R", "G", "G", "G", "G", "G", "G"},
						{"R", "G", "G", "G", "G", "G", "G"},
						{"R", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"Y", "G", "G", "G", "G", "G", "G"},
						{"Y", "G", "G", "G", "G", "G", "G"},
						{"Y", "G", "G", "G", "G", "G", "G"},
						{"Y", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("Y", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "R"},
						{"G", "G", "G", "G", "G", "G", "R"},
						{"G", "G", "G", "G", "G", "G", "R"},
						{"G", "G", "G", "G", "G", "G", "R"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "Y", "G", "G"},
						{"G", "G", "R", "G", "Y", "G", "G"},
						{"G", "G", "R", "G", "Y", "G", "G"},
						{"G", "G", "R", "G", "G", "G", "G"},
						{"G", "G", "R", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "Y", "G", "Y", "G", "G"},
						{"G", "G", "Y", "G", "Y", "G", "G"},
						{"G", "R", "Y", "G", "Y", "G", "G"},
						{"G", "R", "G", "G", "G", "G", "G"},
						{"G", "R", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(false, connect4GameBoard.is_gameover());
		assertEquals(null, connect4GameBoard.get_piece_color_who_won());



	}

	@Test
	void game_over_diagonal () {
		gameboard = new String[][]
				{		{"R", "G", "G", "G", "G", "G", "G"},
						{"G", "R", "G", "G", "G", "G", "G"},
						{"G", "G", "R", "G", "G", "G", "G"},
						{"G", "G", "G", "R", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "Y", "G", "G", "G"},
						{"G", "G", "G", "G", "Y", "G", "G"},
						{"G", "G", "G", "G", "G", "Y", "G"},
						{"G", "G", "G", "G", "G", "G", "Y"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("Y", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "Y"},
						{"G", "G", "G", "G", "G", "Y", "G"},
						{"G", "G", "G", "G", "Y", "G", "G"},
						{"G", "G", "G", "Y", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("Y", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "Y"},
						{"G", "G", "G", "G", "G", "Y", "G"},
						{"G", "G", "G", "R", "Y", "G", "G"},
						{"G", "G", "R", "G", "G", "G", "G"},
						{"G", "R", "G", "G", "G", "G", "G"},
						{"R", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals("R", connect4GameBoard.get_piece_color_who_won());


		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "Y"},
						{"R", "Y", "G", "G", "G", "Y", "G"},
						{"G", "G", "Y", "G", "Y", "G", "G"},
						{"G", "G", "R", "G", "G", "G", "G"},
						{"G", "R", "G", "G", "R", "R", "R"},
						{"R", "G", "G", "G", "G", "G", "G"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(false, connect4GameBoard.is_gameover());
		assertEquals(null, connect4GameBoard.get_piece_color_who_won());

	}

	@Test
	void game_over_tie() {
		gameboard = new String[][]
				{		{"R", "Y", "R", "Y", "R", "Y", "R"},
						{"R", "Y", "R", "Y", "R", "Y", "R"},
						{"R", "Y", "R", "Y", "R", "Y", "R"},
						{"Y", "R", "Y", "R", "Y", "R", "Y"},
						{"Y", "R", "Y", "R", "Y", "R", "Y"},
						{"Y", "R", "Y", "R", "Y", "R", "Y"}};
		connect4GameBoard.setGameBoard(gameboard);
		assertEquals(true, connect4GameBoard.is_gameover());
		assertEquals(null, connect4GameBoard.get_piece_color_who_won());

	}

	@Test
	void update_GameBoard() {

		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"}};

		connect4GameBoard = new Connect4GameBoard();
		connect4GameBoard.update_GameBoard(0, 0, "R");
		assertTrue(Arrays.deepEquals(gameboard, connect4GameBoard.getGameBoard()));

		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"Y", "G", "G", "G", "G", "G", "G"}};

		connect4GameBoard = new Connect4GameBoard();
		connect4GameBoard.update_GameBoard(5, 0, "Y");
		assertTrue(Arrays.deepEquals(gameboard, connect4GameBoard.getGameBoard()));

		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "R", "G", "G", "G"},
						{"G", "G", "G", "Y", "G", "G", "G"},
						{"G", "G", "G", "R", "G", "G", "G"},
						{"G", "G", "G", "Y", "G", "G", "G"}};

		connect4GameBoard = new Connect4GameBoard();
		connect4GameBoard.update_GameBoard(5, 3, "Y");
		connect4GameBoard.update_GameBoard(4, 3, "R");
		connect4GameBoard.update_GameBoard(3, 3, "Y");
		connect4GameBoard.update_GameBoard(2, 3, "R");

		assertTrue(Arrays.deepEquals(gameboard, connect4GameBoard.getGameBoard()));

		gameboard = new String[][]
				{		{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "G", "G", "G", "G"},
						{"G", "G", "G", "Y", "G", "G", "G"},
						{"G", "G", "R", "R", "R", "G", "G"},
						{"G", "G", "Y", "Y", "Y", "R", "G"},
						{"G", "G", "R", "R", "R", "Y", "R"}};

		connect4GameBoard = new Connect4GameBoard();
		connect4GameBoard.update_GameBoard(5, 2, "R");
		connect4GameBoard.update_GameBoard(5, 3, "R");
		connect4GameBoard.update_GameBoard(5, 4, "R");
		connect4GameBoard.update_GameBoard(5, 5, "Y");
		connect4GameBoard.update_GameBoard(5, 6, "R");

		connect4GameBoard.update_GameBoard(4, 5, "R");
		connect4GameBoard.update_GameBoard(4, 4, "Y");
		connect4GameBoard.update_GameBoard(4, 3, "Y");
		connect4GameBoard.update_GameBoard(4, 2, "Y");

		connect4GameBoard.update_GameBoard(3, 4, "R");
		connect4GameBoard.update_GameBoard(3, 3, "R");
		connect4GameBoard.update_GameBoard(3, 2, "R");

		connect4GameBoard.update_GameBoard(2, 3, "Y");

		connect4GameBoard.print_gameboard();
		assertTrue(Arrays.deepEquals(gameboard, connect4GameBoard.getGameBoard()));




	}

}
