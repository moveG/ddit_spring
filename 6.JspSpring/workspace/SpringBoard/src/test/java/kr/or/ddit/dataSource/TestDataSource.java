package kr.or.ddit.dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
public class TestDataSource {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	@Before
	public void init() throws SQLException {
		conn = dataSource.getConnection();
	}
	
	@Test
	public void testSQL() throws Exception {
		Assert.assertNotNull(conn);
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT * FROM MEMBER";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		while(rs.next()) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(rs.getString("id"));
			memberVO.setPwd(rs.getString("pwd"));
			
			memberList.add(memberVO);
		}
		
		rs.close();
		stmt.close();
		
		Assert.assertEquals(43, memberList.size());
	}
}
