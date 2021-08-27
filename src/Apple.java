import javax.swing.*;
import java.awt.*;

public class Apple extends GameObject {

    public int posX;
    public int posY;
    public boolean isAlive = true;// состояние - жив
    Image img = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\appl.red.png").getImage();

    public Apple(int x, int y) {
        super(x, y);
        posX = x;
        posY = y;
    }

    public void setRandomPosition() {
        posX = Math.abs((int)(Math.random() * SnakeGame.WIDTH - 1));
        posY = Math.abs((int)(Math.random() * SnakeGame.HEIGHT - 1));
    }
}
