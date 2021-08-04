package maps.lwjgl;

import java.util.ArrayList;

public class Animation {

    private ArrayList<Frame> frames;
    private int curFrame;

    public Animation(){

        frames = new ArrayList<>();
    }

    public void render(){

        Frame temp = frames.get(curFrame);

        if(temp.render()){
            curFrame++;
            curFrame %= frames.size();
        }
    }
}
