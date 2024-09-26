package com.example.prm392_miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_miniproject.Object.Runner;
import com.example.prm392_miniproject.Object.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RaceResultActivity extends AppCompatActivity {

    private TextView tvWinnerName, tvTime;
    private RecyclerView rvResults;
    private Button btnPlayAgain;
    private List<Runner> runners;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_result);

        initializeViews();
        loadData();
        displayResults();
        setupPlayAgainButton();
        updateUserWallet();
    }

    private void initializeViews() {
        tvWinnerName = findViewById(R.id.tvWinnerName);
        tvTime = findViewById(R.id.tvTime);
        rvResults = findViewById(R.id.rvResults);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
    }

    private void loadData() {
        Intent intent = getIntent();
        runners = (List<Runner>) intent.getSerializableExtra("runners");
        currentUser = (User) intent.getSerializableExtra("currentUser");

        if (runners == null || runners.isEmpty()) {
            runners = new ArrayList<>();
            Toast.makeText(this, "Không có dữ liệu về cuộc đua", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayResults() {
        if (!runners.isEmpty()) {
            sortRunners();
            displayWinner();
            setupRecyclerView();
        } else {
            Toast.makeText(this, "Không có kết quả đua ngựa", Toast.LENGTH_SHORT).show();
        }
    }

    private void sortRunners() {
        Collections.sort(runners, new Comparator<Runner>() {
            @Override
            public int compare(Runner r1, Runner r2) {
                return Double.compare(r1.getFinishTime(), r2.getFinishTime());
            }
        });
    }

    private void displayWinner() {
        Runner winner = runners.get(0);
        tvWinnerName.setText(winner.getName());
        tvTime.setText(String.format("%.2f giây", winner.getFinishTime()));
    }

    private void setupRecyclerView() {
        RaceResultAdapter adapter = new RaceResultAdapter(runners);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setAdapter(adapter);
    }

    private void setupPlayAgainButton() {
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // TODO: Cập nhật ví của người thắng
    private void updateUserWallet() {
        int winnings = calculateWinnings();
        currentUser.setMoneyInWallet(currentUser.getMoneyInWallet() + winnings);
        updateUserInDatabase(currentUser);
    }

    // TODO: Tính toàn tiền thắng cược
    private int calculateWinnings() {
        return 0;
    }

    private void updateUserInDatabase(User user) {
        // TODO: Xử lý lưu vào đâu đó
    }
}