package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.service.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class TimerServiceimpl implements TimerService {

    private boolean ifLogin;
    private Long start = 0L, end = 0L;

    @Override
    public Long getLoginTime() {
        if (ifLogin) {
            start = System.currentTimeMillis();
        } else {
            end = System.currentTimeMillis();
        }
        return end - start;
    }

    @Override
    public void changeLoginStatus(boolean status) {
        ifLogin = status;
    }
}
