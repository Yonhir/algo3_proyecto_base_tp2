package edu.fiuba.algo3.views.components.cardcomponent.card;

import java.io.InputStream;

public class ImageNameMapper {
    public static String getImageFilename(String cardName) {
        if (cardName == null || cardName.trim().isEmpty()) {
            return null;
        }
        
        String normalizedName = cardName.replace(":", "");
        return normalizedName + ".png";
    }
    
    public static InputStream getImageStream(String cardName) {
        if (cardName == null || cardName.trim().isEmpty()) {
            return null;
        }
        
        String imageFilename = getImageFilename(cardName);
        if (imageFilename == null) {
            return null;
        }
        
        String imagePath = "/images/" + imageFilename;
        return ImageNameMapper.class.getResourceAsStream(imagePath);
    }
} 