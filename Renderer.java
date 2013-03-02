package Ant;


import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class Renderer extends GLCanvas implements GLEventListener
{
  private int x = 0;

  private float zoom = 0.0f;
  private float rotateY = 0.0f;
  private float rotateX = 0.0f;
  private float Z = 0.0f;
 
  private boolean PAUSE = false;
  private boolean WALK = false;
  private boolean SILHOUETTE;

  private static float ROTATION_SPEED = 2.0F;
  private static float ZOOM_SPEED = 0.3F;

  //keyboard manipulation
  private class Key implements KeyListener{


        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
           if(e.getKeyCode() == KeyEvent.VK_W){
               zoom += ZOOM_SPEED;
           }
            if(e.getKeyCode() == KeyEvent.VK_S){
               zoom -= ZOOM_SPEED;
           }

           if(e.getKeyCode() == KeyEvent.VK_A){
               rotateY += ROTATION_SPEED;
           }
           if(e.getKeyCode() == KeyEvent.VK_D){
               rotateY -= ROTATION_SPEED;
           }

           if(e.getKeyCode() == KeyEvent.VK_Q){
               rotateX += ROTATION_SPEED;
           }
           if(e.getKeyCode() == KeyEvent.VK_E){
               rotateX -= ROTATION_SPEED;
           }

           if(e.getKeyCode() == KeyEvent.VK_SPACE){
               if(PAUSE){
                   PAUSE = false;
               }else{

                   PAUSE = true;
               }
           }

           if(e.getKeyCode() == KeyEvent.VK_ENTER){
               if(WALK){
                   WALK = false;
               }else{
                   WALK = true;
               }
           }

           if(e.getKeyCode() == KeyEvent.VK_1){
               if(SILHOUETTE){
                   SILHOUETTE = false;
               }else{
                   SILHOUETTE = true;
               }
           }

           if(e.getKeyCode() == KeyEvent.VK_UP){
               Z +=ROTATION_SPEED;
           }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
               Z -=ROTATION_SPEED;
           }
        }

        public void keyReleased(KeyEvent e) {

        }

  }
  public static void main(String[] args)
  {
    Frame frame = new Frame("An ant");
    GLCanvas canvas = new Renderer();
    
    frame.add(canvas);
    frame.setSize(640, 480);
    final FPSAnimator animator = new FPSAnimator(canvas, 60);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {

        // Run this on another thread than the AWT event queue to
        // make sure the call to Animator.stop() completes before
        // exiting
        new Thread(new Runnable() {
          public void run() {
            animator.stop();
            System.exit(0);
          }
        }).start();

      }
    });

    // Center frame
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);
    animator.start();
  }

  public Renderer(){
      this.addGLEventListener(this);
      this.addKeyListener(new Key());
  }

  public void init(GLAutoDrawable drawable)
  {
     GL2 gl = drawable.getGL().getGL2();

    // Set backgroundcolor and shading mode
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    // give me some light
    float ambient[] = {1.0f,1.0f,1.0f,1.0f };
    float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
    float specular[]= {0.2f,1.0f,0.2f,1.0f};
    float position[] = {20.0f,30.0f,20.0f,0.0f };
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT,ambient,0);
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse,0);
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position,0);
  

    gl.glEnable(GL2.GL_LIGHTING);
    gl.glEnable(GL2.GL_LIGHT0);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glEnable(GL2.GL_NORMALIZE);
  }

  public void reshape(GLAutoDrawable drawable,
                      int x, int y, int width, int height)
  {
    GL2 gl = drawable.getGL().getGL2();;
    GLU glu = new GLU();
    if (height <= 0) // no divide by zero
      height = 1;
    // keep ratio
    final float h = (float) width / (float) height;
    gl.glViewport(0, 0, width, height);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluPerspective(45.0f, h, 1.0, 40.0);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
  }
  private void update(){
      rotateY-=0.25f;
    
  }
  public void display(GLAutoDrawable drawable)
  {

    if(!PAUSE){
        update();
    }
    
    GL2 gl = drawable.getGL().getGL2();;
   
     
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();
    gl.glTranslatef(0,0f,-25f);
    //gl.glTranslatef(-5,0f,0f);
    

    gl.glTranslatef(0, 0, zoom);
    gl.glRotatef(rotateY, 0,1, 0f);
    gl.glRotatef(rotateX, 1, 0, 0);
    gl.glRotatef(Z, 0, 0, 1);
    Ant ant = new Ant();

    ant.draw(gl);
    
    if(WALK){
        ant.walk();
    }

    if(SILHOUETTE){
        ant.enableSilhouette();
    }else{
        ant.disableSilhouette();
    }
 
    gl.glFlush();

  }

  public void displayChanged(GLAutoDrawable drawable,
                            boolean modeChanged, boolean deviceChanged) { }

    public void dispose(GLAutoDrawable arg0) {

    }

  
}
