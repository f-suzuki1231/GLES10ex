package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pyramid implements SimpleRenderer.Drawable {

    private static final float[] vertices = {
            // bottom
            -0.5f, 0, -0.5f,
            0.5f, 0, -0.5f,
            -0.5f, 0, 0.5f,
            0.5f, 0, 0.5f,
            // left
            -0.5f, 0, -0.5f,
            0, 0.5f, 0,
            -0.5f, 0, 0.5f,
            // right
            0.5f, 0, -0.5f,
            0, 0.5f, 0,
            0.5f, 0, 0.5f,
            // back
            -0.5f, 0, -0.5f,
            0, 0.5f, 0,
            0.5f, 0, -0.5f,
            // front
            -0.5f, 0, 0.5f,
            0, 0.5f, 0,
            0.5f, 0, 0.5f
    };

    private FloatBuffer vbuf;

    public Pyramid() {
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // left
        gl.glNormal3f(-1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 4, 3);

        // right
        gl.glNormal3f(1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 7, 3);

        // back
        gl.glNormal3f(0, 1, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 10, 3);

        // front
        gl.glNormal3f(0, 1, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 13, 3);
    }
}
