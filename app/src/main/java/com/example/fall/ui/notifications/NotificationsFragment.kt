package com.example.fall.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fall.ChangePasswordActivity
import com.example.fall.R
import com.example.fall.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNn = view.findViewById<EditText>(R.id.editTextNickName)
        val etSex = view.findViewById<EditText>(R.id.editTextSex)
        etSex.setOnKeyListener(null)
        val etUsr = view.findViewById<EditText>(R.id.editTextUsrNm)
        val etAge = view.findViewById<EditText>(R.id.editTextNumberAge)
        val tvChangePass = view.findViewById<TextView>(R.id.textViewPass)

        tvChangePass.setOnClickListener {
            val intent = Intent(view.context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}