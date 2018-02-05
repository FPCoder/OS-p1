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
public class Row {
    private int cpuTime = -1;
    private int pid = -1;
    private int startBT = -1;
    private int endBT = -1;
    private int completionTime = -1;
    
    Row() {
    	
    }
    Row(int cput, int id, int sbt, int ebt, int ct) {
        cpuTime = cput;
        pid = id;
        startBT = sbt;
        endBT = ebt;
        completionTime = ct;
    }
    
    public void cpu(int i) { cpuTime = i; }
    public void pid(int i) { pid = i; }
    public void sbt(int i) { startBT = i; }
    public void ebt(int i) { endBT = i; }
    public void ct(int i) { completionTime = i; }
    
    @Override
    public String toString() {
        return cpuTime + "," +
                pid + "," +
                startBT + "," +
                endBT + "," +
                completionTime + "\n";
    }
}
