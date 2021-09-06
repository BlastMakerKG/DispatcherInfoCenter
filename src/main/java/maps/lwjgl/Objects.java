package maps.lwjgl;

import lombok.Getter;
import maps.lwjgl.objects.*;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

@Getter
public abstract class Objects {
    protected float x;
    protected float y;

    protected Point.Sprite spr;
    protected Road.Sprite road;
    protected Line.Sprite line;
    protected Venichle.Sprite car;
    protected Layer.Sprite layer;

    public void update(){

    }

    public  void render(){
        glPushMatrix();
        {
            glTranslated(x, y,0);
            if(spr != null) {
                spr.render();
            }else if(line != null){
                line.render();
            }else if(car != null){
                car.render();
            }else if(layer != null){
                layer.render();
            }else if(road != null){
                road.render();
            }
        }

        glPopMatrix();
    }

    public void resize(float zoom){

        if(zoom>0){
            this.x /= 2;
            this.y /= 2;
            if(line != null){
                line.setSx(line.getSx()/2);
                line.setSy(line.getSy()/2);
            }
        }else if(zoom < 0){
            this.x *= 2;
            this.y *= 2;
            if(line != null){
                line.setSx(line.getSx()*2);
                line.setSy(line.getSy()*2);
            }
        }
    }

    protected void line(float x1,float y1, Line.Sprite spr){
        this.x = x1;
        this.y = y1;
        this.line = spr;
    }

    protected void point(float x, float y, Point.Sprite spr){
        this.x = x;
        this.y = y;
        this.spr = spr;
    }

    protected void point(float x, float y, Road.Sprite spr){
        this.x = x;
        this.y = y;
        this.road = spr;
    }

    protected void venichle(float x, float y, Venichle.Sprite spr){
        this.x = x;
        this.y = y;
        this.car = spr;
    }
    protected void layer(float x, float y, Layer.Sprite spr){
        this.x = x;
        this.y = y;
        this.layer = spr;
    }

}
