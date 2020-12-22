package com.kylix.submissionbajp3.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.kylix.submissionbajp3.R

class FakeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_main)
        val content = FrameLayout(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        content.layoutParams = params
        content.id = R.id.container

        setContentView(content)
    }
    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, "TEST")
            .commit()
    }
}