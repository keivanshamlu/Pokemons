package com.shamlou.shift.ui.shifts.shifts

import com.shamlou.bases_android.recyclerview.adapter.AdapterBase
import com.shamlou.shift.R
import com.shamlou.shift.model.shifts.ResponseShiftDataView

class AdapterShifts : AdapterBase<ResponseShiftDataView>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
) {

    override fun getItemViewType(position: Int): Int = R.layout.item_shifts
}