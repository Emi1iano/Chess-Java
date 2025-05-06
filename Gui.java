import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Gui extends Chess {

    private class Tile extends JButton {
        int x;
        int y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Clicked {
        int sx = -1;
        int sy = -1;
        int ex = -1;
        int ey = -1;

        public void click(int x, int y) {
            // No first click
            if (sx == -1) {
                // The first click was an empty space
                if (board[x][y] == null) {
                    sx = -1;
                    sy = -1;
                    return;
                }
                // If you clicked the other teams piece first
                if (turn != board[x][y].getColor()) {
                    return;
                }
                // The first click is valid
                else {
                    sx = x;
                    sy = y;
                    System.out.println("first click");
                }
            }
            // Clicked empty
            else if (board[x][y] == null) {
                ex = x;
                ey = y;
            }
            // Clicked the same color
            else if (board[x][y].getColor() == board[sx][sy].getColor()) {
                sx = x;
                sy = y;
            }
            // Second click is valid (Maybe)
            else {
                ex = x;
                ey = y;
            }

            if ((sx != -1) && (ex != -1)) {
                System.out.println("Second click");
                boolean initialTurn = turn;
                validMove(sx + 1, sy + 1, ex + 1, ey + 1);
                if (initialTurn != turn) {
                    updateBoard();
                }
                sx = -1;
                sy = -1;
                ex = -1;
                ey = -1;
            }

        }
    }

    JFrame frame = new JFrame();
    Tile[][] buttons = new Tile[8][8];

    HashMap<String, ImageIcon> Images = new HashMap<>();

    // ImageIcon bPawnImg = new ImageIcon("b-pawn.png");
    // Image scaledBPawn = bPawnImg.getImage().getScaledInstance(100, 100,
    // Image.SCALE_SMOOTH);
    // ImageIcon bPawn = new ImageIcon(scaledBPawn);

    Gui() {
        Clicked clicked = new Clicked();
        setImages();
        // Make GUI with buttons and pieces
        for (int x = 7; x >= 0; x--) {
            for (int y = 0; y < 8; y++) {
                Tile button = new Tile(x, y);
                buttons[y][x] = button;
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                if (board[y][x] != null) {
                    buttons[y][x].setIcon(Images.get(board[y][x].getName()));
                }
                frame.add(button);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Tile tile = (Tile) e.getSource();

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            // System.out.println(tile.y + 1);
                            // System.out.println(tile.x + 1);
                            clicked.click(tile.y, tile.x);
                            System.out.println("clicked!");
                        }
                    }
                });
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setTitle("MEAnChess.com");
        frame.setLayout(new GridLayout(8, 8));
        colorBoard();
        frame.setVisible(true);

    }

    public void setImagesHelper(String name) {
        ImageIcon pieceImage = new ImageIcon("Images/" + name + ".png");
        Image scaledImage = pieceImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon piece = new ImageIcon(scaledImage);
        Images.put(name, piece);
    }

    public void setImages() {
        setImagesHelper("b-Pawn");
        setImagesHelper("w-Pawn");
        setImagesHelper("b-Rook");
        setImagesHelper("w-Rook");
        setImagesHelper("b-Knight");
        setImagesHelper("w-Knight");
        setImagesHelper("b-Bishop");
        setImagesHelper("w-Bishop");
        setImagesHelper("b-Queen");
        setImagesHelper("w-Queen");
        setImagesHelper("b-King");
        setImagesHelper("w-King");
    }

    public void updateBoard() {
        for (int x = 7; x >= 0; x--) {
            for (int y = 0; y < 8; y++) {
                if (board[y][x] != null) {
                    buttons[y][x].setIcon(Images.get(board[y][x].getName()));
                } else {
                    buttons[y][x].setIcon(null);
                }
            }
        }
    }

    public void colorBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    buttons[row][col].setBorder(new LineBorder(new Color(0, 0, 0), 3));
                    if (col % 2 == 0)
                        buttons[row][col].setBackground(new Color(150, 75, 0));
                    else
                        buttons[row][col].setBackground(new Color(196, 164, 132));
                } else {
                    buttons[row][col].setBorder(new LineBorder(new Color(0, 0, 0), 3));
                    if (col % 2 == 1)
                        buttons[row][col].setBackground(new Color(150, 75, 0));
                    else
                        buttons[row][col].setBackground(new Color(196, 164, 132));
                }
            }
        }
    }
}