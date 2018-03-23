package kr.co.jarvisk.pattern.visitor.step3;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<DocumentPart> documentParts = new ArrayList<>();

    public void add(DocumentPart part) {
        documentParts.add(part);
    }

    public String toHtml() {
        HtmlDocumentVisitor visitor = new HtmlDocumentVisitor();
        documentParts.forEach(part -> {
            part.accept(visitor);
            visitor.newLine();
        });

        return visitor.toHtml();
    }

    public static void main(String[] args) {
        Document doc = new Document();

        doc.add(new PlainText("plain text"));
        doc.add(new BoldText(new PlainText("bold text")));
        doc.add(new Hyperlink(new BoldText(new PlainText("github host")), "https://github.com"));

        System.out.println(doc.toHtml());
    }
}
