package me.centralhardware.telegram

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.AppConfig
import dev.inmo.tgbotapi.longPolling

@OptIn(Warning::class)
suspend fun main() {
    AppConfig.init("app")
    longPolling {

    }
}