package com.study.profanityFilterSystem.service;

import com.study.profanityFilterSystem.entity.Dialog;
import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.repository.DialogRepository;
import com.study.profanityFilterSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DialogRepository dialogRepository;

    // 사용자를 확인하고 필요 시 새로 생성하는 메서드
    public Users getOrCreateUser(String userName) {
        Users user = usersRepository.findByUserName(userName);
        if (user == null) {
            user = new Users();
            user.setUserName(userName);
            usersRepository.save(user);
        }
        return user;
    }

    public Dialog saveDialog(Long userID, Long partnerUserID, String message) {
        Dialog dialog = new Dialog();
        dialog.setUserID(userID);
        dialog.setPartnerUserID(partnerUserID);
        dialog.setMessage(message);
        dialog.setTimestamp(LocalDateTime.now());
        dialogRepository.save(dialog);
        return dialog;
    }

    // 특정 유저의 최근 3일간의 대화 내역 조회
    public List<Dialog> getRecentDialogs(String userName) throws Exception {
        Users user = usersRepository.findByUserName(userName);
        if (user == null) {
            throw new Exception("해당 이름의 유저를 찾을 수 없습니다.");
        }

        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        return dialogRepository.findByUserIDAndTimestampAfter(user.getUserID(), threeDaysAgo);
    }
}
