package something.interesting.web_api;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import something.interesting.web_api.Model.LiguesDetailsModel;
import something.interesting.web_api.R;
import something.interesting.web_api.Service.APiClient;


public class addActivity extends AppCompatActivity {

    LiguesDetailsModel ligue;
    EditText txtName;
    EditText txtCount;
    EditText txtCounry;
    EditText txtID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        final TextView txtPut = (TextView) findViewById(R.id.txtNameEdit) ;
        txtName = (EditText) findViewById(R.id.txtName);
        txtCount = (EditText) findViewById(R.id.txtTeamsCount);
//        final TextView txtID = findViewById(R.id.txtID);
        txtCounry = (EditText) findViewById(R.id.txtCounry);
        txtID = (EditText) findViewById(R.id.txtID);




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
                        .postData(ldm)
                        .enqueue(new Callback<LiguesDetailsModel>() {
                            @Override
                            public void onResponse(@NonNull Call<LiguesDetailsModel> call, @NonNull Response<LiguesDetailsModel> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Данные обновлены" + response.code(), Toast.LENGTH_LONG).show();}
                                else
                                    Toast.makeText(getApplicationContext(), "Данные не обновлены" + response.code(), Toast.LENGTH_LONG).show();}



                            @Override
                            public void onFailure(@NonNull Call<LiguesDetailsModel> call, @NonNull Throwable t) {

                                txtName.append("Error occurred while getting request!");
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });
            }
        });


    }

}