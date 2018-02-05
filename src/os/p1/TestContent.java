/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.p1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evan
 */
public class TestContent {
    private ArrayList<Process> processes = new ArrayList<>();
    
    TestContent(String fStr) {
        try {
            FileReader f = new FileReader("src\\test\\" + fStr);
            Scanner sc = new Scanner(f);
            Process p;
            while (sc.hasNextInt()) {
                p = new Process(sc.nextInt(), sc.nextInt(), sc.nextInt());
                processes.add(p);
            }
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected ArrayList<Process> cloneProcess() { return (ArrayList<Process>) processes.clone(); }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestContent tc = new TestContent("testdata1.txt");
    }
}
