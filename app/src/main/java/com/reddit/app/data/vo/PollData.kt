package com.reddit.app.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PollData(
    val is_prediction: Boolean? = false,
    val options: List<Option>? = emptyList(),
    val resolved_option_id: String? = "",
    val total_stake_amount: String? = "",
    val total_vote_count: Int? = 0,
    val tournament_id: String? = "",
    val user_selection: String? = "",
    val user_won_amount: String? = "",
    val voting_end_timestamp: Long? = 0
) : Parcelable
