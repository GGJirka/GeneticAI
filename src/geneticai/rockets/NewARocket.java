/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.rockets;

import geneticai.genetics.DNA;
import java.util.Random;

/**
 *
 * @author jirkazbor
 */
public abstract class NewARocket {
    
    public double fitness;
    public int targetX, targetY;
    public double x,y;
    public double velX, velY;
    public DNA[] genes = new DNA[2000];
    public int counter;
    public Random random;
            
    public NewARocket(int targetX, int targetY, double x, double y){
        this.targetX = targetX;
        this.targetY = targetY;
        this.x = x;
        this.y = y;
    }
    
    public abstract void loop(java.awt.Graphics g);
    
    public abstract void createGenes();
    
    public abstract double calculateFitness();
    
    public abstract NewARocket crossover(NewARocket subjectB) throws Exception;
    
    public abstract void mutate();
    
}
