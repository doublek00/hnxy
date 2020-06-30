package com.hnxy.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.hnxy.utils.Jdbcutils;

public class testTransaction {

	public void transaction(String from, String to, int salary) throws Exception {
		DataSource dataSource = Jdbcutils.getDataSoure();
		QueryRunner qr = new QueryRunner(dataSource);
		
		Connection conn = null;
        conn = dataSource.getConnection();
        
        conn.setAutoCommit(false);
		String sql1 = "update employye set salary = salary-? where name =? ";

		Object[] params1 = {salary, from };

		int i1 = qr.update(conn, sql1, params1);
		
		//int n =1/0;

		String sql2 = "update employye set salary = salary +? where name=?";
		Object[] params2 = { salary, to};
		int i2 = qr.update(conn, sql2, params2);
		
		conn.commit();
		conn.rollback();
	}
	@Test
	public void test() throws Exception {
		this.transaction("张三", "李四", 400);
	}
}
