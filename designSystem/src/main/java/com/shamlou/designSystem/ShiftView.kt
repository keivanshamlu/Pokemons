package com.shamlou.designSystem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.shamlou.designSystem.databinding.ShiftViewBinding

/**
 * represents UI of a shift, since we made a
 * custom view, we can share it between other
 * feature modules, design system module can
 * be a different project to be used between projects
 */
class ShiftView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var mBinding: ShiftViewBinding? = null

    init {

        initView()
        addView(mBinding?.root)
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = ShiftViewBinding.inflate(inflater)
    }

    fun setShiftTitle(shiftTitle: String?) {

        mBinding?.textViewShiftTitle?.text = shiftTitle
    }

    fun setStartAndEnd(startAndEnd: String?) {

        mBinding?.textViewShiftStartAndEndAt?.text = startAndEnd
    }

    fun setEarningsAmount(earningsAmount: String?) {

        mBinding?.textViewShiftEarningsAmount?.text = earningsAmount
    }

    fun setExtraBriefing(extraBriefing: String?) {

        mBinding?.textViewShiftExtraBriefing?.text = extraBriefing
    }

    /**
     * i couldn't load shift image since it requires authentication
     */
    fun setImageUrl(imageUrl: String) {

    }

}
