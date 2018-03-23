package kr.co.jarvisk.pattern.visitor.step3;

public interface DocumentPart {
    String getText();

    void accept(DocumentVisitor visitor);
}

