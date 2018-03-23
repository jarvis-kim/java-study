package kr.co.jarvisk.pattern.visitor.step3;

public interface DocumentVisitor  {

    void visit(PlainText plainText);

    void visit(BoldText boldText);

    void visit(Hyperlink hyperlink);
}
