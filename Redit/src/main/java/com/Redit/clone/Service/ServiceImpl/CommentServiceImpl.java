package com.Redit.clone.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.CommentDto;
import com.Redit.clone.Dto.UserDto;
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
		User user = userRepo.findById(commentDto.getUser().getId()).orElseThrow(()->new UserNameNotFoundException("no user was found with this id"));
		UserDto userDto= ModelMapperConverter.map(user, UserDto.class);
		commentDto.setUser(userDto);
		Comment comment=ModelMapperConverter.map(commentDto, Comment.class);
		CommentDto returnedComment = ModelMapperConverter.map(commentRepo.save(comment), CommentDto.class);
		String message= commentDto.getUser().getUserName()+" "+"commented in your post ";
		this.sendCommentNotification(message, comment.getPost().getUser(),comment.getUser());
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
	public List<CommentDto> getAllCommentsByPostId(Long PostId) {
		
		return ModelMapperConverter.mapAll(commentRepo.findByPostPostId(PostId), CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllCommentsByUserId(Long id) {
		return ModelMapperConverter.mapAll(commentRepo.findByUserId(id), CommentDto.class);
	}

}
