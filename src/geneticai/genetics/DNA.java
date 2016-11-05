/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticai.genetics;



/**
 *
 * @author jirkazbor
 */

public class DNA implements IDNA{
    
    public double[] genes = new double[2];
    
    public DNA(double dirX,double dirY){
        genes[0] = Math.cos(dirX);
        genes[1] = Math.sin(dirY);
    }

    @Override
    public void mutate() {
        this.genes[0] = Math.cos(Math.random()*2.0*Math.PI);
        this.genes[1] = Math.sin(Math.random()*2.0*Math.PI);
    }
    
}
