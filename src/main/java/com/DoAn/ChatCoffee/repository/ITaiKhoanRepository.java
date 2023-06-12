package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITaiKhoanRepository extends JpaRepository<Taikhoan, Long> {


    @Query("SELECT tk FROM Taikhoan tk WHERE tk.username = :username")
    public Taikhoan findByUsername(@Param("username") String username);
/*  phương thức get thì không dùng modifying,Transactional
    @Modifying
    @Transactional*/
    @Query("SELECT tk.id FROM Taikhoan tk WHERE tk.username = ?1")
    Long getUserIdByUsername(String username);

    @Query(value = "SELECT r.name FROM role r INNER JOIN user_role ur " +
            "ON r.id = ur.role_id WHERE ur.user_id = ?1", nativeQuery = true)
    public String[] getRoleOfUser(Long userId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long userId, Long roleId);

}
