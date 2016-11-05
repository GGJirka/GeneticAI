/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.genetics;

import geneticai.rockets.ARocket;
import geneticai.rockets.Rocket;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author jirkazbor
 */
public class Population {
    
    public ARocket[] population;
    public ArrayList<ARocket> matingPool;
    public Random random;
    public int maxPopulation;
    public Point target;
    public int mutationRate;
    public int generations = 1;
    
    /*
        population - array of ARocket class
        matingPool - arrayList - of ARocket class - used in reproduction
        mutationRate - says what is the % to recreate a new vector of each gene
    */
    public Population(Point target,int maxPopulation,int mutationRate){
        this.target = target;
        this.maxPopulation = maxPopulation; 
        this.mutationRate = mutationRate;
        matingPool = new ArrayList<>();
        population = new ARocket[maxPopulation];
        random = new Random();
    }
    public void createPopulation(){
        for(int i=0;i<population.length;i++){
            population[i] = new Rocket(target,400,550);
        }
    }
    
    public void createGenes(){
        for(int i=0;i<population.length;i++){
            population[i].createGenes();
        }
    }
   
    public void loop(Graphics g){
        for(ARocket r: population){
            r.action();
            r.render(g);
        }
    }
    /*
        this returns value of fitness, fitness is variable which says how good each element has performed
        lower distance to target the higher fitness is. 
    */
    public void calculateFitness(){
        int fitness = 0;
        for(int i=0;i<population.length;i++){
            population[i].bestFitness();            
        }
        System.out.println(fitness/population.length);
    }
    
    public void createMatingPool(){
        for(int i=0;i<population.length;i++){
            for(int j=0;j<population[i].bestFitness();j++){
                matingPool.add(population[i]);
            }
        }  
    } 
    
    public void reproduction(){
        Random rand = new Random();
        for(int i=0;i<population.length;i++){
            ARocket subjectA = this.matingPool.get(rand.nextInt(matingPool.size()));
            ARocket subjectB = this.matingPool.get(rand.nextInt(matingPool.size()));
            System.out.println(subjectA.fitness+"  "+subjectB.fitness);
            ARocket newSubject = subjectA.crossover(subjectA,subjectB);
            //newSubject.mutate(mutationRate);
            population[i] = newSubject;
        }
        this.matingPool.removeAll(matingPool);
        generations++;
    }
}
