package os.p1;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Priority implements Scheduler {
	private PriorityQueue<Process> q;
	private ArrayList<Row> rows;
	private String fileName;
	private String outputFileName;

	Priority(String str) {
    	String fn = str;
    	
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "FCFS-" + fn + ".csv";
    	q = new PriorityQueue<>();
    	rows = new ArrayList<>();
	}

	@Override
	public void fillQueue(TestContent tc) {
		q = new PriorityQueue<Process>(new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {
				return o1.priority - o2.priority;
			}
			
		});
		q.addAll(tc.cloneProcess());
		System.out.println(q.toString());
	}

	@Override
	public boolean hasNextProcess() {
		if (!q.isEmpty()) { return true; }
		else { return false; }
	}
	@Override
	public Process nextProcess() {
		if (!q.isEmpty()) {
			// TODO: remove the next process and return
			return null;
		}
		else {
			return null;
		}
	}

	@Override
	public Row calcRow(Process p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void outputToFile(String str) throws FileNotFoundException {
		try {
			PrintWriter pw;
			
			/* opens the file to write to, creating a new one if it doesn't exist, 
			 * and overwriting the old if it does */
			pw = new PrintWriter(Files.newOutputStream(Paths.get(outputFileName), CREATE, TRUNCATE_EXISTING));
			StringBuilder sb = new StringBuilder();
			String output;
			
			sb.append(colLabels);
			for (int i = 0; i < rows.size(); ++i) {
				sb.append(rows.get(i));
			}
			output = sb.toString();
			System.out.println(output);
			pw.write(output);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new Priority("testdata1.txt").fillQueue(new TestContent("testdata1.txt"));
	}

}
