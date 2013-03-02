/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ant;


import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Draws an ant in jogl
 * @author Baardsen
 */
public class Ant {
    private static int SLICES = 15;
    private static int STACKS = 15;

    private static float FEET_ANGLE_RADIUS = 0.5F;
    private static float BACK_HIP_LENGTH = 2.0F;
    private static float BACK_LEG_LENGTH = 2.0F;
    private static float BACK_HIP_RADIUS = 0.3F;
    private static float BACK_LEG_RADIUS = 0.2F;

    private static float NECK_RADIUS = 0.5F;
    private static float NECK_LENGTH = 0.7F;
    
    private static float HORN_RADIUS = 0.1F;
    private static float HORN_LENGTH = 0.5F;

    private static float CLUB_RADIUS = 0.1f;
    private static float CLUB_LENGTH = 2.3F;

    private static float EYE_RADIUS = 0.5f;

    //animation properties

    private static float LEG_SWING = 0.0F;
    private static float LEG_DELTA = 0.5F;
    private static float LEG_MAX = 4.0F;

    private static float KNEE_SWING = 0.0F;
    private static float KNEE_DELTA = 0.8F;
    private static float KNEE_MAX = 15.0F;


    private static float GASTER_SWING = 0.0F;
    private static float GASTER_DELTA = 0.3F;
    private static float MAX_GASTER_SWING = 18.0F;

    private static float HORN_SWING = 0.0F;
    private static float HORN_DELTA = 0.7F;
    private static float HORN_MAX_SWING = 12.0F;

    private static float CLUB_UPPER_SWING = 0.0F;
    private static float CLUB_LOWER_SWING = 0.0F;
    private static float CLUB_DELTA_LOWER = 0.2F;
    private static float CLUB_DELTA_UPPER = 0.5F;
    private static float CLUB_MAX_SWING = 15.0F;

    private static float NECK_SWING = 0.0F;
    private static float NECK_DELTA_SWING = 0.3F;
    private static float NECK_MAX_SWING = 18.0F;

    private static boolean SILHOUETTE = false;

    //enable silhouette rendering
    public void enableSilhouette(){
        SILHOUETTE = true;
    }

    public void disableSilhouette(){
        SILHOUETTE = false;
    }

    // http://www.allpestexpress.com/ants-ant-information.aspx#Acrobat-Ant
    //
    public void draw(GL2 gl2){
        GLU glu=new GLU();
        GLUquadric q=glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);

