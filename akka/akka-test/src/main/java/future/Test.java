package future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
	
	public Test(){
		ExecutorService singleton = Executors.newSingleThreadExecutor();
		ExecutorService another = Executors.newFixedThreadPool(1);
		singleton.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		another.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	
	
	
	public static void main(String[] args){
		Test test = new Test();
		
		try {
			TimeUnit.MINUTES.sleep(2);
		} catch (InterruptedException e) {
			System.out.println("Interruped!!!!!");
			e.printStackTrace();
		}
	}

}
