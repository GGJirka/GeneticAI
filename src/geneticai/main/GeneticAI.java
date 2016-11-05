/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.main;

import geneticai.genetics.Population;
import geneticai.frame.GeneticFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

/**
 *
 * @author jirkazbor
 */
public class GeneticAI extends Canvas implements Runnable {
    
    public Thread thread;
    public Population population;
    public int lifeTime = 1000,fps = 0,fpsToString = 0;
    public long timer = System.currentTimeMillis();
    /**
     * @param args the command line arguments
     * main class
     */
    
    public static void main(String[] args) {
        /*
            starting Frame class
        */
        GeneticFrame frame = new GeneticFrame(new GeneticAI());
    }
    /*
        starting thread
    */
    public void start(){
        thread = new Thread(this);
        thread.start();
    }
    /*
        main constructor, creating population (target, population number, mutation rate)
        of 50 elements, creating genes - create array of 1000 (individual) random vectors
    */
    public GeneticAI(){
        population = new Population(new Point(950,550),50,1);
        population.createPopulation();
        population.createGenes();
    }
    /*
        new Thread activated 
    */
    @Override
    public void run(){
        while(thread.isAlive()){
            render();
            if(this.lifeTime==0){
                this.population.calculateFitness();
                this.population.createMatingPool();
                this.population.reproduction();                
                this.lifeTime = 1000;
            }
            this.lifeTime--;
        }
    }
    /*
        render method - used for Graphic
    */
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1800,900);
        g.setColor(Color.BLACK);
        this.fps++;
        
        if (System.currentTimeMillis() - timer >= 1000){
            fpsToString = fps;
            timer += 1000;
            fps = 0; 
        }      
        g.drawString("Generation: "+this.population.generations,10,15);
        g.drawString("Numer of population: "+this.population.maxPopulation,10,30);
        g.drawString("Mutation rate: "+this.population.mutationRate+"%",10,45);
        g.drawString("FPS:"+fpsToString,10,60);
        g.setColor(Color.BLUE);
        g.fillRect(950,550,30,30);
        this.population.loop(g);
        g.dispose();
        bs.show();
    }
    
}
