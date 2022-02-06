package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseJobRemote(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("extra_briefing") var extraBriefing: String? = null,
    @SerializedName("report_at_address") var reportAtAddress: ResponseReportAtAddressRemote? = null,
)