package ttt;

import java.util.Arrays;
import java.util.Stack;

public class PathFinder {
    private boolean[][] visited;

    public boolean hasPath(QuoridorBoard board, Player player1, Player player2) {
        int rows = board.getN();
        int cols = board.getM();
        visited = new boolean[rows][cols];

        int startRow1 = player1.getPlayer_current_row();
        int startCol1 = player1.getPlayer_current_col();
        int destRow1 = player1.getFinish_position_for_player();

        int startRow2 = player2.getPlayer_current_row();
        int startCol2 = player2.getPlayer_current_col();
        int destRow2 = player2.getFinish_position_for_player();

        // Only check for cell that are for player symbol not for walls
        if (startRow1 % 2 != 0 || startCol1 % 2 != 0 || destRow1 % 2 != 0 ||
                startRow2 % 2 != 0 || startCol2 % 2 != 0 || destRow2 % 2 != 0) {
            return false;
        }

        boolean hasPathForPlayer1 = dfs(board, startRow1, startCol1, destRow1);

        // reset visited
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visited[i], false);
        }

        boolean hasPathForPlayer2 = dfs(board, startRow2, startCol2, destRow2);

        return hasPathForPlayer1 && hasPathForPlayer2;
    }

    private boolean dfs(QuoridorBoard board, int currentRow, int currentCol, int destRow) {
        int[][] direction = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
        if (currentRow == destRow) {
            return true;
        }

        visited[currentRow][currentCol] = true;

        for (int[] dir : direction) {
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];

            // Check if the new position is within the bounds of the board
            if (isValidMove(board, currentRow, currentCol, newRow, newCol) && !visited[newRow][newCol]) {
                // Check if the cell next to the current position has no wall
                if (!hasWallInBetween(board, currentRow, currentCol, newRow, newCol)) {
                    if (dfs(board, newRow, newCol, destRow)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isValidMove(QuoridorBoard board, int currentRow, int currentCol, int newRow, int newCol) {
        int rows = board.getN();
        int cols = board.getM();

        // Check if the new position is within the bounds of the board
        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
    }

    private boolean hasWallInBetween(QuoridorBoard board, int currentRow, int currentCol, int newRow, int newCol) {
        int rows = board.getN();
        int cols = board.getM();

        // Check if the new coordinates are within bounds of the board
        if (currentRow < 0 || currentRow >= rows || currentCol < 0 || currentCol >= cols ||
                newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
            return false;
        }

        // Calculate the coordinates of the wall
        int wallRow = (currentRow + newRow) / 2;
        int wallCol = (currentCol + newCol) / 2;

        // Check if the wall coordinates are within the valid bounds of the board
        // return false for out of bound
        if (wallRow < 0 || wallRow >= rows || wallCol < 0 || wallCol >= cols) {
            return false;
        }

        // Check if there is a wall at the coordinates
        String cellValue = board.position_info(wallRow, wallCol);
        return cellValue.contains("|") || cellValue.contains("-");
    }

}
