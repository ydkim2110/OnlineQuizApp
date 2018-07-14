package com.example.anti2.onlinequizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anti2.onlinequizapp.Model.QuestionScore;
import com.example.anti2.onlinequizapp.ViewHolder.ScoreDetailViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreDetail extends AppCompatActivity {

    private String viewUser = "";

    private FirebaseDatabase database;
    private DatabaseReference question_score;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");

        recyclerView = (RecyclerView) findViewById(R.id.scoreList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.d("DEBUG", getIntent().getStringExtra("viewUser"));

        if(getIntent() != null){
            viewUser = getIntent().getStringExtra("viewUser");
        }
        if(!viewUser.isEmpty()){
            loadScoreDetail(viewUser);
        }

    }

    private void loadScoreDetail(String viewUser) {

        adapter = new FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder>(
                QuestionScore.class,
                R.layout.score_datail_layout,
                ScoreDetailViewHolder.class,
                question_score.orderByChild("user").equalTo(viewUser)
        ) {
            @Override
            protected void populateViewHolder(ScoreDetailViewHolder viewHolder, QuestionScore model, int position) {
                viewHolder.txt_name.setText(model.getCategoryName());
                viewHolder.txt_score.setText(model.getScore());
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }
}
