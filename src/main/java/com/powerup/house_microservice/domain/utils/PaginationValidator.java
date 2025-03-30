package com.powerup.house_microservice.domain.utils;

public class PaginationValidator {


    private static final int MIN_PAGE_INDEX = 0;
    private static final int MIN_PAGE_SIZE = 1;

    private PaginationValidator() {}

    public static void validatePaginationParameters(int page, int size,  String sortDirection) {

        if (page < MIN_PAGE_INDEX) {
            throw new IllegalArgumentException(ErrorMessages.PAGE_INDEX_NEGATIVE_ERROR);
        }
        if (size < MIN_PAGE_SIZE) {
            throw new IllegalArgumentException(ErrorMessages.PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR);
        }
        if (!sortDirection.equalsIgnoreCase(MessageConstants.ASC) && !sortDirection.equalsIgnoreCase(MessageConstants.DESC)) {
            throw new IllegalArgumentException(ErrorMessages.SORT_DIRECTION_INVALID_ERROR);
        }
    }
}
