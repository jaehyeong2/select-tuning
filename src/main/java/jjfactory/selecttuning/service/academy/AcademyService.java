package jjfactory.selecttuning.service.academy;


import jjfactory.selecttuning.domain.academy.Academy;
import jjfactory.selecttuning.repository.academy.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AcademyService {
    private final AcademyRepository academyRepository;

    @Transactional(readOnly = true)
    public List<String> getSubjectNames(){
        return extractSubjectNames(academyRepository.findAll());
    }

    private List<String> extractSubjectNames(List<Academy> academies){
        return academies.stream().map(a-> a.getSubjects().get(0).getName())
                .collect(Collectors.toList());
    }
}
