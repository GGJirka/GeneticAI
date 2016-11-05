/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.rockets;

import geneticai.genetics.DNA;
import java.awt.*;

/**
 *
 * @author jirkazbor
 */
public class Rocket extends ARocket {
    
    public Rocket(Point target, double x, double y) {
        super(target, x, y);
    }

    @Override
    public void createGenes(){
        for (int i = 0; i < genes.length; i++) {
            double dirX = Math.random() * 2.0 * Math.PI;
            double dirY = Math.random() * 2.0 * Math.PI;
            genes[i] = new DNA(dirX, dirY);
        }
        
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, 20, 20);
        g.setColor(Color.RED);
        g.drawString(""+calculateFitness(),(int)x+10,(int)y-5);
    }

    @Override
    public void action(){
        if(loop%10==0){           
            velX = this.genes[lifeTime].genes[0];
            velY = this.genes[lifeTime].genes[1];
            lifeTime++;
        }
        this.fitnesss.add(calculateFitness());
        x+=velX;
        y+=velY;
        loop++;
    }
    
    @Override
    public void genes(){
        for(DNA dna : genes){
            System.out.println(dna.genes[0]+"  "+dna.genes[1]);
        }
    }
    
    /*
        first i calculate distance - pythagor
        i make fitness exponential so there is high difference beetwen each element    
    */
    
    @Override
    public int calculateFitness(){
        double fx = target.x - x;
        double fy = target.y - y;
        double distance = Math.sqrt(fx * fx + fy * fy);
        
        this.fitness = (int) Math.round((1 / (distance))*10000);
        this.fitness = (int) Math.pow(this.fitness,6);
        this.fitness /= 1000000;
        return this.fitness;
    }
    /*
        crossover = take half genes of first subject and half genes 
        of second subject then mix it into new subject   (i mean it´s not half it´s random number)
    */
    @Override
    public ARocket crossover(ARocket subjectA,ARocket subjectB){
        Rocket child = new Rocket(target, 400, 550);  
        
        int midPoint = random.nextInt(genes.length);
        
        for(int i=0;i<this.genes.length;i++){          
            if(i<midPoint){
                child.genes[i] = subjectA.genes[i];
            }else{
                child.genes[i] = subjectB.genes[i];
            }
        }
        
        return child;
    }

    @Override
    public void mutate(int mutationRate) {
        for(int i=0;i<this.genes.length;i++){
            if(random.nextInt(100)>0 && random.nextInt(100)<=mutationRate){
                this.genes[i].genes[0] = Math.random()*2.0*Math.PI;
                this.genes[i].genes[1] = Math.random()*2.0*Math.PI;
            } 
        }
    }
    //returns average fitness - works better
    @Override
    public int bestFitness() {
        int averageFitness = 0;
        for(int i=0;i<fitnesss.size();i++){
            averageFitness+=fitnesss.get(i);
        }
        if(new Rectangle((int) this.x, (int) this.y,20,20).intersects(new Rectangle((int) this.target.x,this.target.y,30,30))){
            averageFitness*=2;
        }
        return averageFitness/fitnesss.size();
    }
}
