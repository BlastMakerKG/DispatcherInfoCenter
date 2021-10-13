package maps.lwjgl.objects.venichles;

import maps.lwjgl.Objects;
import lombok.Setter;
import maps.lwjgl.objects.Sprite;

import static org.lwjgl.opengl.GL11.*;

@Setter
public abstract class Venichle extends Objects {

    public static float SIZE = 8;
    private float r = 0.1f,g=1.0f,b=0.1f;

    private SpriteVenichle spr;

    public Venichle(float x, float y){
        this.spr = new SpriteVenichle(r,g,b,SIZE,SIZE);
        venichle(x,y, spr);
    }

    public Venichle(float x, float y, float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
        this.spr = new SpriteVenichle(r,g,b,SIZE, SIZE);
        venichle(x,y, spr);
    }

    public class SpriteVenichle implements Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        @Override
        public float getSx() {
            return sx;
        }

        @Override
        public void setSx(float sx) {
            this.sx = sx;
        }

        @Override
        public float getSy() {
            return sy;
        }

        @Override
        public void setSy(float sy) {
            this.sy = sy;
        }



        public SpriteVenichle(float r, float g, float b, float sx, float sy) {
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
