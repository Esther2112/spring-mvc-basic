package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "select * from tbl_score";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                scoreList.add(new Score(rs));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public boolean save(Score score) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql = "insert into tbl_score (name, kor, eng, math, total, average, grade) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate();

            if(result == 1){
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql = "delete from tbl_score where stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            int result = pstmt.executeUpdate();
            if(result > 0){
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Score findByStuNum(int stuNum) {
        Score score = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "select * from tbl_score where stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                score = new Score(rs);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return score;
    }

    //수정하기 구현 안함
}
