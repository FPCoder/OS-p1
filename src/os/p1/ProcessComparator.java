package os.p1;

import java.util.Comparator;

public class ProcessComparator implements Comparator<Process> {

	public static void main(String[] args) {
		Process p1 = new Process(1, 50, 1);
		Process p2 = new Process(2, 100, 1);
		System.out.println(new ProcessComparator().compare(p1, p2));
	}

	@Override
	public int compare(Process arg0, Process arg1) {
		return arg0.burstTime - arg1.burstTime;
	}

}
