import java.util.Scanner;
import java.util.ArrayList;

public class Chess {

    abstract public class Piece {
        // White is false Black is true
        private boolean color;
        private int x;
        private int y;
        private String name;
        public ArrayList<Integer> moves = new ArrayList<Integer>();
        Piece(boolean c, int x, int y) {
            this.color = c;
            this.x = x;
            this.y = y;
        }
        abstract public boolean validMove(int x, int y);
        public boolean validMoveHelper(int x, int y) {
            int move = x * 10 + y;
            if(moves.contains(move)) {
                Piece p = this;
                board[getX()][getY()] = null;
                board[x][y] = p;
                setXY(x, y);
                return true;
            }
            else {
                return false;
            }
        }
        public boolean isWhite() {
            return !color;
        }
        public boolean isBlack() {
            return color;
        }
        public boolean getColor() {
            return color;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String getName() {
            return name;
        }
    }
    public class Pawn extends Piece {
        private boolean firstTime = true;
        Pawn(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-Pawn";
            }
            else {
                super.name = "b-Pawn";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves
            if(isWhite()) {
                if(firstTime) {
                    if(board[getX()][getY() + 2] == null && board[getX()][getY() + 1] == null)
                        moves.add(getX() * 10 + getY() + 2);
                }
                if(board[getX()][getY() + 1] == null)
                    moves.add(getX() * 10 + getY() + 1);
                // If to the left or right of the board dont go out of bounds
                if(0 <= getX() - 1 && getX() - 1 <= 8 && 0 <= getY() + 1 && getY() + 1 <= 8)
                    if(board[getX() - 1][getY() + 1] != null && board[getX() - 1][getY() + 1].isBlack())
                        moves.add((getX() - 1) * 10 + getY() + 1);
                // If to the left or right of the board dont go out of bounds
                if(0 <= getX() + 1 && getX() + 1 <= 8 && 0 <= getY() + 1 && getY() + 1 <= 8)
                    if(board[getX() + 1][getY() + 1] != null && board[getX() + 1][getY() + 1].isBlack())
                        moves.add((getX() + 1) * 10 + getY() + 1);
            }
            else if (isBlack()) {
                if(firstTime) {
                    if(board[getX()][getY() - 2] == null && board[getX()][getY() - 1] == null)
                        moves.add(getX() * 10 + getY() - 2);
                }
                // Go forward one as black
                if(board[getX()][getY() - 1] == null)
                    moves.add(getX() * 10 + getY() - 1);
                // Take left diagonal
                if(0 <= getX() - 1 && getX() - 1 <= 8 && 0 <= getY() - 1 && getY() - 1 <= 8)
                    if(board[getX() - 1][getY() - 1] != null && board[getX() - 1][getY() - 1].isWhite())
                        moves.add((getX() - 1) * 10 + getY() - 1);
                // Take right diagonal
                if(0 <= getX() - 1 && getX() - 1 <= 8 && 0 <= getY() - 1 && getY() - 1 <= 8)
                    if(board[getX() + 1][getY() - 1] != null && board[getX() + 1][getY() - 1].isWhite())
                        moves.add((getX() + 1) * 10 + getY() - 1);
            }
            // Check if move is valid
            int move = x * 10 + y;
            if(moves.contains(move)) {
                Piece p = this;
                board[getX()][getY()] = null;
                board[x][y] = p;
                setXY(x, y);
                firstTime = false;
                return true;
            }
            else {
                return false;
            }
        }
    }
    public class Rook extends Piece {
        Rook(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-Rook";
            }
            else {
                super.name = "b-Rook";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves
            // Left of Rook
            for(int col = getX() - 1; col >= 0; col--) {
                if(board[col][getY()] != null && board[col][getY()].getColor() == getColor()) {
                    break;
                }
                else if (board[col][getY()] != null && board[col][getY()].getColor() != getColor()) {
                    moves.add(col * 10 + getY());
                    break;
                }
                moves.add(col * 10 + getY());
            }
            // Right of Rook
            for(int col = getX() + 1; col < 8; col++) {
                if(board[col][getY()] != null && board[col][getY()].getColor() == getColor()) {
                    break;
                }
                else if (board[col][getY()] != null && board[col][getY()].getColor() != getColor()) {
                    moves.add(col * 10 + getY());
                    break;
                }
                moves.add(col * 10 + getY());
            }
            // Bottom of Rook
            for(int row = getY() - 1; row >= 0; row--) {
                if(board[getX()][row] != null && board[getX()][row].getColor() == getColor()) {
                    break;
                }
                else if (board[getX()][row] != null && board[getX()][row].getColor() != getColor()) {
                    moves.add(getX() * 10 + row);
                    break;
                }
                moves.add(getX() * 10 + row);
            }
            // Top of Rook
            for(int row = getY() + 1; row < 8; row++) {
                if(board[getX()][row] != null && board[getX()][row].getColor() == getColor()) {
                    break;
                }
                else if (board[getX()][row] != null && board[getX()][row].getColor() != getColor()) {
                    moves.add(getX() * 10 + row);
                    break;
                }
                moves.add(getX() * 10 + row);
            }

            
            // Check if move is valid
            return validMoveHelper(x, y);
        }
    }
    public class Knight extends Piece {
        Knight(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-Knight";
            }
            else {
                super.name = "b-Knight";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves
            knightHelper(getX() + 1, getY() + 2);
            knightHelper(getX() + 1, getY() - 2);
            knightHelper(getX() - 1, getY() + 2);
            knightHelper(getX() - 1, getY() - 2);
            knightHelper(getX() + 2, getY() + 1);
            knightHelper(getX() + 2, getY() - 1);
            knightHelper(getX() - 2, getY() + 1);
            knightHelper(getX() - 2, getY() - 1);

            
            // Check if move is valid
            return validMoveHelper(x, y);
        }
        public void knightHelper(int a, int b) {
            if(0 <= a && a <= 7 && 0 <= b && b <= 7) {
                if(board[a][b] == null || getColor() != board[a][b].getColor())
                    moves.add(a * 10 + b);
            } 
        }
    }
    public class Bishop extends Piece {
        Bishop(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-Bishop";
            }
            else {
                super.name = "b-Bishop";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves

            
            for(int a = 0; a < 8; a++) {
                bishopHelper(getX() + a, getY() + a);
                bishopHelper(getX() + a, getY() - a);
                bishopHelper(getX() - a, getY() + a);
                bishopHelper(getX() - a, getY() - a);
            }

            // Check if move is valid
            return validMoveHelper(x, y);
        }
        public void bishopHelper(int a, int b) {
            if(0 <= a && a <= 7 && 0 <= b && b <= 7)
                if(board[a][b] == null || getColor() != board[a][b].getColor()) {
                    moves.add(a * 10 + b);
                }
        }
    }
    public class Queen extends Piece {
        Queen(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-Queen";
            }
            else {
                super.name = "b-Queen";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves
            // Left of Queen
            for(int col = getX() - 1; col >= 0; col--) {
                if(board[col][getY()] != null && board[col][getY()].getColor() == getColor()) {
                    break;
                }
                else if (board[col][getY()] != null && board[col][getY()].getColor() != getColor()) {
                    moves.add(col * 10 + getY());
                    break;
                }
                moves.add(col * 10 + getY());
            }
            // Right of Queen
            for(int col = getX() + 1; col < 8; col++) {
                if(board[col][getY()] != null && board[col][getY()].getColor() == getColor()) {
                    break;
                }
                else if (board[col][getY()] != null && board[col][getY()].getColor() != getColor()) {
                    moves.add(col * 10 + getY());
                    break;
                }
                moves.add(col * 10 + getY());
            }
            // Bottom of Queen
            for(int row = getY() - 1; row >= 0; row--) {
                if(board[getX()][row] != null && board[getX()][row].getColor() == getColor()) {
                    break;
                }
                else if (board[getX()][row] != null && board[getX()][row].getColor() != getColor()) {
                    moves.add(getX() * 10 + row);
                    break;
                }
                moves.add(getX() * 10 + row);
            }
            // Top of Queen
            for(int row = getY() + 1; row < 8; row++) {
                if(board[getX()][row] != null && board[getX()][row].getColor() == getColor()) {
                    break;
                }
                else if (board[getX()][row] != null && board[getX()][row].getColor() != getColor()) {
                    moves.add(getX() * 10 + row);
                    break;
                }
                moves.add(getX() * 10 + row);
            }
            // Diagonals
            for(int a = 0; a < 8; a++) {
                queenHelper(getX() + a, getY() + a);
                queenHelper(getX() + a, getY() - a);
                queenHelper(getX() - a, getY() + a);
                queenHelper(getX() - a, getY() - a);
            }


            // Check if move is valid
            return validMoveHelper(x, y);
        }
        public void queenHelper(int a, int b) {
            if(0 <= a && a <= 7 && 0 <= b && b <= 7)
                if(board[a][b] == null || getColor() != board[a][b].getColor()) {
                    moves.add(a * 10 + b);
                }
        }
    }
    public class King extends Piece {
        King(boolean c, int x, int y) {
            super(c, x, y);
            if(!c) {
                super.name = "w-King";
            }
            else {
                super.name = "b-King";
            }
        }
        @Override
        public boolean validMove(int x, int y) {
            // Clear previous moves
            moves.clear();
            // Initialize new possible moves

            kingHelper(getX() + 0, getY() + 1);
            kingHelper(getX() + 0, getY() - 1);
            kingHelper(getX() + 1, getY() + 1);
            kingHelper(getX() + 1, getY() - 1);
            kingHelper(getX() + 1, getY() + 0);
            kingHelper(getX() - 1, getY() + 1);
            kingHelper(getX() - 1, getY() - 1);
            kingHelper(getX() - 1, getY() + 0);

            // Check if move is valid
            return validMoveHelper(x, y);
        }
        public void kingHelper(int a, int b) {
            if(0 <= a && a <= 7 && 0 <= b && b <= 7)
                if(board[a][b] == null || getColor() != board[a][b].getColor()) {
                    moves.add(a * 10 + b);
                }
        }
    }

