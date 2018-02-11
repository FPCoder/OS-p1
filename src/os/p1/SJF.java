package os.p1;

import java.util.Comparator;

/**
 * Shortest Job First Scheduler
 * Sorts the processes entered to handle the shortest jobs first.
 * @author ModernCyborg
 *
 */
public class SJF extends FCFS {
	
	SJF(String str) {
		super(str);
		outputFileName = "SJF-" + fn + ".csv";
	}

	@Override
	public void fillQueue(TestContent tc) {
		q = tc.cloneProcess();
		q.sort(new Comparator<Process>() {
			@Override
			public int compare(Process arg0, Process arg1) {
				return arg0.burstTime - arg1.burstTime;
			}
		});
	}

	public static void main(String[] args) {
		SJF sch = new SJF("testdata1.txt");
		sch.run();
        System.out.println("Average CT: " + sch.getAvgCT());
	}

}
