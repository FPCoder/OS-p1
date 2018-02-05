/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

/**
 *
 * @author Evan
 */
public class Process {
    protected int pid = -1;
    protected int burstTime = -1;
    protected int priority = -1;
    
    Process(int id, int bt, int p) {
        pid = id;
        burstTime = bt;
        priority = p;
    }
    public int getPID() { return pid; }
    public int getBT() { return burstTime; }
    public int getPriority() { return priority; }
    
    /**
     * Subtract the given amount of burst time, then return based on
     * remaining amount of burst time. Param 'n' is forced to be positive.
     * @param n is amount of burst time being expended
     * @return false if no burst time remaining, true otherwise
     */
    public boolean reduceBT(int n) {
        n = Math.abs(n); // turns n positive if its not already
        
        if (n > burstTime) {
            burstTime = 0;
            return false;
        }
        else {
            burstTime -= n;
            return true;
        }
    }
    
    public String toString() {
    	return pid + "," + burstTime + "," + priority;
    }
    protected Process clone() {
    	return new Process(pid, burstTime, priority);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Process p = new Process(1, 50, 10);
        System.out.println("bt: " + p.getBT());
        p.reduceBT(55);
        System.out.println("reduced: " + p.getBT());
    }
}
