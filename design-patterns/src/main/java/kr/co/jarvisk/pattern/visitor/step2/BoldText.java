package kr.co.jarvisk.pattern.visitor.step2;

public class BoldText extends AbstractDocument {

    public BoldText(DocumentPart document) {
        super(document);
    }

    @Override
    public String getText() {
        return getDocument().getText();
    }

    @Override
    public String toHtml() {
        return "<b>" + getText() + "</b>";
    }
}
