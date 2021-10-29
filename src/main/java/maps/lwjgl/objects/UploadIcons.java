package maps.lwjgl.objects;
import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class UploadIcons {

    private String filepath;
    private Texture wood;
    int idTexture;

    public UploadIcons(String path){
        filepath = path;
        try {
            System.out.println("src\\main\\resources\\Venichles\\"+ path);
            wood = TextureLoader.getTexture("PNG", new FileInputStream(new File("src\\main\\resources\\Venichles\\"+ path)));

//            idTexture = glGenTextures();
//            glBindTexture(GL_TEXTURE_2D, idTexture);
//            // Use TWL's utility classes to load the png file.
//            PNGDecoder decoder = new PNGDecoder(new FileInputStream("src\\main\\resources\\Venichles\\"+ path));
//            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
//            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
//            buffer.flip();
//            // Load the previously loaded texture data into the texture object.
//            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
//                    buffer);
//            // Unbind the texture.
//            glBindTexture(GL_TEXTURE_2D, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(){
        glEnable(GL_TEXTURE_2D);
        wood.bind();
        // Enable additive blending. This means that the colours will be added to already existing colours in the
        // frame buffer. In practice, this makes the black parts of the texture become invisible.
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        glColor3f(1,1,1);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2i(-10, 30); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i(20, 30); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i(20, 10); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(-10, 10); // Bottom-left
        }
        glEnd();
    }
}
