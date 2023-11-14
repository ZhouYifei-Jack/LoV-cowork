package ttt;
/*
 * usage of this class:
 * extends from general game
 *
 *
 *
 *
 * */
import java.util.Scanner;


public class Orderandchaosgame extends Generalboardgame {
	

	private Player Order;
	private  Player Chaos;
	private Pieces o_p1;
	private Pieces o_p2;

	public void Init(Player player1,Player player2) {
		this.player1=player1;
		this.player2=player2;
		this.o_p1=player1.get_piece();
		this.o_p2=player2.get_piece();


		Board o_t=new Board();
		this.board=o_t;

		this.N=6;
		this.M=6;
		this.board.SetBoardSize(this.N,this.M);
		System.out.println("The size of the board will be fixed as 6x6 as below" +
				"\nand the rule will be:" +
				"\nfor any pieces ,if 5 same pieces in a line,Order win," +
				"\nif not make it in line until every cell filled,Chaos win");
		this.wincondition=4;

		this.board.print_with_location();
		System.out.println("who want to be Order(0 for "+this.player1.get_name()+",1 for"+this.player2.get_name()+")");

		int c_o=this.CheckSetUp(0,1);
		if(c_o==0){
			this.Order=this.player1;
			this.Chaos=this.player2;
		}else {
			this.Chaos=this.player1;
			this.Order=this.player2;
		}
		this.StartPlay();
	}
	public void StartPlay() {
		this.current_player=this.player1;
		this.finish=false;
		this.sclocation=new Scanner(System.in);


		System.out.println("set pieces just for oac,no influence on other games");
		boolean piece_different=false;
		Pieces p1 = new Pieces();
		Pieces p2 = new Pieces();
		p1.createPtest();
		while (!piece_different){
			p2.createPtest();
			if (!p1.getPiece_Ptest().equals(p2.getPiece_Ptest())){
				piece_different=true;

			}else {
				System.out.println("pieces should not be same,enter again for the second piece");
			}
		}
		this.player1.set_pieces(p1);
		this.player2.set_pieces(p2);



		
		System.out.println("Start play!!");
		this.board.print_with_location();
		while(!this.finish) {


			System.out.println(current_player.get_name()+",pick a pieces,0 for "+p1.getPiece_Ptest()+"," +
					"1 for "+p2.getPiece_Ptest());
			int p=this.CheckSetUp(0,1);
			if(p==0){
				current_player.set_pieces(p1);
			}else {
				current_player.set_pieces(p2);
			}



			int[]coord=new int[2];
			coord=this.CheckValidationOfCoordinate(-1);
			int row=coord[0];
			int col=coord[1];
			this.board.set_p(row,col,this.current_player.get_piece());
			this.board.print_with_location();
			this.CheckWin(row, col,this.current_player.get_piece());

			if(this.finish) {
				System.out.println(this.Order.get_name()+" Order win");
				this.Order.add_win();
				this.Order.get_win();
				System.out.println(" ");
				System.out.println(" ");
				//PrintBoard();
			}else {
				if(this.CheckFilled()){
					System.out.println(this.Chaos.get_name()+" Chaos win");
					this.Chaos.add_win();
					this.Chaos.get_win();
				};
				this.current_player=(this.current_player==this.player1)?this.player2:this.player1;
			}


			this.player1.set_pieces(o_p1);
			this.player2.set_pieces(o_p2);


		}
		System.out.println("Do you want to play again?");
		
		
		
	}

	

}
