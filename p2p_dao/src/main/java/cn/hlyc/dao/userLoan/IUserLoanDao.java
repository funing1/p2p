package cn.hlyc.dao.userLoan;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hlyc.domain.jiekuan.UserLoan;


public interface IUserLoanDao extends JpaRepository<UserLoan, Integer>{

}
