//DELON ARTHUR
package Frontend;

public class Animation {
    private final int nbFrames;
    private final int yOffset;
    private final int frameWidth;
    private final int frameHeight;
    private final int interFramePause;

    public Animation(int nbFrames, int yOffset, int frameWidth, int frameHeight, int interFramePause) {
        this.nbFrames = nbFrames;
        this.yOffset = yOffset;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.interFramePause = interFramePause;
    }

    public int getNbFrames() {
        return nbFrames;
    }

    public int getyOffset() {
        return yOffset;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getFramePause() {
        return interFramePause;
    }
}