        //enable silhouette
        if(SILHOUETTE){ glu.gluQuadricDrawStyle(q, GLU.GLU_SILHOUETTE);}

        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);

        

        /////////////////////////////////////////////////////BODY///////////////////////////////////////////////
        //draw body
        
       
        Material.brown(gl2);
        gl2.glTranslatef(-5,0,0);
          gl2.glPushMatrix();
       
        //Gaster
        
        gl2.glRotatef(25,0,0,1);
        gl2.glRotatef(GASTER_SWING, 1,0,0);

        superEllipsoid(gl2, 1.5, 1.0, 1.5, 30, 2.5, 2.5);

        gl2.glPopMatrix();
        gl2.glPushMatrix();


        //middle gaster
        gl2.glTranslatef(2f,0.5f,0);
        Material.redBrown(gl2);
    
        superEllipsoid(gl2, 1.5, 0.5, 1, 30, 2, 2);
       
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        Material.brown(gl2);
        
        //Thorax
        gl2.glTranslatef(4f, 1, 0);
    

        superEllipsoid(gl2, 2.0, 1.0, 2, 30, 2.5, 2.5);

        gl2.glPopMatrix();
        gl2.glPushMatrix();
        
        //neck
        gl2.glTranslatef(5.5f,1,0);

        
        gl2.glRotatef(90+NECK_SWING,0,1,0);
        
      
        glu.gluCylinder(q, NECK_RADIUS, NECK_RADIUS/2, NECK_LENGTH, SLICES, STACKS);
        gl2.glTranslatef(0, 0,NECK_LENGTH+1.5f);


        //head
        drawHead(gl2, glu, q);

        gl2.glPushMatrix();
       

        //eyes
        gl2.glTranslatef(0.9f,0,0.6f);
        Material.black(gl2);
        drawEye(gl2, glu, q);

        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslatef(-0.9f,0,0.6f);
        drawEye(gl2, glu, q);

        
       

        //CLUB
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        Material.brown(gl2);
        gl2.glTranslatef(-0.5f,-0.7f,1.2f);
        gl2.glRotatef(-25,0,1,0);
        gl2.glRotatef(-55,1,0,0);
        drawClub(gl2, glu, q, CLUB_UPPER_SWING, -CLUB_LOWER_SWING);

        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslatef(0.5f,-0.7f,1.2f);
        gl2.glRotatef(30,0,1,0);
        gl2.glRotatef(-55,1,0,0);
        drawClub(gl2, glu, q, -CLUB_UPPER_SWING, CLUB_LOWER_SWING);

        
       
        gl2.glPopMatrix();
        gl2.glPushMatrix();

        //HORN
        gl2.glTranslatef(0.2f, -0.7f,1.2f);
        gl2.glRotatef(50, 0,1,0);
        drawHorn(gl2, glu, q, HORN_SWING);

        
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        
        gl2.glTranslatef(-0.2f,-0.7f,1.2f);
        gl2.glRotatef(-50, 0,1,0);
        drawHorn(gl2, glu, q, -HORN_SWING);
        
        gl2.glPopMatrix();
        gl2.glPopMatrix();



        ////////////////////////////////LEGS////////////////////////////////////////////////

        //drawing first set of legs
      
        gl2.glPushMatrix();
        gl2.glTranslatef(3f,0,-1.5f);
        gl2.glRotatef(-60,0,1,0);
        drawLeg(gl2, glu, q, LEG_SWING,KNEE_SWING);

        gl2.glPopMatrix();
        gl2.glPushMatrix();

        gl2.glTranslatef(4f,0,-1.5f);
        gl2.glRotatef(-90,0,1,0);
        drawLeg(gl2, glu, q, -LEG_SWING, -KNEE_SWING/2);

        gl2.glPopMatrix();
        gl2.glPushMatrix();

        gl2.glTranslatef(5f,0.2f,-1.5f);
        gl2.glRotatef(220,0,1,0);
        drawLeg(gl2,glu,q,  LEG_SWING, KNEE_SWING);


        //drawing second set of legs
       
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslatef(3f,0,1.5f);
        gl2.glRotatef(60,0,1,0);
        drawLeg(gl2, glu, q, -LEG_SWING,KNEE_SWING);

        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslatef(4f,0,1.5f);
        gl2.glRotatef(90,0,1,0);
        drawLeg(gl2, glu, q, LEG_SWING, -KNEE_SWING/2);

        gl2.glPopMatrix();
        gl2.glPushMatrix();

        gl2.glTranslatef(5f,0.2f,1.5f);
        gl2.glRotatef(-220,0,1,0);
        drawLeg(gl2,glu,q, -LEG_SWING, KNEE_SWING);
        
        gl2.glPopMatrix();
       
    }

    private void drawHead(GL2 gl2, GLU glu, GLUquadric q){
        gl2.glPushMatrix();
        superEllipsoid(gl2, 1.3, 1.3, 1.5, 30, 2.5, 2.5);
        gl2.glTranslatef(0.5f,0.8f,0);
        Material.redBrown(gl2);
        superEllipsoid(gl2, 0.5, 0.5, 1, 30, 2.5, 2.5);
        gl2.glTranslatef(-1f,0, 0);
        superEllipsoid(gl2, 0.5, 0.5, 1, 30, 2.5, 2.5);
        gl2.glPopMatrix();

    }
  
    
    private void drawEye(GL2 gl2, GLU glu, GLUquadric q){
        gl2.glPushMatrix();
        glu.gluSphere(q, EYE_RADIUS, SLICES, STACKS);
        gl2.glPopMatrix();
    }

    private void drawClub(GL2 gl2, GLU glu, GLUquadric q, float swing, float swing2){
        gl2.glPushMatrix();
        glu.gluCylinder(q, CLUB_RADIUS, CLUB_RADIUS, CLUB_LENGTH, SLICES, STACKS);
        gl2.glTranslatef(0, 0,CLUB_LENGTH);
        gl2.glRotatef(120+swing,1,0,0);
        Material.redBrown(gl2);
        glu.gluSphere(q, CLUB_RADIUS, SLICES, STACKS);
        Material.brown(gl2);
        drawCone(gl2, glu, q, 3, CLUB_RADIUS, 0.1f, 0.5f, 0.2f, true);
       

        gl2.glTranslatef(0f,0.0f,0.3f);
        glu.gluSphere(q, CLUB_RADIUS, SLICES, STACKS);
        
        gl2.glRotatef(-30+swing2,1,0,0);
         drawCone(gl2, glu, q, 6, CLUB_RADIUS, 0.1f, 0.5f, 0.2f, true);
         gl2.glTranslatef(0,0,HORN_LENGTH/2);
         glu.gluCylinder(q, HORN_RADIUS, 0, CLUB_LENGTH/4, SLICES, STACKS);
        gl2.glPopMatrix();
    }

    private void drawHorn(GL2 gl2 ,GLU glu, GLUquadric q, float swing){
        gl2.glPushMatrix();
        glu.gluCylinder(q, HORN_RADIUS, HORN_RADIUS, HORN_LENGTH, SLICES, STACKS);
        gl2.glTranslatef(0, 0,HORN_LENGTH);
        gl2.glRotatef(120+swing,1,0,0);
        glu.gluSphere(q, HORN_RADIUS, SLICES, STACKS);
        drawCone(gl2, glu, q, 3, HORN_RADIUS, 0.1f, 0.5f, 0.2f, true);
        gl2.glTranslatef(0,0,HORN_LENGTH/2);
        glu.gluCylinder(q, HORN_RADIUS, 0, HORN_LENGTH, SLICES, STACKS);
        gl2.glPopMatrix();
    }

    public  void drawLeg(GL2 gl2, GLU glu, GLUquadric q, float swing, float swing2 ){
        gl2.glPushMatrix();
        
        
        gl2.glRotatef(-20+swing,0,0,1);
        Material.redBrown(gl2);
        glu.gluSphere(q, FEET_ANGLE_RADIUS, SLICES, STACKS);

        Material.brown(gl2);
   
        gl2.glTranslatef(-(FEET_ANGLE_RADIUS*4),0,0);
        gl2.glRotatef(90,0,1,0);
        glu.gluCylinder(q, BACK_HIP_RADIUS/2, BACK_HIP_RADIUS, BACK_HIP_LENGTH, SLICES, STACKS);
        //add rotation here for animation
        Material.redBrown(gl2);

        gl2.glRotatef(60,1,0,0);
        gl2.glRotatef(swing2, 0,1,0);
        glu.gluSphere(q, FEET_ANGLE_RADIUS/1.9, SLICES, STACKS);
        //gl2.glRotatef(60,1,0,0);
        Material.brown(gl2);
        glu.gluCylinder(q, BACK_LEG_RADIUS/2, BACK_LEG_RADIUS, BACK_LEG_LENGTH/2, SLICES, STACKS);
        gl2.glTranslatef(0,0,1);
        
        glu.gluCylinder(q, BACK_LEG_RADIUS, BACK_LEG_RADIUS/2, BACK_LEG_LENGTH/2, SLICES, STACKS);
        gl2.glTranslatef(0,0,1);
        Material.redBrown(gl2);
        glu.gluSphere(q, FEET_ANGLE_RADIUS/3, SLICES, STACKS);
        gl2.glRotatef(45, 1,0,0);
        Material.brown(gl2);
        glu.gluCylinder(q, BACK_LEG_RADIUS/4, BACK_LEG_RADIUS/2, BACK_LEG_LENGTH/2, SLICES, STACKS);
        
        gl2.glTranslatef(0,0,1);
        gl2.glRotatef(45,1,0,0);
        glu.gluSphere(q, FEET_ANGLE_RADIUS/4, SLICES, STACKS);
        glu.gluCylinder(q, BACK_LEG_RADIUS/8, BACK_LEG_RADIUS/4, BACK_LEG_LENGTH, SLICES, STACKS);
        gl2.glTranslatef(0,0,BACK_LEG_LENGTH);
        glu.gluCylinder(q, BACK_LEG_RADIUS/4, 0, BACK_LEG_LENGTH/2, SLICES, STACKS);
        gl2.glPopMatrix();

    }

   private  void drawCone(GL2 gl2, GLU glu, GLUquadric q, int n, float start_width, float stop_width, float length, float distance, boolean MIRROR){
       for(int i=0;i<n;i++){
           if(MIRROR){
              glu.gluCylinder(q, start_width/2, stop_width, length, SLICES, STACKS);
           }else{
               glu.gluCylinder(q, start_width, stop_width/2, length, SLICES, STACKS);
           }
           
           gl2.glTranslatef(0,0,distance);
       }

   }
  
    private  void superEllipsoid(GL2 gl, double rx, double ry, double rz, int n,
                                     double e1, double e2) {

    double pi = Math.PI;
    double dv = 2 * pi / n;
    double dw = pi / (n / 2);

    double exp1 = 2.0 / e1;
    double exp2 = 2.0 / e2;


    for (double v = 0; v < 2 * pi; v += dv) {
      //enable silhouette
      if(SILHOUETTE){
         gl.glBegin(GL2.GL_LINES);
      }else{
          gl.glBegin(GL2.GL_TRIANGLE_STRIP);
      }
     

      gl.glVertex3d(0.0, 0.0, rz);

      for (double w = dw; w < pi; w += dw) {

        double cosv = Math.cos(v);
        double cosw = Math.cos(w);
        double cosvdv = Math.cos(v + dv);
        double sinv = Math.sin(v);
        double sinw = Math.sin(w);
        double sinvdv = Math.sin(v + dv);

        double acosv = Math.abs(cosv);
        double acosw = Math.abs(cosw);
        double acosvdv = Math.abs(cosvdv);
        double asinv = Math.abs(sinv);
        double asinw = Math.abs(sinw);
        double asinvdv = Math.abs(sinvdv);

        double scosv = Math.signum(cosv);
        double scosw = Math.signum(cosw);
        double scosvdv = Math.signum(cosvdv);
        double ssinv = Math.signum(sinv);
        double ssinw = Math.signum(sinw);
        double ssinvdv = Math.signum(sinvdv);

        gl.glNormal3d((1 / rx) * scosv * Math.pow(acosv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                      (1 / ry) * ssinv * Math.pow(asinv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                      (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
        gl.glVertex3d(rx * scosv * Math.pow(acosv, exp1) * ssinw * Math.pow(asinw, exp2),
                      ry * ssinv * Math.pow(asinv, exp1) * ssinw * Math.pow(asinw, exp2),
                      rz * scosw * Math.pow(acosw, exp2));

        gl.glNormal3d((1 / rx) * scosvdv * Math.pow(acosvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                      (1 / ry) * ssinvdv * Math.pow(asinvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                      (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
        gl.glVertex3d(rx * scosvdv * Math.pow(acosvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                      ry * ssinvdv * Math.pow(asinvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                      rz * scosw * Math.pow(acosw, exp2));

      }

      gl.glVertex3d(0.0, 0.0, -rz);

      gl.glEnd();
    }
  }
    

     public void walk(){

   
          GASTER_SWING+=GASTER_DELTA;
            if((GASTER_SWING > MAX_GASTER_SWING)||(GASTER_SWING < - MAX_GASTER_SWING))
                GASTER_DELTA = -GASTER_DELTA;

         KNEE_SWING+=KNEE_DELTA;
            if((KNEE_SWING > KNEE_MAX)||(KNEE_SWING < - KNEE_MAX))
                KNEE_DELTA = -KNEE_DELTA;

          LEG_SWING+=LEG_DELTA;
            if((LEG_SWING > LEG_MAX)||(LEG_SWING < - LEG_MAX))
                LEG_DELTA = -LEG_DELTA;

        

          HORN_SWING+=HORN_DELTA;
            if((HORN_SWING > HORN_MAX_SWING)||(HORN_SWING < - HORN_MAX_SWING))
                HORN_DELTA = -HORN_DELTA;

          NECK_SWING+=NECK_DELTA_SWING;
            if((NECK_SWING > NECK_MAX_SWING)||(NECK_SWING < - NECK_MAX_SWING))
                NECK_DELTA_SWING = -NECK_DELTA_SWING;

          CLUB_UPPER_SWING+=CLUB_DELTA_UPPER;
            if((CLUB_UPPER_SWING > CLUB_MAX_SWING)||(CLUB_UPPER_SWING < - CLUB_MAX_SWING))
                CLUB_DELTA_UPPER = -CLUB_DELTA_UPPER;

          CLUB_LOWER_SWING+=CLUB_DELTA_LOWER;
            if((CLUB_LOWER_SWING > CLUB_MAX_SWING)||(CLUB_LOWER_SWING < - CLUB_MAX_SWING))
                CLUB_DELTA_LOWER = -CLUB_DELTA_LOWER;
         
     }

     



}
