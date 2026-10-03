package kr.ac.jbnu.hnr.wsd.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String title, String content) {
        Post post = new Post(title, content);
        return postRepository.save(post);
    }

    public List<Post> listPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("게시글을 찾을 수 없습니다.")
                );
    }

    public Post updatePost(Long id, String title, String content) {
        Post post = findPost(id);
        post.update(title, content);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    public void deleteAllPosts() {
        postRepository.deleteAll();
    }

    public Post updateTitle(Long id, String title) {
        Post post = findPost(id);
        post.update(title, post.getContent());
        return postRepository.save(post);
    }

    public Post duplicatePost(Long id) {
        Post originalPost = findPost(id);

        Post duplicatedPost = new Post(
                originalPost.getTitle(),
                originalPost.getContent()
        );

        return postRepository.save(duplicatedPost);
    }
}