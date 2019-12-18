package something.interesting.web_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import something.interesting.web_api.Model.LiguesModel;
import something.interesting.web_api.Service.APiClient;

public class MainActivity extends AppCompatActivity {
    private ListView listViewLigues;
    private List<LiguesModel> ligues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewLigues = (ListView) findViewById(R.id.listLigues);

        TextView add = (TextView) findViewById(R.id.add_txt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivity(intent);
            }
        });

        APiClient.getInstance()
                .getAPI()
                .findAll()
                .enqueue(new Callback<List<LiguesModel>>() {
                    @Override
                    public void onResponse(Call<List<LiguesModel>> call, final Response<List<LiguesModel>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Load Successful", Toast.LENGTH_SHORT).show();
                            ligues = (List<LiguesModel>) response.body();

                            listViewLigues.setAdapter(new Adapter(getApplicationContext(), ligues));

                            listViewLigues.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this, detailsActivity.class);
                                    intent.putExtra("id", response.body().get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Server is not returned any data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<LiguesModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "There is no internet connection" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        TextView refresh = (TextView) findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APiClient.getInstance()
                        .getAPI()
                        .findAll()
                        .enqueue(new Callback<List<LiguesModel>>() {
                            @Override
                            public void onResponse(Call<List<LiguesModel>> call, final Response<List<LiguesModel>> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Load Successful", Toast.LENGTH_SHORT).show();
                                    ligues = (List<LiguesModel>) response.body();

                                    listViewLigues.setAdapter(new Adapter(getApplicationContext(), ligues));

                                    listViewLigues.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(MainActivity.this, detailsActivity.class);
                                            intent.putExtra("id", response.body().get(position).getId());
                                            startActivity(intent);
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Server is not returned any data", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<LiguesModel>> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "There is no internet connection" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

}
