package me.centralhardware.telegram

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.AppConfig
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommandWithArgs
import dev.inmo.tgbotapi.longPolling

@OptIn(Warning::class)
suspend fun main() {
    AppConfig.init("SpotTimeBot")
    longPolling {
        onCommandWithArgs("spot") { msg, args ->
            val flights = OpenSky.getFlight(args.first())
        }
    }.second.join()
}