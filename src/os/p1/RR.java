package os.p1;

import java.io.FileNotFoundException;

public class RR extends FCFS {
	private int tq = 25;

	RR(String str) {
		super(str);
    	String fn = str;
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "RR25-" + fn + ".csv";	// RR25 is the default
	}
	
	/**
	 * 
	 * @param str string of the file to read data from
	 * @param t can override the Time_Quantum if necessary (25 is the default value)
	 */
	RR(String str, int t) {
		super(str);
		tq = t; // update time_quantum to parameter
    	String fn = str;
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "RR" + t + '-' + fn + ".csv"; // appends time quantum to RR
	}
	
	@Override
	public Row calcRow(Process p) {
		if (p != null) {
			Row r = new Row();
			// if process completes now, time is burst, otherwise is tq
			int time = (p.burstTime < tq) ? p.burstTime : tq;
			
			r.cpu(cpu);					// set cpuTime column
			r.pid(p.pid);				// set the processID
			r.sbt(p.burstTime);			// set the StartBT to the processBT
			p.reduceBT(tq);
			r.ebt(p.burstTime);			// set EndBT to the time remaining
			if (p.burstTime > 0) {
				r.ct(0);				// branch when process is not completed
			}
			else {
				r.ct(cpu + time);
			}
			
			cpu += time;	// update cpuTime
			if (q.size() > 1) { // will not switch if there is only one process left
				cpu += 3; 		// 3 is the process switching time quantum
			}
			
			return r;
		}
		else {
			return null;
		}
	}
	@Override
	public void run() {
		Process p;
		TestContent tc = new TestContent(fileName);
		fillQueue(tc);
		
		/* while there are more processes, calculate the next row values and add it
		*  to the output array.
		*/
		while (hasNextProcess()) {
			p = nextProcess();
			rows.add(calcRow(p));
			if (p.burstTime <= 0) {
				q.remove(0); // if process done, remove it
			}
			else {
				q.add(q.remove(0)); // otherwise, move it to the back
			}
		}
		try {
			outputToFile(outputFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RR r = new RR("testdata1.txt", 50);
		r.run();
	}

}
