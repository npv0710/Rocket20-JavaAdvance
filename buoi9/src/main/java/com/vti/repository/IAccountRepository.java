package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>{
	@Modifying
	@Transactional
	@Query("DELETE Account ac WHERE ac.id IN(:Ids)")
	void deleteMultilAccount(List<Integer> Ids);
	
//	@Query("SELECT ac FROM Account ac WHERE ac.department IS NULL")
//	List<Account> getListAccountsAddDepartment();
	
	boolean existsByUserName(String username);

	Account findByUserName(String username);
	
	@Query("UPDATE Account SET password =: newPassword WHERE id =: id")
	List<Account> changePasswordAccount(int id, String newPaswword);
	
//	@Query("SELECT ac FROM Account ac WHERE ac.email =:email")
//	Account findAccountByEmail(String email);
	
//	@Query("SELECT ac FROM Account ac WHERE ac.email = ?1")
//	Account findAccountByEmail(String email);
	
	@Query("SELECT ac FROM Account ac WHERE ac.email =:emailParameter")
	Account findAccountByEmail(@Param("emailParameter") String email);
}
