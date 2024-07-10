package org.hoshi.maplay.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestDAO {

	public int test(int no);

}
