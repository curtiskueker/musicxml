package org.curtis.musicxml.display;

import org.curtis.database.OrderedItem;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class FormattedDisplay extends OrderedItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_format_id")
    private TextFormat textFormat;

    public FormattedDisplay() {

    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public TextFormat getTextFormat() {
        return textFormat;
    }

    public void setTextFormat(TextFormat textFormat) {
        this.textFormat = textFormat;
    }
}
