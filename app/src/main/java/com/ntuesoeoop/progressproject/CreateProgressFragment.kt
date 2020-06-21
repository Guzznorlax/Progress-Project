package com.ntuesoeoop.progressproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.create_progress.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [CreateProgressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateProgressFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_progress, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Switch>(R.id.switch_create_view_progress_number_mode).setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            if(switch_create_view_progress_number_mode.isChecked){
                textView3.visibility = View.VISIBLE
                text_input_target_num.visibility = View.VISIBLE
            }else{
                textView3.visibility = View.INVISIBLE
                text_input_target_num.visibility = View.INVISIBLE
            }
        }


        view.findViewById<Button>(R.id.button_create_progress).setOnClickListener {

            var titleTextInputLayout =
                view.findViewById<TextInputEditText>(R.id.text_input_progress_title)

            var descriptionTextInputLayout =
                view.findViewById<TextInputEditText>(R.id.text_input_progress_description)

            var periodTextInputLayout =
                view.findViewById<EditText>(R.id.text_input_progress_period)

            var targetcompletedTextInputLayout =
                view.findViewById<EditText>(R.id.text_input_progress_target_completed)

            var usetargetuumTextInputLayout =
                view.findViewById<Switch>(R.id.switch_create_view_progress_number_mode)

            var targetnumTextInputLayout =
                view.findViewById<EditText>(R.id.text_input_target_num)

            var titleText = titleTextInputLayout?.text.toString()
            var descriptionText = descriptionTextInputLayout?.text.toString()
            var period = periodTextInputLayout?.text.toString()
            var targetcompleted = targetcompletedTextInputLayout.text.toString()
            var usetargetnum = usetargetuumTextInputLayout.isChecked.toString()
            var targetnum = targetnumTextInputLayout?.text.toString()

            var periodNum = 0
            if (period != ""){
                periodNum = period.toInt()
            }

            var targetcompletday = 0
            if(targetcompleted != ""){
                targetcompletday = targetcompleted.toInt()
            }


            var targetnumber = 0
            if(targetnum != ""){
                targetnumber = targetnum.toInt()
            }


            val action =
                CreateProgressFragmentDirections.actionCreateProgressFragmentToFirstFragment(
                    titleText,
                    descriptionText,
                    periodNum,
                    targetcompletday,
                    targetnumber,
                    usetargetnum.toBoolean()
                )
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_cancel_create_progress).setOnClickListener {
            findNavController().navigate(R.id.action_createProgressFragment_to_FirstFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateProgressFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,param3: String) =
            CreateProgressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
