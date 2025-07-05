import java.awt.AWTException; 
import java.awt.MouseInfo; 
import java.awt.Point; 
import java.awt.Robot; 

public class IntelliJDebugHelper { 
    public static void main(String[] args) { 
        try { 
            // 创建 Robot 对象 
            Robot robot = new Robot(); 
            while (true) { 
                // 每隔 60 秒移动一次鼠标 
                Thread.sleep(1000); 
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