package kr.ac.jbnu.hnr.wsd.post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String message) {
        super(message);
    }
}