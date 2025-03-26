package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.handler.IStateHandler;
import com.powerup.house_microservice.application.mapper.request.IStateRequestMapper;
import com.powerup.house_microservice.domain.api.IStateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateHandler implements IStateHandler {

    private final IStateServicePort stateServicePort;
    private final IStateRequestMapper stateRequestMapper;

    @Override
    public void create(StateRequestDto requestDto) {

        stateServicePort.create(stateRequestMapper.toStateModel(requestDto));

    }

}
