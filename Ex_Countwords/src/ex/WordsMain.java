/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Franz
 */
public class WordsMain 
{
    public static void main(String[] args) 
    {
        JFrame gui = new JFrame();
        gui.setLayout(new GridLayout(2, 1));
        MyQueue<Book> queue = new MyQueue<Book>(3);
        
        ThreadState prd = new ThreadState("WordsProducer");
        gui.add(prd);
        
        ThreadState cons = new ThreadState("WordsConsumer");
        gui.add(cons);
        
        Thread p1 = new Thread(new WordsProducer(queue, prd));
        Thread c1 = new Thread(new WordsConsumer(queue, cons));
        
        p1.start();
        c1.start();
        
        gui.setVisible(true);
        gui.setSize(500, 250);     
    }
}
