package com.spring.mvc.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    /*
        1. 사용자가 파일을 업로드했을때
           중복이 없는 새로운 파일명을 생성해서
           해당 파일명으로 업로드 하는 메서드
     */

    /**
     *
     * @param file - 사용자가 업로드한 파일 객체
     * @param rootPath - 서버에 파일업로드 루트 경로 (ex: C:/spring-prj/upload/)
     * @return - 업로드가 완료된 파일의 위치 경로 (ex: /2023/05/16/스크린샷.jpg)
     */

    public static String uploadFile(
            MultipartFile file
            , String rootPath
    ) {
        //원본 파일명을 중복이 없는 랜덤 이름으로 변경
        //ex) 상어.png -> fkfkkdsfk-sdfkjs-skdjfdf_상어.png
        String newFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        //이 파일을 저장할 날짜별 폴더를 생성
        //C:/spring-prj/upload/2023/05/16/djsdfkjhsdf_스크린샷.png
        String newPath = makeDateFormatDirectory(rootPath);

        //파일업로드 수행
        try {
            file.transferTo(new File(newPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

    /**
     * 루트경로를 받아서 일자별로 폴더를 생성한 후
     * 루트경로 + 날짜 폴더 경로를 리턴
     * @param rootPath - 파일 업로드 루트 경로
     * @return - 날짜 폴더 경로가 포함된 새로운 업로드 경로
     */
    private static String makeDateFormatDirectory(String rootPath){

        //오늘 연월일 날짜정보 가져오기
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        List<String> date = List.of(
                String.valueOf(year)
                , len2(month)
                , len2(day)
        );

        String directoryPath = rootPath;
        for (String s : date) {
            directoryPath += "/" + s;
            File f = new File(directoryPath);
            if(!f.exists()){
                f.mkdir();
            }
        }

        return directoryPath;
    }

    private static String len2(int n){
        return new DecimalFormat("00").format(n);
    }
}
