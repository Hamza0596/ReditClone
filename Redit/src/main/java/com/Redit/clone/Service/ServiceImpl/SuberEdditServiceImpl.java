package com.Redit.clone.Service.ServiceImpl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Exceptions.UserNameNotFoundException;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Subereddit;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.SubeEditRepository;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.SuberEdditService;

@Service
public class SuberEdditServiceImpl implements SuberEdditService{

	@Autowired
	SubeEditRepository subeEditRepository;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public void saveSubbeEredit(SuberedditDto suberedditDto) {
		
		User user = ModelMapperConverter.map(userRepo.findByUserName(suberedditDto.getUser().getUserName()).orElseThrow(()-> new UserNameNotFoundException("userNotFound")), User.class);
		Subereddit subereddit=Subereddit.builder()
		.name(suberedditDto.getName())
		.description(suberedditDto.getDescription())
		.user(user)
		.createdDate(Instant.now())
		.build();
		subeEditRepository.save(subereddit);
	}

	@Override
	public List<SuberedditDto> getAllSberEddits() {
		return ModelMapperConverter.mapAll(subeEditRepository.findAll(), SuberedditDto.class)	;	
	}

	@Override
	public SuberedditDto getSubbereditById(Long id) {
		return ModelMapperConverter.map(subeEditRepository.findById(id), SuberedditDto.class);
	}
	
	

}
