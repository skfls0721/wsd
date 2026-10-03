package kr.ac.jbnu.hnr.wsd.post;

public record PostRequest(
        String title,
        String content
) {

    public void validate() {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }
    }
}