/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *  First Come First Serve
 * A class which reads the given file and schedules the contents in a process
 * queue in the FCFS format. After assembling the queue, the next item in the
 * queue is run to completion, then the next process is popped from the queue.
 * @author Evan
 */
public class FCFS implements Scheduler {
    protected ArrayList<Process> q;
    protected ArrayList<Row> rows;
    protected int cpu = 0;
    protected String fileName;
    protected String outputFileName;
    
    FCFS(String str) {
    	String fn = str;
    	fileName = str;
		if (fn.indexOf(".") > 0)
			fn = fn.substring(0, fn.lastIndexOf("."));
		outputFileName = "FCFS-" + fn + ".csv";
    	q = new ArrayList<>();
    	rows = new ArrayList<>();
    }
    
	@Override
	public void fillQueue(TestContent tc) {
        q = tc.cloneProcess();
        // do nothing else, q has same order as the file input.
	}

	@Override
	public boolean hasNextProcess() {
		if (!q.isEmpty()) { return true; }
		else { return false; }
	}
	@Override
	public Process nextProcess() {
		if (!q.isEmpty()) {
			return q.get(0); // get the next process, which is always the head.
		}
		else {
			return null;
		}
	}
	@Override
	public Row calcRow(Process p) {
		if (p != null) {
			Row r = new Row();
			
			r.cpu(cpu);					// set cpuTime column
			r.pid(p.pid);				// set the processID
			r.sbt(p.burstTime);			// set the StartBT to the processBT
			r.ebt(0);					// endingBt will always be 0
			r.ct(cpu + p.burstTime);	// completionTime is start + burstTime
			
			cpu += p.burstTime;	// update cpuTime
			cpu += 3; 			// 3 is the process switching time quantum
			
			return r;
		}
		else {
			return null;
		}
	}
	@Override
	public void outputToFile(String str) throws FileNotFoundException {		
		PrintWriter pw = new PrintWriter(new File(outputFileName));
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
	}

	@Override
	public void run() {
		TestContent tc = new TestContent(fileName);
		fillQueue(tc);
		
		/* while there are more processes, calculate the next row values and add it
		*  to the output array.
		*/
		while (hasNextProcess()) {
			rows.add(calcRow(nextProcess()));
			q.remove(0);
		}
		try {
			outputToFile(outputFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FCFS sch = new FCFS("testdata1.txt");
        sch.run();
    }
}
