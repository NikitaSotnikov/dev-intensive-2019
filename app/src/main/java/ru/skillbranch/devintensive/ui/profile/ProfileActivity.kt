package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R


class ProfileActivity : AppCompatActivity(){

    companion object{
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String,TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews(savedInstanceState)

    }

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields= mapOf(
                "nickname" to tv_nick_name,
                "rank" to tv_rank,
                "first_name" to et_first_name
        )
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

    }

}



