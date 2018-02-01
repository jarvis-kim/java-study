package kr.co.jarvisk.pattern.composite;

public interface FileNode {

    /**
     * 파일, 디렉토리의 이름.
     * @return 파일, 디렉토리의 이름.
     */
    String name();

    /**
     * 파일의 사이즈.
     *
     * @return
     * 파일의 경우 해당 파일의 사이즈를 리턴하겠다.
     * 디렉토리의 경우 현재 디렉토리 하위에 있는 디렉토리와 파일 사이즈들의 합을 리턴하겠다.
     */
    int size();

    /**
     * 현재 경로
     * @return 상위 경로를 포함한 현재 경로를 리턴한다.
     */
    String path();

    /**
     * 상위 경로를 위한 상위 FileNode다.
     * 디텍토리처럼 그릇에 담는경우 디렉토리가 자신을 파라미터로 넘길것이다.
     * 개인적으로 interface에 protected 접근제어자가 존재했으면 좋겠..
     * 본래 이쪽에 들어가면 안되는 메소드지만, 예제라 여기에 배치했다.
     * @param parent 상위 FileNode
     */
    void setParent(FileNode parent);
}