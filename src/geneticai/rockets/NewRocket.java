/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.rockets;

import geneticai.genetics.DNA;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jirkazbor
 */
public class NewRocket extends NewARocket {

    public NewRocket(int targetX, int targetY, double x, double y) {
        super(targetX, targetY, x, y);
    }

    @Override
    public void createGenes() {
        for(int i=0;i<this.genes.length;i++){
            //this.genes[i] = new DNA(Math.random()*2.0*Math.PI);
        }
    }
    
    @Override
    public void loop(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, 20, 20);
        g.setColor(Color.RED);
        g.drawString(""+calculateFitness(),(int)x+10,(int)y-5);
        velX = this.genes[this.counter].genes[0];
        velY = this.genes[this.counter].genes[1];
        x+=velX;
        y+=velY;
        counter++;
    }
    
    @Override
    public double calculateFitness(){
        double fx = this.targetX - this.x;
        double fy = this.targetY - this.y;
        double distance = (int) Math.sqrt(fx*fx+fy*fy);
        this.fitness = distance;
        return (int)Math.round(Math.pow((1/fitness)*1000,4));
    }

    @Override
    public NewARocket crossover(NewARocket subjectB) throws Exception{
        NewARocket rocket = new NewRocket(this.targetX, this.targetY, this.x, this.y);       
        int midpoint = random.nextInt(genes.length-1);            
        for(int i=0;i<this.genes.length;i++){
            if(i<=midpoint){
                rocket.genes = this.genes;
            }else{
                rocket.genes = subjectB.genes;
            }
        }
        return rocket;
    }

    @Override
    public void mutate(){
        
    }

    
    
}
