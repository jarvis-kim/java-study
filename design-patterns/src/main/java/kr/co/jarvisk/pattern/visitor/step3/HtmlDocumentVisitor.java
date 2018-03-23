package kr.co.jarvisk.pattern.visitor.step3;

public class HtmlDocumentVisitor implements DocumentVisitor {
    private StringBuilder sb = new StringBuilder();

    private void visit(DocumentPart part) {
        if ( part instanceof PlainText ) {
            visit((PlainText) part);
        } else if ( part instanceof BoldText ) {
            visit((BoldText) part);
        } else if ( part instanceof Hyperlink ) {
            visit(((Hyperlink) part));
        } else if ( part instanceof AbstractDocument ) {
            visit(((AbstractDocument) part).getDocument());
        } else {
            throw new IllegalArgumentException("is not support type");
        }
    }

    @Override
    public void visit(PlainText plainText) {
        sb.append(plainText.getText());
    }

    @Override
    public void visit(BoldText boldText) {
        sb.append("<b>");
        visit(boldText.getDocument());
        sb.append("</b>");
    }

    @Override
    public void visit(Hyperlink hyperlink) {
        sb.append("<a href =\"" + hyperlink.getUrl() + "\">");
        visit(hyperlink.getDocument());
        sb.append("</a>");
    }

    public void newLine() {
        sb.append("\n");
    }

    public String toHtml() {
        return sb.toString();
    }
}
