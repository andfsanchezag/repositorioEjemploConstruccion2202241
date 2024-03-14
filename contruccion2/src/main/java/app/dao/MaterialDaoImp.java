package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dto.MaterialDto;
import app.models.Material;

public class MaterialDaoImp implements MaterialDao {
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public void createMaterial(MaterialDto materialDto) throws Exception {
		String query = "INSERT INTO MATERIAL(TITLE,ACTQUANTITY,QUANTITY,REGISTERDATE) VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setString(i++, materialDto.getTittle());
		preparedStatement.setInt(i++, materialDto.getActQuantity());
		preparedStatement.setInt(i++, materialDto.getQuantity());
		preparedStatement.setDate(i++, materialDto.getRegisterDate());
		preparedStatement.execute();
		preparedStatement.close();

	}
	
	@Override
	public void updateMaterial(MaterialDto materialDto) throws Exception {
		String query = "UPDATE MATERIAL SET ACTQUANTITY=?, QUANTITY=? WHERE ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setInt(i++, materialDto.getActQuantity());
		preparedStatement.setInt(i++, materialDto.getQuantity());
		preparedStatement.setInt(i++, materialDto.getId());
		preparedStatement.execute();
		preparedStatement.close();

	}


	@Override
	public MaterialDto findMaterialById(MaterialDto materialDto) throws Exception {
			String query = "SELECT ID,TITLE,ACTQUANTITY,QUANTITY FROM MATERIAL WHERE ID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, materialDto.getId());
			ResultSet resulSet = preparedStatement.executeQuery();
			if(resulSet.next()) {
				Material material = new Material();
				material.setId(resulSet.getInt("ID"));
				material.setTittle(resulSet.getString("TITLE"));
				material.setActQuantity(resulSet.getInt("ACTQUANTITY"));
				material.setQuantity(resulSet.getInt("QUANTITY"));
				resulSet.close();
				preparedStatement.close();
				return new MaterialDto(material);
			}
			resulSet.close();
			preparedStatement.close();
			return null;
		
	}
	
	
	

}
