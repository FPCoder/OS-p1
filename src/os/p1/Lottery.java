package os.p1;

public class Lottery extends RR {
	private int totalPriority = 0;
	private int tickets = 0;

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
			Process p;
			totalPriority = 0; // reset the total priority
			tickets = 0; // reset number of tickets left
			for (int i = 0; i < q.size(); ++i) { // determine total priority
				totalPriority += q.get(i).priority;
			}
			do { // until priority is reached, select the next node
				p = q.remove(q.size()-1); // cycle last to front
				q.add(0, p);
			} while (tickets < totalPriority);
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
