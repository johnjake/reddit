package com.reddit.app.data.vo

import android.os.Parcelable
import com.reddit.app.data.vo.hot.RedditVideoPreview
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetails(
    val all_awardings: List<String>? = emptyList(),
    val allow_live_comments: Boolean? = false,
    val approved_at_utc: String? = "",
    val approved_by: String? = "",
    val archived: Boolean? = false,
    val author: String? = "",
    val author_flair_background_color: String? = "",
    val author_flair_css_class: String? = "",
    val author_flair_richtext: List<String>? = emptyList(),
    val author_flair_template_id: String? = "",
    val author_flair_text: String? = "",
    val author_flair_text_color: String? = "",
    val author_flair_type: String? = "",
    val author_fullname: String? = "",
    val author_patreon_flair: Boolean? = false,
    val author_premium: Boolean? = false,
    val awarders: List<String>? = emptyList(),
    val banned_at_utc: String? = "",
    val banned_by: String? = "",
    val can_gild: Boolean? = false,
    val can_mod_post: Boolean? = false,
    val category: String? = "",
    val clicked: Boolean? = false,
    val content_categories: String? = "",
    val contest_mode: Boolean? = false,
    val created: Double? = 0.0,
    val created_utc: Double? = 0.0,
    val discussion_type: String? = "",
    val distinguished: String? = "",
    val domain: String? = "",
    val downs: Int? = 0,
    val edited: Boolean? = false,
    val gilded: Int? = 0,
    val gildings: Gildings? = null,
    val hidden: Boolean? = false,
    val hide_score: Boolean? = false,
    val id: String? = "",
    val is_created_from_ads_ui: Boolean? = false,
    val is_crosspostable: Boolean? = false,
    val is_meta: Boolean? = false,
    val is_original_content: Boolean? = false,
    val is_reddit_media_domain: Boolean? = false,
    val is_robot_indexable: Boolean? = false,
    val is_self: Boolean? = false,
    val is_video: Boolean? = false,
    val likes: String? = "",
    val link_flair_background_color: String? = "",
    val link_flair_css_class: String? = "",
    val link_flair_richtext: List<String>? = emptyList(),
    val link_flair_template_id: String? = "",
    val link_flair_text: String? = "",
    val link_flair_text_color: String? = "",
    val link_flair_type: String? = "",
    val locked: Boolean? = false,
    val media: String? = "",
    val media_embed: MediaEmbed? = null,
    val media_only: Boolean? = false,
    val mod_note: String? = "",
    val mod_reason_by: String? = "",
    val mod_reason_title: String? = "",
    val mod_reports: List<String>? = emptyList(),
    val name: String? = "",
    val no_follow: Boolean? = false,
    val num_comments: Int? = 0,
    val num_crossposts: Int? = 0,
    val num_reports: String? = "",
    val over_18: Boolean? = false,
    val parent_whitelist_status: String? = "",
    val permalink: String? = "",
    val pinned: Boolean? = false,
    val poll_data: PollData? = null,
    val pwls: Int? = 0,
    val quarantine: Boolean? = false,
    val removal_reason: String? = "",
    val removed_by: String? = "",
    val removed_by_category: String? = "",
    val report_reasons: String? = "",
    val saved: Boolean? = false,
    val score: Int? = 0,
    // val secure_media: RedditVideoPreview? = null,
    val secure_media_embed: SecureMediaEmbed? = null,
    val selftext: String? = "",
    val selftext_html: String? = "",
    val send_replies: Boolean? = false,
    val spoiler: Boolean? = false,
    val stickied: Boolean? = false,
    val subreddit: String? = "",
    val subreddit_id: String? = "",
    val subreddit_name_prefixed: String? = "",
    val subreddit_subscribers: Int? = 0,
    val subreddit_type: String? = "",
    val suggested_sort: String? = "",
    val thumbnail: String? = "",
    val thumbnail_height: String? = "",
    val thumbnail_width: String? = "",
    val title: String? = "",
    val top_awarded_type: String? = "",
    val total_awards_received: Int? = 0,
    val treatment_tags: List<String>? = emptyList(),
    val ups: Int? = 0,
    val upvote_ratio: Double? = 0.0,
    val url: String? = "",
    val url_overridden_by_dest: String? = "",
    val user_reports: List<String> = emptyList(),
    val view_count: Int? = 0,
    val visited: Boolean? = false,
    val whitelist_status: String? = "",
    val wls: Int? = 0
) : Parcelable
