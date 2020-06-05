package com.example.healthapplication.adapter

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapplication.R
import com.example.healthapplication.model.QuestionData
import com.example.healthapplication.util.QuestionTypeConstant
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_question_page.view.*
import kotlinx.android.synthetic.main.layout_edit_text.view.*
import kotlinx.android.synthetic.main.layout_radio.view.*


class QuestionPagerAdapter(val questionData: List<QuestionData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return QuestionRadio(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_question_page, parent, false)
        )

    }

    //get the size of color array
    override fun getItemCount(): Int = questionData.size

//    override fun getItemViewType(position: Int): Int {
//        return when(questionData.get(position).questionType){
//            is QuestionTypeConstant().RADIOQUESTION -> QuestionTypeConstant().RADIOTYPE
//            is QuestionTypeConstant().DROPDOWNQUESTION -> QuestionTypeConstant().DROPDOWNTYPE
//            is QuestionTypeConstant().CHECKBOXQUESTION -> QuestionTypeConstant().CHECKBOXTYPE
//            is QuestionTypeConstant().EDITTEXTQUESTION -> QuestionTypeConstant().EDITTEXTTYPE
//            else -> throw IllegalArgumentException("Invalid type of data " + position)
//        }
//    }

    //binding the screen with view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        holder.itemView.run {

            when (holder) {
                is QuestionRadio -> holder.bind(questionData.get(position) as QuestionData)
//            is QuestionRadio -> holder.bind(questionData.get(position) as QuestionData)
//            is QuestionRadio -> holder.bind(questionData.get(position) as QuestionData)
                else -> throw IllegalArgumentException()
            }
        }

    class QuestionRadio(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(questionData: QuestionData) {
            itemView.questionTitle.text = questionData.question
            itemView.questionQuote.text = questionData.questionQuote

            val inflater: LayoutInflater =
                (itemView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            if (questionData.questionType.equals(QuestionTypeConstant().EDITTEXTQUESTION)) {
                val view: View = inflater.inflate(R.layout.layout_edit_text, null)
                view.edtQuestionAnswer.hint = questionData.questionHint

                if (questionData.inputType.equals("textNumber")) {
                    view.edtQuestionAnswer.inputType =
                        InputType.TYPE_CLASS_TEXT
                } else if (questionData.inputType.equals("number")) {
                    view.edtQuestionAnswer.inputType =
                        InputType.TYPE_CLASS_NUMBER
                }
                itemView.questionAnswers.addView(view, 0)
            } else if (questionData.questionType.equals(QuestionTypeConstant().RADIOQUESTION)) {
                val view: View = inflater.inflate(R.layout.layout_radio, null)
                var selectedValue = "Male"

                view.rgQuestionAnswer.addOnButtonCheckedListener {
                        group, checkedId, isChecked ->

                    val listenerButton: MaterialButton = group.findViewById(checkedId)
                    val checkedButton:MaterialButton? = group
                        .findViewById(group.checkedButtonId)

                    if (checkedButton!=null){
                        if (!isChecked){
                            selectedValue = listenerButton.text.toString()
                        }
                    }
                }
                itemView.questionAnswers.addView(view, 0)
            }

//            if (questionData.questionType.equals(QuestionTypeConstant().EDITTEXTQUESTION)) {
//
//
//                itemView.questionAnswers.layoutResource = R.layout.layout_edit_text
//                if (questionData.inputType.equals("textNumber")) {
//                    itemView.questionTitle.inputType =
//                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
//                } else if (questionData.inputType.equals("number")) {
//                    itemView.questionTitle.inputType = InputType.TYPE_CLASS_NUMBER
//                }
//            } else if (questionData.questionType.equals(QuestionTypeConstant().RADIOQUESTION)) {
//                itemView.questionAnswers.layoutResource = R.layout.layout_radio
//            } else if (questionData.questionType.equals(QuestionTypeConstant().DROPDOWNQUESTION)) {
//                itemView.questionAnswers.layoutResource = R.layout.layout_drop_down
//            } else if (questionData.questionType.equals(QuestionTypeConstant().CHECKBOXQUESTION)) {
//                itemView.questionAnswers.layoutResource = R.layout.layout_check_box
//            }
//            itemView.questionAnswers.inflate()
        }
    }

}

