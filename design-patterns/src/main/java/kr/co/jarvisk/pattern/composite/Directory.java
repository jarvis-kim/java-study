package kr.co.jarvisk.pattern.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;

public class Directory implements FileNode {

    private String name;

    private List<FileNode> nodes = new ArrayList<>();

    private FileNode parent = null;

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        int size = 0;
        for ( FileNode node : nodes ) {
            size += node.size();
        }

        return size;
    }

    @Override
    public String path() {
        return Objects.nonNull(parent ) ?
                parent.path() + java.io.File.separator + name :
                java.io.File.separator + name;
    }

    @Override
    public void setParent(FileNode parent) {
        this.parent = parent;
    }

    public void addNode(FileNode node) {
        nodes.add(node);
        node.setParent(this);
    }

    public void removeNode(FileNode node) {
        nodes.add(node);
    }

    public Spliterator<FileNode> child() {
        return nodes.spliterator();
    }

}