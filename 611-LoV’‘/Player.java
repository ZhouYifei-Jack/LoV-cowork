package ttt;
import java.util.Scanner;
/*
 * usage of this class:
 * create player storage name and pieces
 * record win times
 * */
public class Player {
	private Pieces piece;
	protected String name;
	private int time_of_win=0;

	private int player_now_row;
	private int player_now_col;

	private int finish_position_for_player;

	public int getNumber_of_blocks() {
		return number_of_blocks;
	}

	public void setNumber_of_blocks(int number_of_blocks) {
		this.number_of_blocks = number_of_blocks;
	}

	private int number_of_blocks;

	public void set_name() {
		System.out.println("please enter your name:");
		Scanner sc_choose = new Scanner(System.in);
		String cp=sc_choose.nextLine();
		this.name=cp;
	}
	public void create_a_player() {
		this.set_name();
	}
	public void set_pieces(Pieces piece) {
		this.piece=piece;
	}
	
	public String get_name() {
		return this.name;
	}
	
	public void add_win() {
		this.time_of_win=this.time_of_win+1;
	}
	public void get_win() {
		System.out.println("Totally win :"+this.time_of_win+" times");
	}
	public Pieces get_piece() {
		return this.piece;
	}

	public int getPlayer_current_row() {
		return player_now_row;
	}

	public void setPlayer_current_row(int player_now_row) {
		this.player_now_row = player_now_row;
	}

	public int getPlayer_current_col() {
		return player_now_col;
	}

	public void setPlayer_current_col(int player_now_col) {
		this.player_now_col = player_now_col;
	}

	public int getFinish_position_for_player() {
		return finish_position_for_player;
	}

	public void setFinish_position_for_player(int finish_position_for_player) {
		this.finish_position_for_player = finish_position_for_player;
	}

}
