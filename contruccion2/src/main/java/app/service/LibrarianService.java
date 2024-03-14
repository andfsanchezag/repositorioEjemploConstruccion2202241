package app.service;

import app.dto.MaterialDto;
import app.dto.PersonDto;

public interface LibrarianService {
	public void createUser(PersonDto personDto) throws Exception;
	public void createMaterial(MaterialDto materialDto) throws Exception;
}
