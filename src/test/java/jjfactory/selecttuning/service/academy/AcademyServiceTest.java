package jjfactory.selecttuning.service.academy;

import jjfactory.selecttuning.domain.academy.Academy;
import jjfactory.selecttuning.repository.academy.AcademyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AcademyServiceTest {

    @Autowired
    AcademyService academyService;

    @Autowired
    AcademyRepository academyRepository;

//    @BeforeEach
//    void setUp(){
//        List<Academy> academies = new ArrayList<>();
//
//        for (int i=0; i<10; i++){
//            Academy a = Academy.builder().name("academy" + i).build();
//            academies.add(a);
//        }
//        academyRepository.saveAll(academies);
//    }

    @Test
    @DisplayName("N+1 발생 테스트")
    void getSubjectNames() {
        //given
        List<String> subjectNames = academyService.getSubjectNames();

        //then
        assertThat(subjectNames.size()).isEqualTo(10);
    }
}