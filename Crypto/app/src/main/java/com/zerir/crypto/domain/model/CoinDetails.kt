package com.zerir.crypto.domain.model

import com.zerir.crypto.data.remote.dto.TeamMember

data class CoinDetails(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>,
)