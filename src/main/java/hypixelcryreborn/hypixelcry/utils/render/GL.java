package hypixelcryreborn.hypixelcry.utils.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import hypixelcryreborn.hypixelcry.HypixelCry;
import hypixelcryreborn.hypixelcry.utils.network.Http;
import imgui.gl3.ImGuiImplGl3;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;

public class GL {
    public static byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
    private static NativeImage image;
    public static int bindTextureImgui(String imageUrl) {
        //try {
        //    InputStream channel = Http.get(imageUrl).sendInputStream();
        //    byte[] bytes = channel.readAllBytes();
        //    HypixelCry.LOGGER.info("Texture Bytes: " + bytes.length);
        //    ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
        //    buffer.put(bytes);
        //    buffer.flip();
        //    HypixelCry.LOGGER.info("ByteBuffer Initilizated");
        //    NativeImage image = NativeImage.read(buffer);

        //    // Generate OpenGL texture
        //    int textureId = GL11.glGenTextures();
        //    HypixelCry.LOGGER.info("Texture ID: " + textureId);
        //    GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
        //    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        //    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        //    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        //    HypixelCry.LOGGER.info("Pre bind texture");
        //    GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
        //    return textureId;
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        int textureId = MinecraftClient.getInstance().getTextureManager().getTexture(new Identifier("hypixel-cry", "textures\\custom_texture.jpg")).getGlId();

        MinecraftClient.getInstance().execute(() -> {
            try {
                // Load the texture from a website URL
                URL url = new URL("https://example.com/image.png");
                InputStream stream = url.openStream();

                // Bind the texture
                GlStateManager._bindTexture(textureId);

                // Set texture parameters
                GlStateManager._texParameter(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                GlStateManager._texParameter(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                GlStateManager._texParameter(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
                GlStateManager._texParameter(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

                // Upload the texture data
                NativeImage image = NativeImage.read(stream);
                byte[] imageData = image.getBytes();
                int width = image.getWidth();
                int height = image.getHeight();

                IntBuffer pixelBuffer = ByteBuffer.allocateDirect(imageData.length)
                        .order(ByteOrder.nativeOrder())
                        .asIntBuffer();

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int i = (x + y * width) * 4;
                        int r = imageData[i] & 0xFF;
                        int g = imageData[i + 1] & 0xFF;
                        int b = imageData[i + 2] & 0xFF;
                        int a = imageData[i + 3] & 0xFF;
                        pixelBuffer.put((a << 24) | (b << 16) | (g << 8) | r);
                    }
                }

                pixelBuffer.flip();

                GlStateManager._texImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelBuffer);
                image.close();

                // Unbind the texture
                //GlStateManager.bindTexture(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return textureId;
    }
    public static void bindTexture(Identifier id) {
        GlStateManager._activeTexture(GL_TEXTURE0);
        MinecraftClient.getInstance().getTextureManager().bindTexture(id);
    }

    public static void bindTexture(int i, int slot) {
        GlStateManager._activeTexture(GL_TEXTURE0 + slot);
        GlStateManager._bindTexture(i);
    }
    public static void bindTexture(int i) {
        bindTexture(i, 0);
    }
}
