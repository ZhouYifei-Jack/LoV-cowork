package ttt;

import java.util.ArrayList;
import java.util.List;

public class Quoridor extends Generalboardgame {
    private QuoridorBoard q_board;
    protected int total_N;
    protected int total_M;
    int c_another_p;
    protected Board actualBoard;


    public void Init(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        System.out.println("Enter the size for rows (from 3 to 10):");
        this.N = this.CheckSetUp(3, 10);
        System.out.println("Enter the size for columns (from 3 to 10):");
        this.M = this.CheckSetUp(3, 10);
        this.total_N = 2 * this.N - 1;
        this.total_M = 2 * this.M - 1;


        q_board = new QuoridorBoard();
        q_board.setQuoridorBoard(this.N, this.M);


        System.out.println("How many block every players can use? (from 3 to 10)");
        int total_wall = this.CheckSetUp(3, 10);
        this.player1.setNumber_of_blocks(total_wall);
        this.player2.setNumber_of_blocks(total_wall);
        this.StartPlay();
    }

    public void StartPlay() {
        System.out.println("Game Start!");
        initializePlayers();
        printBoardAndSetFirstPlayer();

        while (!this.finish) {
            int c_a = getPlayerAction();
            if (c_a == 2 & this.current_player.getNumber_of_blocks() > 0) {
                boolean hasPathAfterPlaceWall = handleBlockPlacing();
                if (!hasPathAfterPlaceWall) {
                    continue;
                }
            } else {
                handlePlayerMove();
            }
            checkAndDeclareWinner();
        }
        System.out.println("Do you want to play again?");
    }

    private void checkAndDeclareWinner() {
        this.finish = this.check_win(current_player);
        if (this.finish) {
            System.out.println("Player " + this.current_player.get_name() + " win");
            this.current_player.add_win();
            this.current_player.get_win();
            System.out.println();
        } else {
            this.current_player = (this.current_player == this.player1) ? this.player2 : this.player1;
        }
    }

    private boolean handleBlockPlacing() {
        Player currentPlayer = (current_player == player1) ? player1 : player2;
        int left_b = currentPlayer.getNumber_of_blocks();
        currentPlayer.setNumber_of_blocks(left_b - 1);

        while (true) {
            System.out.println("Enter the first point of the wall: ");
            int c_p = CheckSetUp(1, total_M * total_N);

            if (isValidInitialPoint(c_p)) {
                int c_p_i = calCoordinate(c_p, total_M, "i");
                int c_p_j = calCoordinate(c_p, total_M, "j");

                if (setBlockPoints(c_p, c_p_i, c_p_j)) {
                    // Check if there is a path to reach the destination for current player's position
                    PathFinder pathFinder = new PathFinder();
                    boolean hasPath = pathFinder.hasPath(q_board, player1, player2);

                    if (hasPath) {
                        System.out.println(q_board);
                        return true;
                    } else {
                        System.out.println("Cannot block player, please select another point again: ");
                        unsetWall(c_p, c_another_p);
                        currentPlayer.setNumber_of_blocks(left_b);
                        return false;
                    }
                }
            } else {
                System.out.println("Invalid(this point is a intersection of cell,not an edge)");
            }
        }
    }


    private boolean isValidInitialPoint(int c_p) {
        return c_p % 2 == 0 && q_board.check_full(calCoordinate(c_p, total_M, "i"), calCoordinate(c_p, total_M, "j"));
    }

    private boolean setBlockPoints(int c_p, int c_p_i, int c_p_j) {
        while (true) {
            System.out.println("First point of the wall is set at " + c_p +
                    " , enter second point (enter 0 to back to set new first point)");
            c_another_p = CheckSetUp(0, total_M * total_N);

            if (c_p_i % 2 == 0) {
                if (isWallValid(c_p, c_another_p, c_p_i, c_p_j, "|", "i")) {
                    return true;
                }
            } else {
                if (isWallValid(c_p, c_another_p, c_p_i, c_p_j, "-", "j")) {
                    return true;
                }
            }
            if (c_another_p == 0) {
                return false;
            } else System.out.println("Invalid, wall can't place across Cell");
        }
    }

    private boolean isWallValid(int c_p, int c_another_p, int c_p_i, int c_p_j, String blockType, String coordinateType) {
        int diff_i = Math.abs(calCoordinate(c_p, total_M, "i") - calCoordinate(c_another_p, total_M, "i"));
        int diff_j = Math.abs(calCoordinate(c_p, total_M, "j") - calCoordinate(c_another_p, total_M, "j"));

        boolean isVerticalCondition = "i".equals(coordinateType) ? diff_i == 2 && diff_j == 0 : diff_i == 0 && diff_j == 2;

        if (c_another_p % 2 == 0 && isVerticalCondition && checkCellForWall(c_p, c_another_p, c_p_i, c_p_j, coordinateType)) {
            Block b = new Block();
            b.setBlock(blockType);
            setWallOnBoard(c_p, c_another_p, c_p_i, c_p_j, b);
            return true;
        }
        return false;
    }

