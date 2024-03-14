package app.dao;

import app.dto.MovementDto;

public interface MovementDao {
	public void createMovenment (MovementDto movenmentDto) throws Exception;
}
