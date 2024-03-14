package app.dao;

import app.dto.MaterialDto;

public interface MaterialDao {
	public void createMaterial(MaterialDto materialDto) throws Exception;
	public void updateMaterial(MaterialDto materialDto) throws Exception;
	public MaterialDto findMaterialById(MaterialDto materialDto) throws Exception;
}
