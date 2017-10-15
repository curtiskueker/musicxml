package org.curtis.musicxml.note.notation.ornament;

public class AbstractMordent extends Ornament {
    private EmptyTrillSound emptyTrillSound;
    private Boolean longMordent;
    private String approach;
    private String departure;

    public AbstractMordent() {

    }

    public EmptyTrillSound getEmptyTrillSound() {
        return emptyTrillSound;
    }

    public void setEmptyTrillSound(EmptyTrillSound emptyTrillSound) {
        this.emptyTrillSound = emptyTrillSound;
    }

    public Boolean getLongMordent() {
        return longMordent;
    }

    public void setLongMordent(Boolean longMordent) {
        this.longMordent = longMordent;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
