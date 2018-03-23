package kr.co.jarvisk.pattern.visitor.step1;

public class BoldText extends AbstractDocument {

    public BoldText(DocumentPart document) {
        super(document);
    }

    @Override
    public String getText() {
        return getDocument().getText();
    }
}
