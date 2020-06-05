package com.example.healthapplication.model

data class QuestionListData(
    val questionData: List<QuestionData>
)

data class QuestionData(
    val answer: List<Answer>,
    val question: String,
    val questionQuote: String,
    val questionId: Int,
    val questionType: String,
    val questionHint: String,
    val inputType: String,
    val subQuestion: List<SubQuestion>
)

data class Answer(
    val answerId: Int,
    val answerOption: String
)

data class SubQuestion(
    val questionData: List<QuestionData>
)
