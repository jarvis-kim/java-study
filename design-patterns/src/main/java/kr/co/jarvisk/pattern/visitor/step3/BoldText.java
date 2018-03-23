package kr.co.jarvisk.pattern.visitor.step3;

public class BoldText extends AbstractDocument {

    public BoldText(DocumentPart document) {
        super(document);
    }

    @Override
    public String getText() {
        return getDocument().getText();
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
