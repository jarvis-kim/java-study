package kr.co.jarvisk.pattern.visitor.step2;

public abstract class AbstractDocument implements DocumentPart {

    private DocumentPart document;

    public AbstractDocument(DocumentPart document) {
        this.document = document;
    }

    public DocumentPart getDocument() {
        return document;
    }

    @Override
    public String getText() {
        return document.getText();
    }
}
