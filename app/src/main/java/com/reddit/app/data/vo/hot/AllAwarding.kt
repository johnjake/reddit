package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllAwarding(
    val award_sub_type: String?,
    val award_type: String?,
    val awardings_required_to_grant_benefits: String? = "",
    val coin_price: Int?,
    val coin_reward: Int?,
    val count: Int?,
    val days_of_drip_extension: Int?,
    val days_of_premium: Int?,
    val description: String?,
    val end_date: String? = "",
    val giver_coin_reward: String? = "",
    val icon_format: String? = "",
    val icon_height: Int?,
    val icon_url: String?,
    val icon_width: Int?,
    val id: String?,
    val is_enabled: Boolean?,
    val is_new: Boolean?,
    val name: String?,
    val penny_donate: String? = "",
    val penny_price: String? = "",
    val resized_icons: List<ResizedIcon>?,
    val resized_static_icons: List<ResizedStaticIcon>? = emptyList(),
    val start_date: String? = "",
    val static_icon_height: Int?,
    val static_icon_url: String?,
    val static_icon_width: Int?,
    val subreddit_coin_reward: Int?,
    val subreddit_id: String? = "",
    val tiers_by_required_awardings: ResizedIcon? = null
) : Parcelable