    // Whos turn is it white is false black is true
    boolean turn = false;
    Scanner in = new Scanner(System.in);
    Piece[][] board = new Piece[8][8]; 

    Chess() {
        initialize();
        while(true) {
            printBoard();
            validMove(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }
    }
    public void start() {};
    public void initialize() {
        board[0][7] = new Rook(true, 0, 7);
        board[1][7] = new Knight(true, 1, 7);
        board[2][7] = new Bishop(true, 2, 7);
        board[3][7] = new Queen(true, 3, 7);
        board[4][7] = new King(true, 4, 7);
        board[5][7] = new Bishop(true, 5, 7);
        board[6][7] = new Knight(true, 6, 7);
        board[7][7] = new Rook(true, 7, 7);

        for(int x = 0; x < 8; x++) {
            board[x][6] = new Pawn(true, x, 6);
        }
        for(int x = 0; x < 8; x++) {
            board[x][1] = new Pawn(false, x, 1);
        }

        board[0][0] = new Rook(false, 0, 0);
        board[1][0] = new Knight(false, 1, 0);
        board[2][0] = new Bishop(false, 2, 0);
        board[3][0] = new Queen(false, 3, 0);
        board[4][0] = new King(false, 4, 0);
        board[5][0] = new Bishop(false, 5, 0);
        board[6][0] = new Knight(false, 6, 0);
        board[7][0] = new Rook(false, 7, 0);
    }
    // sx = Starting x position
    // sy = Starting y position
    // ex = Ending x position
    // ey = Ending y posision
    public void validMove(int sx, int sy, int ex, int ey) {
        // Valid input?
        if(!((1 <= sx && sx <= 8) && (1 <= sy && sy <= 8) && (1 <= ex && ex <= 8) && (1 <= ey && ey <= 8))) {
            return;
        }
        if(board[sx - 1][sy - 1] == null) {
            return;
        }
        else if(turn == board[sx - 1][sy - 1].getColor()) {
            board[sx - 1][sy - 1].validMove(ex - 1, ey - 1);
            if(turn)
                turn = false;
            else if(!turn)
                turn = true;
        }
    }
    public void printBoard() {
        for(int x = 7; x >= 0; x--) {
            for(int y = 0; y < 8; y++) {
                if(board[y][x] == null) {
                    System.out.print("" + board[y][x] + " ");
                }
                else {
                    System.out.print("" + board[y][x].getName() + " ");
                }
            }
            System.out.println();
        }
    }
}
