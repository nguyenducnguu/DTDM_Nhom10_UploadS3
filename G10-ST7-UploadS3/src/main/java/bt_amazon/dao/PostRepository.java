package bt_amazon.dao;

import org.springframework.data.repository.CrudRepository;
import bt_amazon.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

}
