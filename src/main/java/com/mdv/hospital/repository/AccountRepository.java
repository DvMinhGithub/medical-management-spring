package com.mdv.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mdv.hospital.entity.Account;
import com.mdv.hospital.enums.AccountType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByPhone(String phone);

    List<Account> findAllByType(AccountType type);

    @Query(
            "SELECT a FROM Account a WHERE a.type = 'PATIENT' AND EXISTS (SELECT o FROM Order o WHERE o.patient = a AND o.status = 'DONE')")
    List<Account> findPatientsWithDoneOrders();

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByCode(String code);
}
