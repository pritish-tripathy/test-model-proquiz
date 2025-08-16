package com.quizpro.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.quizpro.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/*
@Repository
public class UserDAO {
	// use hibernate here. move these to app.pro
	@Autowired
	DataSource dataSource;

	public boolean verifyUser(String userIdOrUsername, String password) {
		try (
				Connection con = dataSource.getConnection();
				) {
			String sqlQuery = "SELECT * FROM myusers WHERE (userId=? OR username=?) AND password=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, userIdOrUsername);
			ps.setString(2, userIdOrUsername);
			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public void updateOTP(String userIdOrUsername, String otp) {
		try (
				Connection con = dataSource.getConnection();
				) {
			String sqlQuery = "UPDATE myusers SET otp=? WHERE userId=? OR username=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, otp);
			ps.setString(2, userIdOrUsername);
			ps.setString(3, userIdOrUsername);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean verifyOTP(String userIdOrUsername, String otp) {
		try (
				Connection con = dataSource.getConnection();
				) {
			String sqlQuery = "SELECT * FROM myusers WHERE (userId=? OR username=?) AND otp=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, userIdOrUsername);
			ps.setString(2, userIdOrUsername);
			ps.setString(3, otp);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public String getEmailByUserIdOrUsername(String userIdOrUsername) {
		String sql = "SELECT email FROM myusers WHERE userId = ? OR username = ?";
		try (
				Connection con = dataSource.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)
						) {
			ps.setString(1, userIdOrUsername);
			ps.setString(2, userIdOrUsername);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("email");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean updatePassword(String userIdOrUsername, String newPassword) {
		try (Connection con = dataSource.getConnection()) {
			String sql = "UPDATE myusers SET password=? WHERE userId=? OR username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, userIdOrUsername);
			ps.setString(3, userIdOrUsername);
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

 */

@Repository
public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean verifyUser(String userIdOrUsername, String password) {
		String jpql = "SELECT u FROM User u WHERE (CAST(u.userId AS string) = :input OR u.username = :input) AND u.password = :password";
		try {
			return !entityManager.createQuery(jpql, User.class)
					.setParameter("input", userIdOrUsername)
					.setParameter("password", password)
					.getResultList()
					.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public void updateOTP(String userIdOrUsername, String otp) {
		String jpql = "UPDATE User u SET u.otp = :otp WHERE CAST(u.userId AS string) = :input OR u.username = :input";
		entityManager.createQuery(jpql)
				.setParameter("otp", otp)
				.setParameter("input", userIdOrUsername)
				.executeUpdate();
	}

	@Transactional
	public boolean verifyOTP(String userIdOrUsername, String otp) {
		String jpql = "SELECT u FROM User u WHERE (CAST(u.userId AS string) = :input OR u.username = :input) AND u.otp = :otp";
		return !entityManager.createQuery(jpql, User.class)
				.setParameter("input", userIdOrUsername)
				.setParameter("otp", otp)
				.getResultList()
				.isEmpty();
	}

	@Transactional
	public String getEmailByUserIdOrUsername(String userIdOrUsername) {
		String jpql = "SELECT u.email FROM User u WHERE CAST(u.userId AS string) = :input OR u.username = :input";
		return entityManager.createQuery(jpql, String.class)
				.setParameter("input", userIdOrUsername)
				.getSingleResult();
	}

	@Transactional
	public boolean updatePassword(String userIdOrUsername, String newPassword) {
		String jpql = "UPDATE User u SET u.password = :pass WHERE CAST(u.userId AS string) = :input OR u.username = :input";
		int updated = entityManager.createQuery(jpql)
				.setParameter("pass", newPassword)
				.setParameter("input", userIdOrUsername)
				.executeUpdate();
		return updated > 0;
	}

	public String findUsernameByUserId(Long userId) {
		String jpql = "SELECT u.username FROM User u WHERE u.userId = :userId";
		return entityManager.createQuery(jpql, String.class)
				.setParameter("userId", userId)
				.getSingleResult();
	}
	
	// ADD THIS METHOD
	@Transactional
	public String getRoleByUserIdOrUsername(String userIdOrUsername) {
	    String jpql = "SELECT u.role FROM User u WHERE CAST(u.userId AS string) = :input OR u.username = :input";
	    return entityManager.createQuery(jpql, String.class)
	            .setParameter("input", userIdOrUsername)
	            .getSingleResult();
	}

}