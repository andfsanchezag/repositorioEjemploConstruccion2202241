package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.config.MYSQLConnection;
import app.dto.MovementDto;

public class MovementDaoImp implements MovementDao{
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public void createMovenment(MovementDto movementDto) throws Exception {
		// TODO Auto-generated method stub
		String query = "INSERT INTO MOVEMENT(PERSON_ID,MATERIAL_ID,TYPE,REGISTERDATE) VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setLong(i++, movementDto.getPersonId());
		preparedStatement.setInt(i++, movementDto.getMaterialId());
		preparedStatement.setString(i++, movementDto.getType());
		preparedStatement.setDate(i++,movementDto.getMovenmentDate());
		preparedStatement.execute();
		preparedStatement.close();
	}
	
}
