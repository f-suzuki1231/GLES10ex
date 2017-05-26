package jp.ac.titech.itpro.sdl.gles10ex;

/**
 * Created by fuyu_suzuki on 2017/05/26.
 * 六角錐を追加（課題：立体図形を追加せよ）
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class HexagonalPyramid implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x, y, z;

    public HexagonalPyramid(float s, float x, float y, float z) {
        float[] vertices = {
                //bottom
                 0,    0.7f, 0,
                -0.7f, 0.5f, 0,
                -0.7f,-0.5f, 0,
                 0,   -0.7f, 0,
                 0.7f,-0.5f, 0,
                 0.7f, 0.5f, 0,


                 0,    0.7f, 0,
                 0,       0, 1,//頂点
                -0.7f, 0.5f, 0,

                -0.7f, 0.5f, 0,
                 0,       0, 1,
                -0.7f,-0.5f, 0,

                -0.7f,-0.5f, 0,
                 0,       0, 1,
                 0,   -0.7f, 0,

                 0,   -0.7f, 0,
                 0,       0, 1,
                 0.7f,-0.5f, 0,

                 0.7f,-0.5f, 0,
                 0,       0, 1,
                 0.7f, 0.5f, 0,

                 0.7f, 0.5f, 0,
                 0,       0, 1,
                 0,    0.7f, 0

        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);



       // bottom
        gl.glNormal3f(0, 1, 1.5f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);


        gl.glNormal3f(0.5f, 1, 0.5f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 6, 3);


        gl.glNormal3f(-0.5f, 0, 0.5f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 9, 3);


        gl.glNormal3f(1, 0, 0.8f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 12, 3);


        gl.glNormal3f(0.5f,-0.5f,0.5f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 15, 3);

        gl.glNormal3f(-0.5f, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 18, 3);

        gl.glNormal3f(0,0,0.8f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 21, 3);

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
