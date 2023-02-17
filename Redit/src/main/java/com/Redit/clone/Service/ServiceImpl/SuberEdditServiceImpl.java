package com.Redit.clone.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Subereddit;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.SubeEditRepository;
import com.Redit.clone.Service.SuberEdditService;

@Service
public class SuberEdditServiceImpl implements SuberEdditService{

	@Autowired
	SubeEditRepository subeEditRepository;
	
	@Override
	public void saveSubbeEredit(SuberedditDto suberedditDto) {
		User user = ModelMapperConverter.map(suberedditDto.getUser(), User.class);
		Subereddit subereddit=Subereddit.builder()
		.name(suberedditDto.getName())
		.description(suberedditDto.getDescription())
		.user(user)
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
