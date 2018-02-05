package os.p1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Shortest Job First Scheduler
 * Sorts the processes entered to handle the shortest jobs first.
 * @author ModernCyborg
 *
 */
public class SJF extends FCFS {
	
	SJF(String str) {
		super(str);
    	String fn = str;
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "SJF-" + fn + ".csv";
	}

	@Override
	public void fillQueue(TestContent tc) {
		q = tc.cloneProcess();
		q.sort(new ProcessComparator());
	}

	public static void main(String[] args) {
		SJF sch = new SJF("testdata1.txt");
		sch.run();
	}

}
