package jp.ac.titech.itpro.sdl.gles10ex;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleRenderer implements GLSurfaceView.Renderer {
    private final static String TAG = "SimpleRenderer";

    public interface Obj {
        void draw(GL10 gl);
        float getX();
        float getY();
        float getZ();
    }

    private float rx = 0, ry = 0, rz = 0;

    private ArrayList<Obj> objs;

    public SimpleRenderer() {
        objs = new ArrayList<>();
    }

    public void addObj(Obj obj) {
        objs.add(obj);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated");
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged");
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 20, ((float) width) / height, 1, 50);
        GLU.gluLookAt(gl, 0, 0, 10, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.1f, 0.1f, 0.1f, 0.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        for (Obj obj : objs) {
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(obj.getX(), obj.getY(), obj.getZ());
            gl.glRotatef(rx, 1, 0, 0);
            gl.glRotatef(ry, 0, 1, 0);
            gl.glRotatef(rz, 0, 0, 1);
            gl.glScalef(1, 1, 1);
            obj.draw(gl);
        }
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
