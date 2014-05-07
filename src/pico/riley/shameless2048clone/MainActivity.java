package pico.riley.shameless2048clone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	static final String logTag = "ActivitySwipeDetector";
	static final int MIN_DISTANCE = 100;
    private RelativeLayout lowestLayout;
    private GridView gameView;
    private Game game;
    private TileAdapter gameAdapter;
    private TextView scoreText;
    private TextView bestText;
    private TextView gameOverText;
    private int score = 0;
    private int bestScore = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        game = new Game();
        
        gameAdapter = new TileAdapter(this, R.layout.tilelayout, game);
        lowestLayout = (RelativeLayout)this.findViewById(R.id.lowestLayout);
        scoreText = (TextView)this.findViewById(R.id.scoreText);
        bestText = (TextView)this.findViewById(R.id.bestScoreText);
        gameOverText = (TextView)this.findViewById(R.id.gameOverText);
        gameView = (GridView)this.findViewById(R.id.grid_view);

    	scoreText.setText(Integer.toString(score));
		bestText.setText(Integer.toString(bestScore));
        ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(gameAdapter, game, this);
        lowestLayout.setOnTouchListener(activitySwipeDetector);
        gameView.setOnTouchListener(activitySwipeDetector);
        gameView.setAdapter(gameAdapter);
    }
    
    public void loseGame()
    {
    	gameOverText.setVisibility(View.VISIBLE);
    }
    
    public void updateScore()
    {
    	score = game.getScore();
    	scoreText.setText(Integer.toString(score));
    	if (score > bestScore)
    	{
    		bestScore = score;
    		bestText.setText(Integer.toString(bestScore));
    	}
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
