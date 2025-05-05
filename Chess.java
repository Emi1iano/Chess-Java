import java.util.Scanner;

public class Chess {

    abstract public class Piece {
        // White is false Black is true
        private boolean color;
        private int x;
        private int y;
        private String name;
        Piece(boolean c, int x, int y) {
            this.color = c;
            this.x = x;
            this.y = y;
        }
        abstract public void validMove();
        public boolean isWhite() {
            return !color;
        }
        public boolean isBlack() {
            return color;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public String getName() {
            return name;
        }
    }
    public class Pawn extends Piece {
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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

            }
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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

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
        public void validMove() {
            if(isWhite()) {

            }
            else if (isBlack()) {

            }
        }
    }

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
        Piece p = board[ex][ey];
        board[ex][ey] = board[sx][sy];
        board[sx][sy] = p;
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
