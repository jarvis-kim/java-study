package kr.co.jarvisk.pattern.visitor.step2;

public class Hyperlink extends AbstractDocument {

    private String url;

    public Hyperlink(DocumentPart document, String url) {
        super(document);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toHtml() {
        return "<a href =\"" + getUrl() + "\">" + getDocument().toHtml() + "</a>";
    }
}
