package kr.co.jarvisk.pattern.composite;

public class Client {

    public static void main(String[] args) {
        Directory home = new Directory("home");

        home.addNode(new File("test.jpg", 100));
        home.addNode(new File("test2.jpg", 100));

        Directory documents = new Directory("documents");
        documents.addNode(new File("word1.doc", 30));

        File word2 = new File("word2.doc", 20);
        documents.addNode(word2);
        home.addNode(documents);

        System.out.println(home.size());
        System.out.println(documents.path());
        System.out.println(word2.path());
    }
}