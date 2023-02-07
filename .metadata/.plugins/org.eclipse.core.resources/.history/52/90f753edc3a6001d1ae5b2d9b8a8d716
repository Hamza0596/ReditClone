package com.Redit.clone.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Model.Subereddit;
import com.Redit.clone.Repository.SubeEditRepository;
import com.Redit.clone.Service.SuberEdditService;

@Service
public class SuberEdditServiceImpl implements SuberEdditService{

	@Autowired
	SubeEditRepository subeEditRepository;
	
	@Override
	public void saveSubbeEredit(SuberedditDto suberedditDto) {
		Subereddit subereddit=Subereddit.builder()
		.name(suberedditDto.getName())
		.description(suberedditDto.getDescription())
		.build();
		subeEditRepository.save(subereddit);
	}

}
