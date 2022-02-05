package `in`.aalap.gitxplorer.fragments

import `in`.aalap.gitxplorer.R
import `in`.aalap.gitxplorer.network.RetrofitConfig
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_button.setOnClickListener {
            if (user_name_github.text?.toString().isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please Fill in UserName", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val args = Bundle()
                args.putString("UserName", user_name_github?.text?.toString())
                it.findNavController().navigate(R.id.action_loginFragment_to_userFragment, args)
            }
        }
    }


}