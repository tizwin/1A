import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public ArrayList<GameObject> snakeParts = new ArrayList<>();

    public int length = 10;
    private Direction direction = Direction.LEFT;
    

    public Snake(int x,int y) {
        GameObject first = new GameObject(x, y);
        GameObject second = new GameObject(x + 1, y);
        GameObject third = new GameObject(x + 2, y);
        
        snakeParts.add(first);
        snakeParts.add(second);
        snakeParts.add(third);
    }

    public boolean isAlive = true;

    Image head_left = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_head.png").getImage();
    Image head_right = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_head_right.png").getImage();
    Image head_up = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_head_up.png").getImage();
    Image head_down = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_tail_down.png").getImage();
    Image body = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_body.png").getImage();
    Image tail_left = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_tail.png").getImage();
    Image tail_right = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_tail_right.png").getImage();
    Image tail_up = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_tail_up.png").getImage();
    Image tail_down = new ImageIcon("C:\\Users\\glush\\Desktop\\Java\\Snake 3\\src\\snake_tail_down.png").getImage();


    public void move(Apple apple) {
        GameObject newHead = createNewHead();

        if((newHead.x < 0 || newHead.x >= SnakeGame.WIDTH) || (newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT)) {
            this.isAlive = false; //не выходить за рамки игрового поля
        }
        if(checkCollision(newHead)) { //столкновение змеи со своим телом
            isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        if(newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false; //если змея совпадает с яблоком, оно исчезает
        } else {
            removeTail();
        }
        }

    public void removeTail() { //Метод (хвост) должен удалять последний элемент из списка snakeParts.
        snakeParts.remove(snakeParts.size() - 1);
    }

    public GameObject createNewHead() { //управление
        GameObject gameObject = null;
      if(direction == Direction.UP) {
          gameObject = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
      }
      if(direction == Direction.DOWN) {
          gameObject = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
      }
      if(direction == Direction.RIGHT) {
          gameObject = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
      }
      if(direction == Direction.LEFT) {
          gameObject = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
      }
        return gameObject;
    }

    public boolean setDirection(Direction direction) { //запрет движения в противоположную сторону
        if(this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y) {
            return false;
        }
        if(this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y) {
            return false;
        }
        if(this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x) {
            return false;
        }
        if(this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x) {
            return false;
        }
        this.direction = direction;
        return false;
    }

    public boolean checkCollision(GameObject object) { //совпадение змеи с ее телом
        for(GameObject obj : snakeParts) {
            if(object.x == obj.x && object.y == obj.y) {
                return true;
            }
        }
        return false;
    }
    
}
