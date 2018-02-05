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
public interface Scheduler {
    void fillQueue();
    Process nextProcess();
    void calcRowTimes(int i);
    void outputToFile(String str);
}
