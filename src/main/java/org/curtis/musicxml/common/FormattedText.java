package org.curtis.musicxml.common;

import org.apache.commons.text.StringEscapeUtils;
import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formatted_text")
public class FormattedText extends DatabaseItem {
    @Lob
    private String value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_formatting_id")
    private TextFormatting textFormatting;

    public FormattedText() {

    }

    public String getValue() {
        return StringEscapeUtils.unescapeXml(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
