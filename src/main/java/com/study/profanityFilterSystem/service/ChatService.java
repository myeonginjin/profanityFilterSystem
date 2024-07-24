package com.study.profanityFilterSystem.service;
import com.study.profanityFilterSystem.entity.Dialog;
import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.repository.DialogRepository;
import com.study.profanityFilterSystem.repository.UsersRepository;
import com.study.profanityFilterSystem.filtering.AhoCorasick;
import com.study.profanityFilterSystem.filtering.BadWordFiltering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.profanityFilterSystem.utils.BadWordLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ChatService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DialogRepository dialogRepository;

    private final AhoCorasick ahoCorasick;
    private final BadWordFiltering badWordFiltering;

    public ChatService() throws IOException {
        ahoCorasick = new AhoCorasick();
        badWordFiltering = new BadWordFiltering();
        initializeBadWords();
    }

    private void initializeBadWords() throws IOException {
        List<String> badWords = BadWordLoader.loadBadWords("badwords.txt");
        for (String word : badWords) {
            ahoCorasick.addKeyword(word);
        }
        ahoCorasick.buildFailureLinks();
        badWordFiltering.addBadWords(badWords);
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

        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        List<Dialog> recentDialogs = dialogRepository.findByUserIDAndTimestampAfter(user.getUserID(), threeDaysAgo);
        List<Dialog> filteredDialogs = new ArrayList<>();

        for (Dialog dialog : recentDialogs) {
            Set<String> badWordsFound = ahoCorasick.search(dialog.getMessage());
            if (!badWordsFound.isEmpty() || badWordFiltering.checkBadWord(dialog.getMessage())) {
                filteredDialogs.add(dialog);
            }
        }

        return filteredDialogs;
    }
}