package ttt;

import java.util.ArrayList;
import java.util.Scanner;
/*
* usage of this class:
* super class of games
* has some general function such as check win
* check validation when enter coordination
*
* */

public class Generalboardgame {
	protected Board board;
	protected int wincondition;
	protected Player player1 =new Player();
	protected Player player2 =new Player();
	protected Player current_player =new Player();
	protected boolean finish=false;
	protected Scanner sclocation;
	protected int N;
	protected int M;

	public void CheckWin(int row, int col, Pieces player) {
		int num1=0;
		int num2=0;
		//System.out.println(this.BoardLength);

		for(int i=col-1;i>-1;i--) {
			if(this.board.position_info(row,i).equals(" ")) {break;}
			if(!this.board.position_info(row,i).equals(player.getPiece_Ptest())) {break;}
			else {num1++;}
		}
		for(int i=col+1;i<this.M;i++) {
			if(this.board.position_info(row,i).equals(" ")) {break;}
			if(!this.board.position_info(row,i).equals(player.getPiece_Ptest())) {break;}
			else {num2++;}
		}
		//System.out.println(""+num2+num1);
		if(num1+num2==this.wincondition) {this.finish=true;}
		//check col
		num1=0;
		num2=0;
		for(int i=row-1;i>-1;i--) {
			if(this.board.position_info(i,col).equals(" ")){break;}
			if(!this.board.position_info(i,col).equals(player.getPiece_Ptest())) {break;}
			else {num1++;}
		}
		for(int i=row+1;i<this.N;i++) {
			if(this.board.position_info(i,col).equals(" ")){break;}
			if(!this.board.position_info(i,col).equals(player.getPiece_Ptest())) {break;}
			else {num2++;}
		}
		if(num1+num2==this.wincondition) {this.finish=true;}
		//check diagnal
		num1=0;
		num2=0;
		for(int i=1;i<Math.min(row, col)+1;i++) {
			if(this.board.position_info(row-i,col-i).equals(" ")){break;}
			if(!this.board.position_info(row-i,col-i).equals(player.getPiece_Ptest())) {break;}
			else {num1++;}
		}
		for(int i=1;i<Math.min(this.N-row-1, this.M-col-1)+1;i++) {
			if(this.board.position_info(row+i,col+i).equals(" ")){break;}
			if(!this.board.position_info(row+i,col+i).equals(player.getPiece_Ptest())) {break;}
			else {num2++;}
		}
		if(num1+num2==this.wincondition) {this.finish=true;}
		//check anti-diagnal
		num1=0;
		num2=0;
		for(int i=1;i<Math.min(this.N-row-1, col)+1;i++) {
			if(this.board.position_info(row+i,col-i).equals(" ")){break;}
			if(!this.board.position_info(row+i,col-i).equals(player.getPiece_Ptest())) {break;}
			else {num1++;}
		}
		for(int i=1;i<Math.min(row, this.M-col-1)+1;i++) {
			if(this.board.position_info(row-i,col+i).equals(" ")){break;}
			if(!this.board.position_info(row-i,col+i).equals(player.getPiece_Ptest())) {break;}
			else {num2++;}
		}
		if(num1+num2==this.wincondition) {this.finish=true;}

	}
	public boolean CheckFilled(){
		boolean filled=true;
		for(int i=0;i<this.N;i++){
			for(int j=0;j<this.M;j++){
				if(this.board.position_info(i,j).equals(" ")){
					filled=false;
				}
			}
		}
		if (filled==true) {
			this.finish=true;
		}
		return filled;
	}

	public int[] CheckValidationOfCoordinate(int choose){
		int[] coordinate=new int[2];
		this.sclocation=new Scanner(System.in);

		boolean set_coordinate=false;
		int c_c=-1;
		while(!set_coordinate){
			if (choose>0){
				System.out.println(this.current_player.get_name()+",please enter a Cell" +
						"\n(0 back to big Board,-1 quit the game):");
				c_c=this.CheckSetUp(-1,this.N*this.M);
				if(c_c>0){
					set_coordinate=this.board.check_full(((c_c - 1) / this.M), ((c_c-1) % this.M));
					coordinate[0]=((c_c - 1) / this.M);
					coordinate[1]=((c_c-1) % this.M);
				}else if(c_c<1){
					set_coordinate=true;
					coordinate[0]=((c_c-1));
					coordinate[1]=((c_c-1));
				}

			}
			else{
				System.out.println(this.current_player.get_name()+",please enter a Cell:");
				c_c=this.CheckSetUp(1,this.N*this.M);
				set_coordinate=this.board.check_full(((c_c - 1) / this.M), ((c_c-1) % this.M));
				coordinate[0]=((c_c - 1) / this.M);
				coordinate[1]=((c_c-1) % this.M);
			}

		}
		return coordinate;
	}

	public int CheckSetUp(int first,int last){
		boolean valid=false;
		ArrayList<String> as=new ArrayList<>();
		for (int i=first;i<=last;i++){
			as.add(String.valueOf(i));
		}

		int enter=0;
		while (!valid){
			Scanner sc_size = new Scanner(System.in);
			String jadge_input=sc_size.nextLine();
			if (as.contains(jadge_input)){
				valid=true;
				enter=Integer.valueOf(jadge_input).intValue();
			}else {
				System.out.println("no valid");
			}
		}
		return enter;
	}
}
