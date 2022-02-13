package me.oscar0713.EaseManage.Utilities;

public class TickCalculation implements Runnable{
	public static int TICK_COUNT= 0;
	public static long[] TICKS= new long[600];
	public static long LAST_TICK= 0L;

	public static double getTPS()
	{
	  return getTPS(100);
	}

	public static double getTPS(int ticks)
	{
	  if (TICK_COUNT< ticks) {
	    return 20.0D;
	  }
	  int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
	  long elapsed = System.currentTimeMillis() - TICKS[target];

	  return ticks / (elapsed / 1000.0D);
	}

	public static long getElapsed(int tickID)
	{
	  if (TICK_COUNT- tickID >= TICKS.length)
	  {
	  }

	  long time = TICKS[(tickID % TICKS.length)];
	  return System.currentTimeMillis() - time;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		  TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();

		  TICK_COUNT+= 1;
	}

}
