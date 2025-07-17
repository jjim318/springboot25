package org.mbc.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info("등록용 테스트 서비스 실행중");
        log.info(boardService.getClass().getName());//객체 생성용 테스트

        BoardDTO boardDTO = BoardDTO.builder()
                .title("서비스에서 만든 제목")
                .content("서비스에서 만든 내용")
                .writer("서비스님")
                .build();//세터대신 @Builder
        Long bno = boardService.register(boardDTO);//서비스 구현 메서드로 동작

        log.info("테스트 결과 bno"+bno);

        //Hibernate:
        //    insert
        //    into
        //        board
        //        (content, moddate, regdate, title, writer)
        //    values
        //        (?, ?, ?, ?, ?)

    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("수정된 제목 101")
                .content("수정된 내용 101")
                .build();
        boardService.modify(boardDTO);//프론트에서 객체가 넘어가 수정이 되었는지 테스트
        //Hibernate:
        //    select
        //        b1_0.bno,
        //        b1_0.content,
        //        b1_0.moddate,
        //        b1_0.regdate,
        //        b1_0.title,
        //        b1_0.writer
        //    from
        //        board b1_0
        //    where
        //        b1_0.bno=?
        //Hibernate:
        //    update
        //        board
        //    set
        //        content=?,
        //        moddate=?,
        //        title=?,
        //        writer=?
        //    where
        //        bno=?
    }
}
