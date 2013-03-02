/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ant;

import javax.media.opengl.GL2;

/**
 * Class to handle materials
 * @author Baardsen
 */
public class Material {

    public static void brown(GL2 gl){
        float amb[]={0.09f,0.0f,0.0f,1.0f};
        float diff[]={0.36f,0.08f,0.0f,1.0f};
        float spec[]={0.58f,0.727811f,0.633f,1.0f};
        float shine=76.8f;
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,amb,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_DIFFUSE,diff,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_SPECULAR,spec,0);
        gl.glMaterialf(gl.GL_FRONT_AND_BACK,gl.GL_SHININESS,shine);
    }

    public static void redBrown(GL2 gl){
        float amb[]={0.08f,0.0f,0.0f,1.0f};
        float diff[]={0.29f,0.1f,0.0f,1.0f};
        float spec[]={0.58f,0.727811f,0.633f,1.0f};
        float shine=76.8f;
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,amb,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_DIFFUSE,diff,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_SPECULAR,spec,0);
        gl.glMaterialf(gl.GL_FRONT_AND_BACK,gl.GL_SHININESS,shine);
    }

    public static void black(GL2 gl){
        //black plastic
        float amb[]={0.0f, 0.0f, 0.0f, 1.0f};
        float diff[]={0.01f, 0.01f, 0.01f, 1.0f};
        float spec[]={0.50f, 0.50f, 0.50f, 1.0f };
        float shine=32.0f;
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_AMBIENT,amb,0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE,diff,0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_SPECULAR,spec,0);
        gl.glMaterialf(GL2.GL_FRONT_AND_BACK,GL2.GL_SHININESS,shine);
    }
}
