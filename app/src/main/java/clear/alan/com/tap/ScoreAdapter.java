package clear.alan.com.tap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alan on 7/21/2018.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder> {

List<Score> arrayList;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_tv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName().toString());
        holder.score.setText(arrayList.get(position).getScore().toString());
    }

    public ScoreAdapter(List<Score> list) {
        this.arrayList=list;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, score;
        public MyViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv1);
            score= itemView.findViewById(R.id.tv2);

        }
    }
}

