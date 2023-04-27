package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Score {

    private String name; //학생이름
    private int kor, eng, math; //국, 영, 수 점수

    private int stuNum; //학번
    private int total; //총점
    private double average; //평균
    private Grade grade; //학점

    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        changeScore(dto);
    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    public void changeScore(ScoreRequestDTO dto) {
        this.stuNum = dto.getStuNum();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); //총점, 평균 계산
        calcGrade(); //학점 계산
    }


    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.average = Math.round((total / 3.0) * 100) / 100.0;
    }
    private void calcGrade() {
        switch((int)average / 10) {
            case 10 :
            case 9 : grade = Grade.A; break;
            case 8 : grade = Grade.B; break;
            case 7 : grade = Grade.C; break;
            case 6 : grade = Grade.D; break;
            default : grade = Grade.F;
        }
    }
}
