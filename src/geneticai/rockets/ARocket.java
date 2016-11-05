/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.rockets;

import geneticai.genetics.DNA;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jirkazbor
 */
public abstract class ARocket {
    
    public double x,y,velX,velY;
    public int fitness;
    public int loop;
    public Point target;
    public Random random = new Random();
    public int lifeTime;
    public ArrayList<DNA> gene = new ArrayList<>();
    public DNA[] genes = new DNA[1000/10];
    public ArrayList<Integer> fitnesss = new ArrayList<>();
    
    public ARocket(Point target, double x, double y) {
        this.target = target;
        this.x = x;
        this.y = y;
    }
    
    public abstract void render(java.awt.Graphics g);
    
    public abstract void action();
    
    public abstract int calculateFitness();
    
    public abstract int bestFitness();
    
    public abstract void createGenes();
    
    public abstract ARocket crossover(ARocket subjectA,ARocket subjectB);
    
    public abstract void mutate(int mutationRate);
    
    public abstract void genes();
    
}
