package maps.lwjgl;

import lombok.Getter;
import maps.lwjgl.objects.Layer;
import maps.lwjgl.objects.Line;
import maps.lwjgl.objects.Point;
import maps.lwjgl.objects.Venichle;

import static org.lwjgl.opengl.GL11.*;

@Getter
public abstract class Objects {
    protected float x;
    protected float y;

    protected Point.Sprite spr;
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



//         if( zoom > 0){
//            this.x -= zoom*0.5;
//            this.y -= zoom*0.5;
//            this.spr.setSx(spr.getSx()+zoom);
//            this.spr.setSy(spr.getSy()+zoom);
//        }else if(this.spr.getSx() <= 1.5f && this.spr.getSy() <= 1.5f){
//            this.spr.setSy(1.5f);
//            this.spr.setSx(1.5f);
//        }else{
//            this.x += -zoom*.5;
//            this.y += -zoom*0.5;
//            this.spr.setSx(spr.getSx()+zoom);
//            this.spr.setSy(spr.getSy()+zoom);
//        }
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
