import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class SnakeGame extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static int speed = 10;

    Timer timer = new Timer(1000 / speed, this);

    Apple apple = new Apple(Math.abs((int)(Math.random() * SnakeGame.WIDTH - 1)),Math.abs((int)(Math.random() * SnakeGame.HEIGHT - 1)));
    Snake snake = new Snake(7, 8);

    public SnakeGame() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }
    
    public class KeyBoard extends KeyAdapter {

        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();

            if (key == KeyEvent.VK_UP) snake.setDirection(Direction.UP);
            if (key == KeyEvent.VK_DOWN) snake.setDirection(Direction.DOWN);
            if (key == KeyEvent.VK_LEFT) snake.setDirection(Direction.LEFT);
            if (key == KeyEvent.VK_RIGHT) snake.setDirection(Direction.RIGHT);
        }
    }


    public void paint(Graphics g) {
        g.setColor(Color.BLACK); //задаем цвет фона
        g.fillRect(0,0, WIDTH * SCALE, HEIGHT * SCALE); //закрашиваем фон

        for(int x = 0; x < WIDTH * SCALE; x+= SCALE) { //линии по оси х
            g.setColor(Color.darkGray);
            g.drawLine(x, 0, x, HEIGHT * SCALE);
        }

        for(int y = 0; y < HEIGHT * SCALE; y+= SCALE) { //линии по оси у
            g.setColor(Color.darkGray);
            g.drawLine(0, y, WIDTH * SCALE, y);
        }

        g.drawImage(apple.img,apple.posX * SCALE + 1,apple.posY * SCALE + 1,SCALE - 1, SCALE - 1,null);


        for(int l = 0; l < snake.snakeParts.size(); l++) {



            g.drawImage(snake.head_up, snake.snakeParts.get(0).x * SCALE, snake.snakeParts.get(0).y * SCALE, SCALE, SCALE, null); //голова
            g.drawImage(snake.body, snake.snakeParts.get(l).x * SCALE, snake.snakeParts.get(l).y * SCALE, SCALE, SCALE, null); //тело
            //g.drawImage(snake.tail_up, snake.snakeParts.get(l + 1).x * SCALE, snake.snakeParts.get(l + 1).y * SCALE, SCALE, SCALE, null); //тело
        }
        
    }

    private void createNewApple() { //создание яблока в рандомном месте поля
        Apple newApple;
        do {
            int x = Math.abs((int)(Math.random() * SnakeGame.WIDTH - 1));
            int y = Math.abs((int)(Math.random() * SnakeGame.HEIGHT - 1));
            newApple = new Apple(x, y);
        } while (snake.checkCollision(newApple)); //яблоко не должно создаваться на змейке
        apple = newApple;
    }
    

    public static void main(String[] args) {
        jFrame = new JFrame("Snake 3"); // название окна
        jFrame.add(new SnakeGame()); //открытие окна
        jFrame.setSize(WIDTH * SCALE + 8, HEIGHT * SCALE + 30); //размеры
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрытие
        jFrame.setVisible(true); //отображение окна
        jFrame.setResizable(false); //растягивание окна
        jFrame.setLocationRelativeTo(null); // расположение по середине
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\icon snake.jpg")); //иконка
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      snake.move(apple);
      if((snake.snakeParts.get(0).x == apple.posX) && (snake.snakeParts.get(0).y == apple.posY)) {
          apple.setRandomPosition();
          createNewApple();
      }
      repaint();
    }



}
