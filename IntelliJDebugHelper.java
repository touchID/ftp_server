import java.awt.AWTException; 
import java.awt.MouseInfo; 
import java.awt.Point; 
import java.awt.Robot; 
import java.util.Random; 
import java.time.LocalTime; 
import java.time.ZoneId; 
import java.time.ZonedDateTime; 

public class IntelliJDebugHelper { 
    public static void main(String[] args) { 
        try { 
            // 创建 Robot 对象 
            Robot robot = new Robot(); 
            Random random = new Random(); 
            while (true) { 
                // 获取北京时间 
                ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")); 
                LocalTime now = zonedDateTime.toLocalTime(); 
                // 判断是否在 12 点 - 13 点 或 18 点之后 
                if ((now.isAfter(LocalTime.of(12, 0)) && now.isBefore(LocalTime.of(13, 0))) || now.isAfter(LocalTime.of(18, 0))) { 
                    Thread.sleep(60000); // 处于停止时间段，每分钟检查一次时间 
                    continue; 
                } 
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