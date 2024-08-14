package sub3;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

// 컨테이너(WAS)의 시작과 종료를 감지하는 리스너
public class MyContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 서버가 시작될 때
		System.out.println("contextInitialized...");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 서버가 종료될 때
		System.out.println("contextDestroyed...");
	}
	
}
