package com.example.loginmvvm01.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.loginmvvm01.R
import com.example.loginmvvm01.databinding.ActivityUserBinding
import com.example.loginmvvm01.domain.entities.UserDataModel
import com.example.loginmvvm01.domain.entities.UserViewModel
import com.example.loginmvvm01.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewModel: UserViewModel by viewModels()
    private var userList: ArrayList<UserDataModel> = arrayListOf()
    private var adapter: UserListAdapter? = null
    private var existingModel: UserDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)




        binding.btnLogout.setOnClickListener {
            SessionManager.clearData(this)
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        //    val mSwitch = findViewById<Switch>(R.id.switch1)
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.switch1.setText("OnLine")
                //Toast.makeText(applicationContext, "Switch On", Toast.LENGTH_SHORT).show()
            } else {
                binding.switch1.setText("OffLine")
                //Toast.makeText(applicationContext, "Switch Off", Toast.LENGTH_SHORT).show()
            }
        }




        observers()
        setClickListeners()
    }

    private fun observers() {

        viewModel.userList.observe(this) {
            if (it != null && it.isNotEmpty()) {
                userList = it as ArrayList<UserDataModel>
                setUserList(userList)
            } else {
                setUserList(arrayListOf())
            }
        }
    }

    private fun setUserList(list: ArrayList<UserDataModel>) {

        adapter = UserListAdapter(list, listener = object : UserListAdapter.OnPerformAction {

            override fun onEdit(userDataModel: UserDataModel) {
                existingModel = userDataModel
                binding.etName.setText(userDataModel.name)
                binding.etNumber.setText(userDataModel.number)
                binding.btnAdd.text = getString(R.string.update)
            }

            override fun onDelete(userDataModel: UserDataModel) {
                viewModel.deleteUser(userDataModel)
            }
        })
        binding.rvUserList.adapter = adapter
    }

    private fun setClickListeners() {
        binding.btnAdd.setOnClickListener {

            if (isValid()) {

                if (existingModel != null) {
                    viewModel.updateUserDetail(UserDataModel(existingModel!!.id, binding.etName.text.toString(),
                        binding.etNumber.text.toString(),binding.etEmail.text.toString()))
                    existingModel = null
                } else {
                    viewModel.addUser(UserDataModel(Date().time, binding.etName.text.toString(),
                        binding.etNumber.text.toString(),binding.etEmail.text.toString()))
                }

                binding.etName.setText("")
                binding.etNumber.setText("")
                binding.etEmail.setText("")
                binding.btnAdd.text = getString(R.string.add)
            }
        }

    }

    private fun isValid(): Boolean {
        return if (binding.etName.text.toString().trim().isEmpty()) {
            showToast("Please enter name")
            false
        } else if (binding.etNumber.text.toString().trim().isEmpty()) {
            showToast("Please enter number")
            false
        } else {
            true
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}

