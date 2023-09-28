//DELON ARTHUR
package Frontend;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Set;

public class AnimatedSprite extends AnimationTimer {
    private final HashMap<String, Animation> animations;
    private final String defaultAnimation;
    private ImageView display;
    private int currentViewportWidth;
    private int currentViewportHeight;
    private int nbFrames;
    private int currentxOffset;
    private int currentyOffset;
    private int interFramePause;
    private int currentFrame;
    private int counter;
    private String currentAnimation;

    public AnimatedSprite(Image image, HashMap<String, Animation> animations, String defaultAnimation) {
        display = new ImageView();
        display.setImage(image);
        this.animations = animations;
        currentxOffset = 0;
        currentyOffset = 0;
        nbFrames = 0;
        interFramePause = 0;
        counter = 0;
        currentFrame = 0;
        this.defaultAnimation = defaultAnimation;
        currentAnimation = "";
        setAnimation(defaultAnimation);
    }

    public void setAnimation(String animation) {
        currentxOffset = 0;
        Animation a = animations.get(animation);
        currentyOffset = a.getyOffset();
        currentViewportWidth = a.getFrameWidth();
        currentViewportHeight = a.getFrameHeight();
        nbFrames = a.getNbFrames();
        interFramePause = a.getFramePause();
        currentFrame = 0;
        currentAnimation = animation;
    }

    @Override
    public void handle(long l) {
        if (counter == interFramePause) {
            display.setViewport(new Rectangle2D(currentxOffset, currentyOffset, currentViewportWidth, currentViewportHeight));
            currentxOffset = (currentxOffset + currentViewportWidth) % (nbFrames * currentViewportWidth);
            counter = 0;
            currentFrame++;
            if (currentFrame == nbFrames && !currentAnimation.equals(defaultAnimation)) {
                synchronized (this) {
                    this.notify();
                    setAnimation(defaultAnimation);
                }
            }
        } else {
            counter++;
        }
    }

    public void start() {
        super.start();
        display.setViewport(new Rectangle2D(currentxOffset, currentyOffset, currentViewportWidth, currentViewportHeight));
    }

    public ImageView getDisplay() {
        return display;
    }

    public Set<String> getAnimationSet() {
        return animations.keySet();
    }
}
