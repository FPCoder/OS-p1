/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

import java.util.ArrayList;

/**
 *  First Come First Serve
 * A class which reads the given file and schedules the contents in a process
 * queue in the FCFS format. After assembling the queue, the next item in the
 * queue is run to completion, then the next process is popped from the queue.
 * @author Evan
 */
public class FCFS implements Scheduler {
    private ArrayList<Integer> q;
    private ArrayList<Row> rows;
    
    FCFS(TestContent tc) {
        int n = tc.size();
        q = new ArrayList<>(n);
        rows = new ArrayList<>(n);
        
        for (int i = 0; i < n; ++i) {
            q.add(tc.getId(i));
        }
    }
    public void writeRows() {
        
    }
}
