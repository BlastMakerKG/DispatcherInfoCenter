package maps.lwjgl.objects;

import maps.lwjgl.Objects;
import lombok.Setter;
import static org.lwjgl.opengl.GL11.*;

@Setter
public class Venichle extends Objects {

    public static float SIZE = 8;
    private float r = 0.1f,g=1.0f,b=0.1f;

    private Venichle.Sprite spr;

    public Venichle(float x, float y){
        this.spr = new Venichle.Sprite(r,g,b,SIZE,SIZE);
        venichle(x,y, spr);
    }

    public class Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        public Sprite(float r, float g, float b, float sx, float sy) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.sx = sx;
            this.sy = sy;
        }

        public void render() {
            glColor3f(r, g, b);

            glBegin(GL_QUADS);

            {
                glVertex2f(0, 0);
                glVertex2f(0, sy);
                glVertex2f(sx, sy);
                glVertex2f(sx, 0);
            }

            glEnd();
        }
    }


}
