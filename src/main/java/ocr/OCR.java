package ocr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class OCR {
    private HashMap<BufferedImage, String> characters = null;

    public OCR(String fontPath) {
        this.characters = new HashMap<>();
        File directory = new File(fontPath);
        try {
            for (File i : directory.listFiles()) {
                BufferedImage character = ImageIO.read(i);
                String charName = i.getName().substring(0, 1);
                this.characters.put(character, charName.equals("%") ? "/" : charName);
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getCharacterFromImage(BufferedImage image) {
        for (BufferedImage i : this.characters.keySet()) {
            if (bufferedImagesEqual(i, image)) {
                //System.out.println("recognized: " + this.characters.get(i));
                return this.characters.get(i);
            }
        }
        System.out.println("Error, character unrecognized");
        try {
            ImageIO.write(image, "png", new File("charError.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new OutOfMemoryError("EVERYTHING IS FUCKED");
    }

    public String getStringFromImage(BufferedImage image) {
        String recognized = "";
        int spaceNumber = 0;
        for (int i = 0; i < image.getWidth() / 8; i++) {
            BufferedImage subImage = image.getSubimage(8 * i, 0, 8, 10);
            String character = getCharacterFromImage(subImage);
            recognized += character;
        }
        return recognized;
    }

    public String getImageHash(BufferedImage image) {
        String hash = "";
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                hash += image.getRGB(x, y) == -16777216 ? "0" : "1";
            }
        }
        return hash;
    }

    public boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
