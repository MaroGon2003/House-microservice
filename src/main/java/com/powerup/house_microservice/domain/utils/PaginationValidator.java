package com.powerup.house_microservice.domain.utils;

public class PaginationValidator {

    private PaginationValidator() {
        throw new UnsupportedOperationException(ErrorMessages.UTILITY_CLASS_INSTANTIATION_ERROR);
    }

    public static void validatePaginationParameters(int page, int size,  String sortDirection) {
        if (page < 0) {
            throw new IllegalArgumentException(ErrorMessages.PAGE_INDEX_NEGATIVE_ERROR);
        }
        if (size <= 0) {
            throw new IllegalArgumentException(ErrorMessages.PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR);
        }
        if (!sortDirection.equalsIgnoreCase(MessageConstants.ASC) && !sortDirection.equalsIgnoreCase(MessageConstants.DESC)) {
            throw new IllegalArgumentException(ErrorMessages.SORT_DIRECTION_INVALID_ERROR);
        }
    }
}
