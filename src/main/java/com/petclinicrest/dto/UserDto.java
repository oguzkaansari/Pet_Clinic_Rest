package com.petclinicrest.dto;

import com.petclinicrest.entities.ReferenceHolder;
import com.petclinicrest.entities.User;
import com.petclinicrest.repositories.ReferenceHolderRepository;
import com.petclinicrest.repositories.UserRepository;
import com.petclinicrest.services.MailService;
import com.petclinicrest.services.UserService;
import com.petclinicrest.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDto {

    final UserRepository uRepo;
    final ReferenceHolderRepository rRepo;
    final UserService uService;
    final MailService mService;


    public UserDto(UserRepository uRepo, UserService uService, ReferenceHolderRepository rRepo, MailService mService) {
        this.uRepo = uRepo;
        this.uService = uService;
        this.rRepo = rRepo;
        this.mService = mService;
    }

    public Map<String, Object> find(){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.findById(uService.getUserId()));
        return result.resultMap;
    }

    public Map<String, Object> find(Integer id){
        Optional<User> user = uRepo.findById(id);
        Result result;
        if(user.isPresent()){
            result = new Result(true, 0, uRepo.count(), "", user);
        }else{
            result = new Result(false, 0, uRepo.count(), Messages.findUserFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> find(String email){
        Optional<User> user = uRepo.findByEmailEqualsAllIgnoreCase(email);
        Result result;
        if(user.isPresent()){
            result = new Result(true, 0, uRepo.count(), "", user);
        }else{
            result = new Result(false, 0, uRepo.count(), Messages.findUserFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<User> userPage = uRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, userPage.getTotalPages(), uRepo.count(), "", userPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByRolesId(Integer id){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.findByRoles_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByRolesId(Integer id, Integer page, Integer size){
        Page<User> userPage = uRepo.findByRoles_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, userPage.getTotalPages(), uRepo.count(), "", userPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.findByStatus_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id, Integer page, Integer size){
        Page<User> userPage = uRepo.findByStatus_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, userPage.getTotalPages(), uRepo.count(), "", userPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<User> userPage = uRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, userPage.getTotalPages(), uRepo.count(), "", userPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key){
        Result result = new Result(true, 0, uRepo.count(), "", uRepo.searchByKeyAndStatusId(key, id));
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key, Integer page, Integer size){
        Page<User> userPage = uRepo.searchByKeyAndStatusId(key, id, PageRequest.of(page, size));
        Result result = new Result(true, userPage.getTotalPages(), uRepo.count(), "", userPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(User user) throws AuthenticationException {
        Result result;
        if(user.getId() == null || user.getId() == 0){
            result = new Result(true, 0, uRepo.count(), Messages.saveSuccessMessage, uService.register(user));
        }else{
            result = new Result(true, 0, 0, Messages.updateSuccessMessage, uRepo.saveAndFlush(user));
        }
        return result.resultMap;
    }

    public Map<String, Object> uploadImage(Integer id, MultipartFile file){
        Result result;
        Map<FileUpload, Object> fileUploadResult = ImageUpload.upload(file);
        if((Boolean)fileUploadResult.get(FileUpload.status)){
            User user = uRepo.getById(id);
            user.setImg(fileUploadResult.get(FileUpload.result).toString());
            result = new Result(true, 0, uRepo.count(), Messages.imageUploadSuccessMessage, uRepo.save(user));
        }else{
            result = new Result(false, 0, uRepo.count(), Messages.imageUploadFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> changePassword(){
        Result result;
        String reference = UUID.randomUUID().toString();
        ReferenceHolder referenceHolder = rRepo.save(new ReferenceHolder(uService.getUserId(), reference));
        if(mService.sendEmail(uService.getUser().getEmail(), Strings.changePasswordMailSubject, reference)){
            result = new Result(true, 0, 0, Messages.sendMailSuccessMessage, referenceHolder);
        }else{
            result = new Result(false, 0, uRepo.count(), Messages.sendMailFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> changePasswordConfirm(ReferenceHolder referenceHolder) {
        Result result;
        if(referenceHolder.getReference() == null || referenceHolder.getNewPassword() == null){
            result = new Result(false, 0, 0, Messages.refOrPasswordNullMessage, referenceHolder);
        }else{
            Optional<ReferenceHolder> referenceHolderOptional = rRepo.findByUid(uService.getUserId());
            if (referenceHolderOptional.isPresent()){
                if(!referenceHolder.getReference().equals(referenceHolderOptional.get().getReference())){
                    result = new Result(false, 0, 0, Messages.refIsNotMatchingMessage, referenceHolder);
                }else{
                    User user = uService.getUser();
                    user.setPassword(uService.encoder().encode(referenceHolder.getNewPassword()));
                    rRepo.deleteById(referenceHolderOptional.get().getId());
                    result = new Result(true, 0, 0, Messages.changePasswordSuccessMessage, uRepo.save(user));
                }
            }else{
                result = new Result(true, 0, 0, Messages.refNotFoundMessage, referenceHolder);
            }

        }
        return result.resultMap;
    }

}
