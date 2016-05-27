package jp.ac.titech.itpro.sdl.gles10ex;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleRenderer implements GLSurfaceView.Renderer {

    public interface Drawable {
        void draw(GL10 gl);
    }

    private Drawable obj1, obj2;
    private float rx = 0, ry = 0, rz = 0;

    public SimpleRenderer(Drawable obj1, Drawable obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 20, ((float) width) / height, 1, 50);
        GLU.gluLookAt(gl, 0, 0, 10, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0.2f, -3);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);
        gl.glScalef(1, 1, 1);
        obj2.draw(gl);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0, -0.2f, 0);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);
        gl.glScalef(1, 1, 1);
        obj1.draw(gl);
    }

    public void setRotationX(float th) {
        rx = th;
    }

    public void setRotationY(float th) {
        ry = th;
    }

    public void setRotationZ(float th) {
        rz = th;
    }
}
