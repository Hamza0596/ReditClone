package com.Redit.clone.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.CommentDto;
import com.Redit.clone.Exceptions.PostNotFoundException;
import com.Redit.clone.Exceptions.UserNameNotFoundException;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Comment;
import com.Redit.clone.Model.NotificationEmail;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.CommentRepository;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.CommentService;
import com.Redit.clone.Service.MailService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	PostRepository postRepo;
	@Autowired 
	MailService mailService;

	@Override
	public CommentDto addComment(CommentDto commentDto)  {
		System.out.println(commentDto);
		User user = (userRepo.findByUserName(commentDto.getUserName())).orElseThrow(()->new UserNameNotFoundException("Utilisateur introuvale"));
		Comment comment = ModelMapperConverter.map(commentDto, Comment.class);
		Post post = postRepo.findById(commentDto.getPostId()).orElseThrow(()->new PostNotFoundException("post introuvale"));
		comment.setUser(user);
		comment.setPost(post);
		CommentDto returnedComment = ModelMapperConverter.map(commentRepo.save(comment), CommentDto.class);
		returnedComment.setPostId(comment.getPost().getPostId());
		returnedComment.setUserName(comment.getUser().getUserName());
		String message= commentDto.getUserName()+" "+"commented in your post ";
		this.sendCommentNotification(message, post.getUser(),comment.getUser());
		return returnedComment;

	}
	
	private void sendCommentNotification(String message, User user, User userCom) {
		NotificationEmail notification = new NotificationEmail();
		notification.setRecipient(user.getEmail());
		notification.setBody(message);
		notification.setSubject(userCom.getUserName()+" "+"commented  in your post");
		mailService.sendMail(notification);
	}

	@Override
	public List<CommentDto> getAllCommentsByPost(Long PostId) {
		return ModelMapperConverter.mapAll(commentRepo.findByPostPostId(PostId), CommentDto.class);
	}

}
