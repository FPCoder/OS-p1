package os.p1;

import java.util.Random;

public class Lottery extends RR {
	private int totalPriority = 0;
	private int tickets = 0;
	private Random r = new Random();

	public Lottery(String str) {
		super(str);
		tq = 50; // set time quantum to 50
    	String fn = str;
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "Lottery-" + fn + ".csv"; // appends time quantum to RR
	}
	@Override
	public Process nextProcess() {
		if (!q.isEmpty()) {
			Process p = null;
			int i = 0;
			totalPriority = 0; // reset the total priority
			tickets = 0; // reset number of tickets left
			for (i = 0; i < q.size(); ++i) { // determine total priority
				totalPriority += q.get(i).getPriority();
			}
			int num = r.nextInt(totalPriority - 1); // range of 0-(totalPriority-1)
			num += 1; // range is now 1-totalPriority
			for (i = 0; tickets < num; ++i) {
				p = q.get(i);
				tickets += p.getPriority();
			}
			return p;
		}
		else {
			return null;
		}
	}

	public static void main(String[] args) {
		new Lottery("testdata1.txt").run();
	}

}
