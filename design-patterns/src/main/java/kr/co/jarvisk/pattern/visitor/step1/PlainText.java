package kr.co.jarvisk.pattern.visitor.step1;

public class PlainText implements DocumentPart {

    private String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
