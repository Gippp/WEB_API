package something.interesting.web_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import something.interesting.web_api.Model.LiguesDetailsModel;
import something.interesting.web_api.Service.APiClient;

public class detailsActivity extends AppCompatActivity {

    LiguesDetailsModel ligue;
    EditText txtName;
    EditText txtCount;
    EditText txtCounry;
    EditText txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle arguments = getIntent().getExtras();
        final Integer id = (Integer) arguments.get("id") ;


        final TextView txtPut = (TextView) findViewById(R.id.txtNameEdit) ;
        txtName = (EditText) findViewById(R.id.txtName);
        txtCount = (EditText) findViewById(R.id.txtTeamsCount);
//        final TextView txtID = findViewById(R.id.txtID);
        txtCounry = (EditText) findViewById(R.id.txtCounry);
        txtID = (EditText) findViewById(R.id.txtID);

        TextView delete = (TextView) findViewById(R.id.txtNameDelete);

        APiClient.getInstance()
                .getAPI()
                .getligueByID(id)
                .enqueue(new Callback<LiguesDetailsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LiguesDetailsModel> call, @NonNull Response<LiguesDetailsModel> response) {
                        LiguesDetailsModel post = response.body();

                        txtID.append(id+ "");
                        txtName.append(post.getName() + "");
                        txtCount.append(post.getTeams_count() + "");
                        txtCounry.append(post.getCountry() + "");

                    }

                    @Override
                    public void onFailure(@NonNull Call<LiguesDetailsModel> call, @NonNull Throwable t) {

                        txtName.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APiClient.getInstance()
                        .getAPI()
                        .deleteData(id)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Post Deleted" + response.code(), Toast.LENGTH_LONG).show();}
                                else
                                    Toast.makeText(getApplicationContext(), "Post not Deleted" + response.code(), Toast.LENGTH_LONG).show();}

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                txtName.append("Error occurred while getting request!");
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });

            }
        });
                    txtPut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LiguesDetailsModel ldm = new LiguesDetailsModel();

                                ldm.setLigue_id(Integer.parseInt(txtID.getText().toString()));
                                ldm.setName((txtName.getText().toString()));
                                ldm.setTeams_count(Integer.parseInt(txtCount.getText().toString()));
                                ldm.setCountry((txtCounry.getText().toString()));

                                APiClient.getInstance()
                                        .getAPI()
                                        .putdata(id, ldm)
                                        .enqueue(new Callback<LiguesDetailsModel>() {
                                            @Override
                                            public void onResponse(@NonNull Call<LiguesDetailsModel> call, @NonNull Response<LiguesDetailsModel> response) {

                                                Toast.makeText(getApplicationContext(), "Load Successful", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<LiguesDetailsModel> call, @NonNull Throwable t) {

                                                txtName.append("Error occurred while getting request!");
                                                t.printStackTrace();
                                            }
                                        });
                            }});

    }
}
