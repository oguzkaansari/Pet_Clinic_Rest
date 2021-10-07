package com.petclinicrest.dto;

import com.petclinicrest.entities.Test;
import com.petclinicrest.repositories.TestRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TestDto {

    final TestRepository tRepo;

    public TestDto(TestRepository tRepo) {
        this.tRepo = tRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Test> test = tRepo.findById(id);
        Result result;
        if(test.isPresent()){
            result = new Result(true, 0, tRepo.count(), "", test);
        }else{
            result = new Result(false, 0, tRepo.count(), Messages.findTestFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> find(String no){
        Test test = tRepo.findByNo(no);
        Result result;
        if(test != null){
            result = new Result(true, 0, tRepo.count(), "", test);
        }else{
            result = new Result(false, 0, tRepo.count(), Messages.findTestFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Test> testPage = tRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findByStatus_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id, Integer page, Integer size){
        Page<Test> testPage = tRepo.findByStatus_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetId(Integer id){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findByPet_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByPetId(Integer id, Integer page, Integer size){
        Page<Test> testPage = tRepo.findByPet_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetChipno(String no){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findByPet_Chipno(no));
        return result.resultMap;
    }

    public Map<String, Object> listByPetChipno(String no, Integer page, Integer size){
        Page<Test> testPage = tRepo.findByPet_Chipno(no, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetCardno(String no){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findByPet_Cardno(no));
        return result.resultMap;
    }

    public Map<String, Object> listByPetCardno(String no, Integer page, Integer size){
        Page<Test> testPage = tRepo.findByPet_Cardno(no, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Test> testPage = tRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.searchByKeyAndStatusId(key, id));
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key, Integer page, Integer size){
        Page<Test> testPage = tRepo.searchByKeyAndStatusId(key, id, PageRequest.of(page, size));
        Result result = new Result(true, testPage.getTotalPages(), tRepo.count(), "", testPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Test test){
        Result result;
        if(test.getId() == null || test.getId() == 0){
            result = new Result(true, 0, tRepo.count(), Messages.saveSuccessMessage, tRepo.save(test));
        }else{
            result = new Result(true, 0, tRepo.count(), Messages.updateSuccessMessage, tRepo.saveAndFlush(test));
        }
        return result.resultMap;
    }

}
