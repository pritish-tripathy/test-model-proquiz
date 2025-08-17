package com.quizpro.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.quizpro.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public String getRoleByUserIdOrUsername(String userIdOrUsername) {
	    String jpql = "SELECT u.role FROM User u WHERE CAST(u.userId AS string) = :input OR u.username = :input";
	    return entityManager.createQuery(jpql, String.class)
	            .setParameter("input", userIdOrUsername)
	            .getSingleResult();
	}

}