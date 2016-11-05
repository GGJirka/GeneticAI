/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.frame;

import geneticai.main.GeneticAI;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author jirkazbor
 */
public class GeneticFrame {
    
    public GeneticAI main;
    
    public GeneticFrame(GeneticAI main){
        this.main = main;
        JFrame frame = new JFrame("Genetic Algortihm AI 1.0");
        frame.setSize(1080, 640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(main);
        main.start();
    }
}
