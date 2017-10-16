package org.curtis.musicxml.note.notation.ornament;

public abstract class AbstractMordent extends Ornament {
    private PlacedTrillSound emptyTrillSound;
    private Boolean longMordent;
    private String approach;
    private String departure;

    public PlacedTrillSound getEmptyTrillSound() {
        return emptyTrillSound;
    }

    public void setEmptyTrillSound(PlacedTrillSound emptyTrillSound) {
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
