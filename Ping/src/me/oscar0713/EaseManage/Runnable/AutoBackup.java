package me.oscar0713.EaseManage.Runnable;


public class AutoBackup implements Runnable{
	volatile private boolean shutdown = false;
	
	@Override
	public void run() {
		while (!shutdown) {
			
		}

	}
	
	public void deactivate() {
		shutdown = true;
	}

}
