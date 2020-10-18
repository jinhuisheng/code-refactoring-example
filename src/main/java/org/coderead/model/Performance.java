package org.coderead.model;

/**
 * 表演
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Performance {

    private String playId;

    private int audience;

    private Play play;

    private int amount;

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
