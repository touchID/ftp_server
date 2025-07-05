import java.awt.AWTException; 
import java.awt.MouseInfo; 
import java.awt.Point; 
import java.awt.Robot; 
import java.util.Random; 

public class IntelliJDebugHelper { 
    public static void main(String[] args) { 
        try { 
            // 创建 Robot 对象 
            Robot robot = new Robot(); 
            Random random = new Random(); 
            while (true) { 
                // 生成 60000 到 120000 之间的随机数 
                int randomInterval = random.nextInt(60001) + 60000; 
                Thread.sleep(randomInterval); 
                // 获取当前鼠标位置 
                Point point = MouseInfo.getPointerInfo().getLocation(); 
                // 相对当前位置移动鼠标 1 个像素 
                robot.mouseMove(point.x + 1, point.y); 
            } 
        } catch (AWTException | InterruptedException e) { 
            e.printStackTrace(); 
        } 
    } 
}