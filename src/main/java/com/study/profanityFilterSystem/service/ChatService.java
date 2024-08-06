package com.study.profanityFilterSystem.service;

import com.study.profanityFilterSystem.entity.Dialog;
import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.repository.DialogRepository;
import com.study.profanityFilterSystem.repository.UsersRepository;
import com.study.profanityFilterSystem.service.filtering.AhoCorasick;
import com.study.profanityFilterSystem.service.filtering.PatternFiltering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.profanityFilterSystem.utils.wordListLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum WordType {
    banWord,
    allowWord
}

@Service
public class ChatService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DialogRepository dialogRepository;

    private final AhoCorasick ahoCorasick;
    private final PatternFiltering patternFiltering;

    public ChatService() throws IOException {
        ahoCorasick = new AhoCorasick();
        patternFiltering = new PatternFiltering();
        initializeBanWords();
    }

    private void initializeBanWords() throws IOException {

        //금칙어 노드 트라이에 삽입
        List<String> banWords = wordListLoader.loadWords(String.valueOf(WordType.banWord));
//        for (String word : banWords) {
//            System.out.println(word);
//            ahoCorasick.addKeyword(String.valueOf(WordType.banWord), word);
//        }
        //허용어 노드 트라이에 삽입
//        List<String> allowWords = wordListLoader.loadWords(String.valueOf(WordType.allowWord));
//        for (String word : allowWords) {
//            ahoCorasick.addKeyword(String.valueOf(WordType.allowWord), word);
//        }

        //ahoCorasick.buildFailureLinks();

        //왜 아래가 필요한자?
        patternFiltering.addWords(String.valueOf(WordType.banWord), banWords);
        //patternFiltering.addWords(String.valueOf(WordType.allowWord), allowWords);
    }

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

    public List<Dialog> getRecentDialogs(String userName) throws Exception {
        Users user = usersRepository.findByUserName(userName);
        if (user == null) {
            throw new Exception("해당 이름의 유저를 찾을 수 없습니다.");
        }

        List<Dialog> recentDialogs = dialogRepository.findByUserID(user.getUserID());
        List<Dialog> filteredDialogs = new ArrayList<>();

        for (Dialog dialog : recentDialogs) {
            if (patternFiltering.checkBanWord(dialog.getMessage())) {
                filteredDialogs.add(dialog);
            }
        }

        return filteredDialogs;
    }
}
