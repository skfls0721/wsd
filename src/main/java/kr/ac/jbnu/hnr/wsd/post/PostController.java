package kr.ac.jbnu.hnr.wsd.post;

import kr.ac.jbnu.hnr.wsd.common.ApiResponse;
import kr.ac.jbnu.hnr.wsd.common.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Post> createPost(
            @RequestBody PostRequest request
    ) {
        request.validate();

        Post post = postService.createPost(
                request.title(),
                request.content()
        );

        return ApiResponse.success(post);
    }

    @PostMapping("/{id}/duplicate")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Post> duplicatePost(
            @PathVariable Long id,
            @RequestHeader(
                    value = "X-Service-Unavailable",
                    required = false
            ) Boolean serviceUnavailable,
            @RequestHeader(
                    value = "X-Internal-Error",
                    required = false
            ) Boolean internalError
    ) {
        if (Boolean.TRUE.equals(serviceUnavailable)) {
            throw new ServiceUnavailableException(
                    "서비스를 일시적으로 사용할 수 없습니다."
            );
        }

        if (Boolean.TRUE.equals(internalError)) {
            throw new RuntimeException("테스트용 서버 오류");
        }

        Post post = postService.duplicatePost(id);

        return ApiResponse.success(post);
    }

    @GetMapping
    public ApiResponse<List<Post>> listPosts() {
        return ApiResponse.success(postService.listPosts());
    }

    @GetMapping("/{id}")
    public ApiResponse<Post> findPost(
            @PathVariable Long id
    ) {
        return ApiResponse.success(postService.findPost(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Post> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequest request
    ) {
        request.validate();

        Post post = postService.updatePost(
                id,
                request.title(),
                request.content()
        );

        return ApiResponse.success(post);
    }

    @PutMapping("/{id}/title")
    public ApiResponse<Post> updateTitle(
            @PathVariable Long id,
            @RequestBody TitleRequest request
    ) {
        request.validate();

        Post post = postService.updateTitle(
                id,
                request.title()
        );

        return ApiResponse.success(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id
    ) {
        postService.deletePost(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPosts() {
        postService.deleteAllPosts();
    }
}