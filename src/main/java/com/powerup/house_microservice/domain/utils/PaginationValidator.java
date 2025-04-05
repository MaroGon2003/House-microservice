package com.powerup.house_microservice.domain.utils;

public class PaginationValidator {

    private PaginationValidator() {}

    public static void validatePaginationParameters(int page, int size,  String sortDirection) {

        if (page < DomainConstants.MIN_PAGE_INDEX) {
            throw new IllegalArgumentException(DomainConstants.PAGE_INDEX_NEGATIVE_ERROR);
        }
        if (size < DomainConstants.MIN_PAGE_SIZE) {
            throw new IllegalArgumentException(DomainConstants.PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR);
        }
        if (!sortDirection.equalsIgnoreCase(DomainConstants.ASC) && !sortDirection.equalsIgnoreCase(DomainConstants.DESC)) {
            throw new IllegalArgumentException(DomainConstants.SORT_DIRECTION_INVALID_ERROR);
        }
    }
}
