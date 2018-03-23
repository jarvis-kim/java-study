package kr.co.jarvisk.pattern.visitor.step1;

public class Hyperlink extends AbstractDocument {

    private String url;

    public Hyperlink(DocumentPart document, String url) {
        super(document);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
