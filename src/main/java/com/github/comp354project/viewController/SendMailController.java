package com.github.comp354project.viewController;

import javafx.fxml.FXML;
import com.github.comp354project.utils.SendMail;



public class SendMailController {
    @FXML
    public void SendMailNow(){
        SendMail.startMail();
    }
    }
