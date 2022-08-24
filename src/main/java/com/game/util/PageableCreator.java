package com.game.util;

import com.game.controller.PlayerOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {
    private final static int DEFAULT_PAGE_SIZE = 3;
    private final static int DEFAULT_PAGE_NUMBER = 0;
    private final static PlayerOrder DEFAULT_PLAYER_ORDER = PlayerOrder.ID;
    public static Pageable createPageableFromParams(Integer pageSize, Integer pageNumber, PlayerOrder playerOrder) {
        int size = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int number = pageNumber != null ? pageNumber : DEFAULT_PAGE_NUMBER;
        String sort = playerOrder != null ? playerOrder.getFieldName() : DEFAULT_PLAYER_ORDER.getFieldName();
        return PageRequest.of(number, size, Sort.by(sort));
    }
}
