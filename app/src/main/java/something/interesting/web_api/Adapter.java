package something.interesting.web_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import something.interesting.web_api.Model.LiguesModel;


public class Adapter extends ArrayAdapter<LiguesModel> {
    private Context context;
    private List<LiguesModel> ligues;

    public Adapter(Context context, List<LiguesModel> ligues){
        super(context, R.layout.all_ligues, ligues);
        this.context = context;
        this.ligues = ligues;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.all_ligues, parent, false);

        LiguesModel liguesModel = ligues.get(position);

        TextView txt_name = (TextView) convertView.findViewById(R.id.team_name);
        txt_name.setText(liguesModel.getName());

        return convertView;
    }
}
