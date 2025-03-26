package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.StateRequestDto;

public interface IStateHandler {

    void create(StateRequestDto requestDto);

}
