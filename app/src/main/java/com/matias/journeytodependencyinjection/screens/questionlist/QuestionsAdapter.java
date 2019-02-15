package com.matias.journeytodependencyinjection.screens.questionlist;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private final QuestionsListActivity.OnQuestionClickListener mOnQuestionClickListener;

    private List<Question> listQuestions = new ArrayList<>(0);

    QuestionsAdapter(QuestionsListActivity.OnQuestionClickListener onQuestionClickListener) {
        mOnQuestionClickListener = onQuestionClickListener;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        QuestionViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
        }
    }

    void bindData(List<Question> questions) {
        listQuestions = new ArrayList<>(questions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionsAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question_list, parent, false);

        return new QuestionsAdapter.QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        holder.tvTitle.setText(listQuestions.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnQuestionClickListener.onQuestionClicked(listQuestions.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }
}
