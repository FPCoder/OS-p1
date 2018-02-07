/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

import java.util.ArrayList;

/**
 *
 * @author Evan
 */
public class OSP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	int numtests = 4;
        ArrayList<Scheduler> s = new ArrayList<>(); // array of all scheduler instances
        
        for (int i = 1; i <= numtests; ++i) {
        	s.add(new FCFS("testdata" + i + ".txt"));
        	s.add(new SJF("testdata" + i + ".txt"));
        	s.add(new RR("testdata" + i + ".txt", 25));
        	s.add(new RR("testdata" + i + ".txt", 50));
        	s.add(new Lottery("testdata" + i + ".txt"));
        }
        for (int i = 0; i < s.size(); ++i) {
        	s.get(i).run();
        }
    }
    
}