    private boolean checkCellForWall(int c_p, int c_another_p, int c_p_i, int c_p_j, String coordinateType) {
        int middle_i = (calCoordinate(c_p, total_M, "i") + calCoordinate(c_another_p, total_M, "i")) / 2;
        int middle_j = (calCoordinate(c_p, total_M, "j") + calCoordinate(c_another_p, total_M, "j")) / 2;

        return q_board.check_full(calCoordinate(c_p, total_M, "i"), calCoordinate(c_p, total_M, "j"))
                && q_board.check_full(calCoordinate(c_another_p, total_M, "i"), calCoordinate(c_another_p, total_M, "j"))
                && q_board.check_full("i".equals(coordinateType) ? middle_i : calCoordinate(c_p, total_M, "i"),
                "j".equals(coordinateType) ? middle_j : calCoordinate(c_p, total_M, "j"));
    }

    private void setWallOnBoard(int c_p, int c_another_p, int c_p_i, int c_p_j, Block b) {
        q_board.set_p(calCoordinate(c_p, total_M, "i"), calCoordinate(c_p, total_M, "j"), b);
        q_board.set_p(calCoordinate(c_another_p, total_M, "i"), calCoordinate(c_another_p, total_M, "j"), b);
        q_board.set_p((calCoordinate(c_p, total_M, "i") + calCoordinate(c_another_p, total_M, "i")) / 2,
                (calCoordinate(c_p, total_M, "j") + calCoordinate(c_another_p, total_M, "j")) / 2, b);
    }

    private void unsetWall(int c_p, int c_another_p) {
        q_board.clear_p(calCoordinate(c_p, total_M, "i"), calCoordinate(c_p, total_M, "j"));
        q_board.clear_p(calCoordinate(c_another_p, total_M, "i"), calCoordinate(c_another_p, total_M, "j"));
        q_board.clear_p((calCoordinate(c_p, total_M, "i") + calCoordinate(c_another_p, total_M, "i")) / 2,
                (calCoordinate(c_p, total_M, "j") + calCoordinate(c_another_p, total_M, "j")) / 2);
    }

    private int calCoordinate(int c_p, int total_M, String type) {
        return "i".equals(type) ? (c_p - 1) / total_M : (c_p - 1) % total_M;
    }

    private void handlePlayerMove() {
        if (this.current_player.getNumber_of_blocks() == 0) {
            System.out.println("You can only move (no blocks left)");
        }

        boolean is_move = false;
        while (!is_move) {
            boolean canJump = canJumpOverOpponent(current_player, q_board);
            // Player will jump over opponent if they choose to move and canJump is true.
            if (canJump) {
                if (jumpOverOpponent()) {
                    System.out.println(q_board);
                    is_move = true;
                }
            } else {
                System.out.println("hi," + this.current_player.get_name() +
                        "(" + this.current_player.get_piece().getPiece_Ptest() + ")" + " enter:" +
                        "\n1 for up" +
                        "\n2 for down" +
                        "\n3 for left" +
                        "\n4 for right");
                int c_m = this.CheckSetUp(1, 4);

                if (check_direction(c_m, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    move(c_m, current_player.getPlayer_current_row(), current_player.getPlayer_current_col());
                    System.out.println(q_board);
                    is_move = true;
                } else {
                    System.out.println("Cannot move to that direction, please choose again.");
                }
            }
        }
    }

    private boolean jumpOverOpponent() {
        int currRow = current_player.getPlayer_current_row();
        int currCol = current_player.getPlayer_current_col();
        Player opponent = (current_player == player1) ? player2 : player1;

        // 1: up 2: down 3: left 4:right
        int[] directions = {1, 2, 3, 4};
        for (int dir : directions) {
            int newRow = currRow;
            int newCol = currCol;

            switch (dir) {
                case 1:
                    newRow -= 4;
                    break;
                case 2:
                    newRow += 4;
                    break;
                case 3:
                    newCol -= 4;
                    break;
                case 4:
                    newCol += 4;
                    break;
                default:
                    return false;
            }

            if (getCellValue(newRow, newCol, currRow, currCol, opponent)) {
                q_board.clear_p(currRow, currCol);
                q_board.set_p(newRow, newCol, current_player.get_piece());
                current_player.setPlayer_current_row(newRow);
                current_player.setPlayer_current_col(newCol);
                return true;
            }
        }
        return false;
    }

    private boolean getCellValue(int newRow, int newCol, int currRow, int currCol, Player opponent) {
        if (newRow >= 0 && newRow < total_N && newCol >= 0 && newCol < total_M) {
            int oppRow = currRow + (newRow - currRow) / 2;
            int oppCol = currCol + (newCol - currCol) / 2;
            String cellValue = q_board.position_info(oppRow, oppCol);

            return cellValue.equals(opponent.get_piece().getPiece_Ptest());
        }
        return false;
    }

    public boolean canJumpOverOpponent(Player player, QuoridorBoard board) {
        List<Integer> playableDirections = findPlayableDirection(player, board);

        if (playableDirections.size() == 1) {
            int currentRow = player.getPlayer_current_row();
            int currentCol = player.getPlayer_current_col();
            int direction = playableDirections.get(0);

            int newRow = currentRow;
            int newCol = currentCol;
            switch (direction) {
                case 1:
                    newRow -= 2;
                    break;
                case 2:
                    newRow += 2;
                    break;
                case 3:
                    newCol -= 2;
                    break;
                case 4:
                    newCol += 2;
                    break;
                default:
                    return false;
            }
            // Check if the cell contains opponent's piece
            String cellValue = board.position_info(newRow, newCol);
            Player opponent = (player == player1) ? player2 : player1;
            String opponentPiece = opponent.get_piece().getPiece_Ptest();

            return cellValue.equals(opponentPiece);
        }

        return false;
    }

    private List<Integer> findPlayableDirection(Player player, QuoridorBoard board) {
        int currRow = player.getPlayer_current_row();
        int currCol = player.getPlayer_current_col();
        List<Integer> playableDirection = new ArrayList<>();

        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < direction.length; i++) {
            int newRow = currRow + direction[i][0];
            int newCol = currCol + direction[i][1];

            if (isValidPosition(newRow, newCol, board)) {
                String cellValue = board.position_info(newRow, newCol);

                if (!cellValue.contains("|") && !cellValue.contains("-")) {
                    playableDirection.add(i + 1);
                }
            }
        }
        return playableDirection;
    }

