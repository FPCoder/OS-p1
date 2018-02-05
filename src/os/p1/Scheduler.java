/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

import java.io.FileNotFoundException;

/**
 *
 * @author Evan
 */
public interface Scheduler {
	final static String colLabels = "CpuTime,PID,StartingBurstTime,EndingBurstTime,CompletionTime\n";
    
    /**
     * Method which parses the given TestContent object and puts the respective
     * parts into the private queue. Should store a clone of tc (which is 
     * included in TestContent.
     * @param tc the contents of the given test.txt file.
     */
    void fillQueue(TestContent tc);
    
    /**
     * Determine if there is another process to examine.
     * @return true if there are more processes, false otherwise
     */
    boolean hasNextProcess();
    
    /**
     * Get next process, which is Scheduler specific.
     * @return next process in the Scheduler queue.
     */
    Process nextProcess();
    
    /**
     * Calculate each column value for the given process,
     * removing the process from the queue after completion.
     * @param p the process for which the row is relating
     * @return a new Row object with all the columns filled
     */
    Row calcRow(Process p);
    
    /**
     * Save the output to the given .csv file.
     * @param str name of the file
     */
    void outputToFile(String str) throws FileNotFoundException;
    
    /**
     * Run the scheduler until the queue is empty.
     */
    void run();
}







