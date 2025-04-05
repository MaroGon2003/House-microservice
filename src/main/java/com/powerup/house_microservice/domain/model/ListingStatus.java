package com.powerup.house_microservice.domain.model;

public enum ListingStatus {
    PUBLISHED,            // Publicada y visible para los usuarios
    LISTING_PAUSED,       // Publicación pausada temporalmente
    TRANSACTION_IN_PROGRESS,  // Venta en proceso
    TRANSACTION_COMPLETED     // Venta finalizada con éxito
}
