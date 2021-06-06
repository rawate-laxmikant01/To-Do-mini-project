package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Detail;
import com.example.todo.R;

import java.util.ArrayList;

import model.Model;
import model.ModelToDo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    ArrayList<ModelToDo> list;
    Context mcontext;


    public RecyclerViewAdapter(ArrayList<ModelToDo> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_custom_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ModelToDo itemmodel=list.get(position);
        holder.tv_Date.setText(list.get(position).getDate());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_des.setText(list.get(position).getDescription());
      //  holder.tv_id.setText(list.get(position).getID());

        final String date=itemmodel.getDate();
        final  String title=itemmodel.getTitle();
        final String description=itemmodel.getDescription();
        final String id=itemmodel.getID();


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    String date=date.toString().trim();
//                    String title=tv_title.toString().trim();
//                    String description=tv_des.toString().trim();

                Toast.makeText(mcontext, "Next", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(mcontext,Detail.class);
                intent.putExtra("date_detail",date);
                intent.putExtra("title_detail",(title));
                intent.putExtra("description",(description));
                intent.putExtra("id",id);

                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Date,tv_title,tv_des;
        ImageView tv_detail;
        View cardview;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Date=itemView.findViewById(R.id.tv_date_id);
            tv_title=itemView.findViewById(R.id.tv_title_id);
            tv_des=itemView.findViewById(R.id.tv_des_id);
          //  tv_id=itemView.findViewById(R.id.tv_dataID_id);
            tv_detail=itemView.findViewById(R.id.btn_detail);
            cardview=itemView.findViewById(R.id.cardview_id);



        }
    }
}
