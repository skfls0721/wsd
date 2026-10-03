package kr.ac.jbnu.hnr.wsd.post;

public record TitleRequest(
        String title
) {

    public void validate() {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
    }
}