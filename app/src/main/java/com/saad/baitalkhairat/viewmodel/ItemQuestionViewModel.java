
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.databinding.CellCommonQuastionsBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.Question;


public class ItemQuestionViewModel extends BaseObservable {

    private final Context context;
    CellCommonQuastionsBinding cellCommonQuastionsBinding;
    private Question question;
    private int position;

    public ItemQuestionViewModel(Context context, Question question, int position, CellCommonQuastionsBinding cellCommonQuastionsBinding) {
        this.context = context;
        this.question = question;
        this.position = position;
        this.cellCommonQuastionsBinding = cellCommonQuastionsBinding;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
        notifyChange();
    }

    public void onItemClick(View view) {
//        mRecyclerClick.onClick(question, position);
        if (cellCommonQuastionsBinding.tvAnswer.getVisibility() == View.VISIBLE) {
            cellCommonQuastionsBinding.tvAnswer.setVisibility(View.GONE);
            GeneralFunction.rotateImageView(cellCommonQuastionsBinding.imgIcon, true);
        } else {
            cellCommonQuastionsBinding.tvAnswer.setVisibility(View.VISIBLE);
            GeneralFunction.rotateImageView(cellCommonQuastionsBinding.imgIcon, false);
        }
    }

}
