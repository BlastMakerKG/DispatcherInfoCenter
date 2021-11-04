package maps.lwjgl.objects.venichles;

import maps.lwjgl.Objects;
import maps.lwjgl.objects.Sprite;
import maps.lwjgl.objects.UploadIcons;

import static org.lwjgl.opengl.GL11.*;

public abstract class Venichle extends Objects {

    public static float SIZE = 8;

    private SpriteVenichle spr;

    public Venichle(float x, float y, float r, float g, float b, String filename){
        this.spr = new SpriteVenichle(r,g,b,SIZE, SIZE, filename);
        venichle(x,y, spr);
    }

    public class SpriteVenichle implements Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        private UploadIcons icon;

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

        public SpriteVenichle(float r, float g, float b, float sx, float sy, String filename) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.sx = sx;
            this.sy = sy;

            icon = new UploadIcons(filename);
        }

        public void render() {

            glColor3f(r, g, b);
            glBegin(GL_TRIANGLES);

            {

                glVertex2f(0, 0);
                glVertex2f(-10, 10);
                glVertex2f(10,10);
            }
            glEnd();
            icon.render();

        }
    }


}
