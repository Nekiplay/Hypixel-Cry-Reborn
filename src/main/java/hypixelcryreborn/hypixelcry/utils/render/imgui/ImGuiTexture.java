package hypixelcryreborn.hypixelcry.utils.render.imgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicInteger;

public class ImGuiTexture {
    private AtomicInteger _texture = new AtomicInteger(-2);

    public int GetTexture() {
        return _texture.get();
    }

    public void Sync(String imageUrl) {
        MinecraftClient.getInstance().execute(() ->
        {
            NativeImage image = FromUrl(imageUrl);
            if (image != null) {
                Identifier dynamic = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("test", new NativeImageBackedTexture(image));
                _texture.set(MinecraftClient.getInstance().getTextureManager().getTexture(dynamic).getGlId());
            }
            else { _texture.set(-1); }
        });
    }
    public void Async(String imageUrl)
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                NativeImage image = FromUrl(imageUrl);
                if (image != null) {
                    Identifier dynamic = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("test", new NativeImageBackedTexture(image));
                    _texture.set(MinecraftClient.getInstance().getTextureManager().getTexture(dynamic).getGlId());
                }
                else { _texture.set(-1); }
            }
        };
        MinecraftClient.getInstance().executeTask(r);
    }
    private NativeImage FromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            byte[] imageData = output.toByteArray();
            stream.close();
            output.close();
            NativeImage image = NativeImage.read(new ByteArrayInputStream(imageData));
            return image;
        }
        catch (IOException e) {

        }
        return null;
    }
}
