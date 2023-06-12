package com.liupanshui.msmservice.service;

import com.liupanshui.msmservice.service.impl.MsmserviceImpl;

import java.util.Map;

public interface Msmservice {

    boolean send(Map<String, Object> param, String phone);
}
