package ua.ddovgal.brainshareApi.exception

class InvalidDataProvidedException(generalReasonMessage: String, val reasons: Map<String, String> = emptyMap())
    : RuntimeException(generalReasonMessage)