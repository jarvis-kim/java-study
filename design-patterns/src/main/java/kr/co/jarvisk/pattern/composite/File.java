package kr.co.jarvisk.pattern.composite;

import java.util.Objects;

public class File implements FileNode {

    private String name;

    private int fileSize;

    private FileNode parent;

    public File(String name, int fileSize) {
        this.name = name;
        this.fileSize = fileSize;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        return fileSize;
    }

    @Override
    public String path() {
        return Objects.nonNull(parent) ?
                parent.path() + java.io.File.separator + name :
                name;
    }

    @Override
    public void setParent(FileNode parent) {
        this.parent = parent;
    }

}