    private boolean isValidPosition(int row, int col, QuoridorBoard board) {
        int numRows = board.getN();
        int numCols = board.getM();
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private void initializePlayers() {
        q_board.set_p(this.total_N - 1, 2 * ((int) Math.ceil(this.M / 2)), this.player1.get_piece());
        q_board.set_p(0, (this.total_M - 1) - 2 * ((int) Math.ceil(this.M / 2)), this.player2.get_piece());
        player1.setFinish_position_for_player(0);
        player2.setFinish_position_for_player(this.total_N - 1);
    }

    private void printBoardAndSetFirstPlayer() {
        System.out.println(q_board);
        player1.setPlayer_current_row(this.total_N - 1);
        player1.setPlayer_current_col(2 * ((int) Math.ceil(this.M / 2)));
        player2.setPlayer_current_row(0);
        player2.setPlayer_current_col((this.total_M - 1) - 2 * ((int) Math.ceil(this.M / 2)));
        this.current_player = this.player1;
    }

    private int getPlayerAction() {
        System.out.println("hi," + this.current_player.get_name() +
                "(" + this.current_player.get_piece().getPiece_Ptest() + ")" + " enter:" +
                "\n1 for moving" +
                "\n2 for putting block(you have " + this.current_player.getNumber_of_blocks() + " blocks left)");
        return this.CheckSetUp(1, 2);
    }

    public boolean check_direction(int i, int now_i, int now_j) {
        if (i == 1) {//up
            if (now_i - 2 >= 0) {
                return this.q_board.position_info(now_i - 1, now_j).equals(" ")
                        & this.q_board.position_info(now_i - 2, now_j).equals(" ");
            } else {
                return false;
            }

        } else if (i == 2) {//down
            if (now_i + 2 <= this.total_N - 1) {
                return this.q_board.position_info(now_i + 1, now_j).equals(" ")
                        & this.q_board.position_info(now_i + 2, now_j).equals(" ");
            } else {
                return false;
            }

        } else if (i == 3) {//left
            if (now_j - 2 >= 0) {
                return this.q_board.position_info(now_i, now_j - 1).equals(" ")
                        & this.q_board.position_info(now_i, now_j - 2).equals(" ");
            } else {
                return false;
            }

        } else if (i == 4) {//right
            if (now_j + 2 <= this.total_M - 1) {
                return this.q_board.position_info(now_i, now_j + 1).equals(" ")
                        & this.q_board.position_info(now_i, now_j + 2).equals(" ");
            } else {
                return false;
            }
        }
        return false;
    }

    public void move(int i, int now_i, int now_j) {
        if (i == 1) {//up
            q_board.clear_p(now_i, now_j);
            q_board.set_p(now_i - 2, now_j, this.current_player.get_piece());
            this.current_player.setPlayer_current_row(now_i - 2);
        } else if (i == 2) {//down
            q_board.clear_p(now_i, now_j);
            q_board.set_p(now_i + 2, now_j, this.current_player.get_piece());
            this.current_player.setPlayer_current_row(now_i + 2);
        } else if (i == 3) {//left
            q_board.clear_p(now_i, now_j);
            q_board.set_p(now_i, now_j - 2, this.current_player.get_piece());
            this.current_player.setPlayer_current_col(now_j - 2);
        } else if (i == 4) {//right
            q_board.clear_p(now_i, now_j);
            q_board.set_p(now_i, now_j + 2, this.current_player.get_piece());
            this.current_player.setPlayer_current_col(now_j + 2);
        }
    }


    public boolean check_win(Player player) {
        return player.getFinish_position_for_player() == player.getPlayer_current_row();
    }

}
