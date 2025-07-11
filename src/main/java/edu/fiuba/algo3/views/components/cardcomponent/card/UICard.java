package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.BaseCardComponent;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

public abstract class UICard extends BaseCardComponent {
    
    protected String cardName;
    protected String description;
    protected ImageView backgroundImage;
    protected CardPlayingController cardPlayingController;

    public UICard(String name, String description, CardPlayingController controllerCards) {
        super();
        this.cardPlayingController = controllerCards;
        this.cardName = name;
        this.description = description;
        setUpMouseHandlers();
        loadCardImage();
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(161, 92, 1);
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(97, 47, 0);
    }

    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        scaleComponent(scaleFactorX, scaleFactorY);
        
        if (backgroundImage != null) {
            double newWidth = baseWidth * scaleFactorX;
            double newHeight = baseHeight * scaleFactorY;
            backgroundImage.setFitWidth(newWidth);
            backgroundImage.setFitHeight(newHeight);
        }
    }
    
    private void loadCardImage() {
        if (cardName == null || cardName.trim().isEmpty()) {
            return;
        }
        
        String pngPath = "/images/" + cardName + ".png";
        InputStream pngStream = getClass().getResourceAsStream(pngPath);
        
        if (pngStream != null) {
            loadImageFromStream(pngStream);
            return;
        }
        
        String webpPath = "/images/" + cardName + ".webp";
        InputStream webpStream = getClass().getResourceAsStream(webpPath);
        
        if (webpStream != null) {
            loadImageFromStream(webpStream);
        }
    }
    
    private void loadImageFromStream(InputStream imageStream) {
        try {
            Image image = new Image(imageStream);
            backgroundImage = new ImageView(image);
            backgroundImage.setFitWidth(baseWidth);
            backgroundImage.setFitHeight(baseHeight);
            backgroundImage.setPreserveRatio(true);
            getChildren().add(0, backgroundImage);
        } catch (Exception e) {
            System.err.println("Error loading image for card " + cardName + ": " + e.getMessage());
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    private void setUpMouseHandlers() {
        setOnMousePressed(cardPlayingController);
        setOnMouseDragged(cardPlayingController);
        setOnMouseReleased(cardPlayingController);
    }

    public void setDraggable(boolean draggable) { cardPlayingController.setDraggable(draggable); }

    public abstract Card getModel();

    public abstract void switchOnRows(ArrayList<UIRow> rows);

    public abstract void switchOffRows(ArrayList<UIRow> rows);

    public abstract void placeUICard(MouseEvent event, ArrayList<Region> board, ArrayList<UIRow> rows, UISpecialZone specialZone);

    protected ArrayList<Node> getBoard(){
        VBox board = (VBox) getParent().getParent().getParent();

        ArrayList<Node> rows = new ArrayList<>(board.getChildrenUnmodifiable().subList(0, 3));

        return rows;
    }
}
