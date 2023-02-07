package com.Redit.clone.Service;

import java.util.List;

import com.Redit.clone.Dto.SuberedditDto;

public interface SuberEdditService  {
	public void saveSubbeEredit(SuberedditDto SuberedditDto);
	public List<SuberedditDto> getAllSberEddits();
	public SuberedditDto getSubbereditById(Long id);

}
