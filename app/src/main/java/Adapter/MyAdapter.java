package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a300276335.fitflex.ExerciseActivity;
import com.example.a300276335.fitflex.NutriSuppsActivity;
import com.example.a300276335.fitflex.R;
import com.example.a300276335.fitflex.WorkoutActivity;

import java.util.List;

import Model.ListItem;

/**
 * Created by 300276335 on 5/29/2018.
 */

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder> {

   private Context context;
   //List is a superclass of ArrayList
   private List<ListItem> ListItems;

    public MyAdapter(Context context, List<ListItem> listItem) {
        this.context = context;
        ListItems = listItem;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        //    import com.example.a300276335.recyclerviewdemo.R;
    return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        ListItem listItem = ListItems.get(position);
        holder.name.setText(listItem.getName());
        holder.name.setBackgroundResource(listItem.getImg());
        //holder.description.setText(listItem.getDescription());
    }


    @Override
    public int getItemCount() {
        return ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
       // public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.txtViewItem);
            //name.setTextColor(Color.WHITE);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ListItem item = ListItems.get(position);
            //Toast.makeText(context,item.getName(), Toast.LENGTH_LONG).show();

            switch(position){
                case 0:
                    view.getContext().startActivity(new Intent(view.getContext(), ExerciseActivity.class));
                    break;
                case 1:
                    view.getContext().startActivity(new Intent(view.getContext(), WorkoutActivity.class));
                    break;
                case 2:

                    view.getContext().startActivity(new Intent(view.getContext(), NutriSuppsActivity.class));
                    break;
                case 3:
                    view.getContext().startActivity(new Intent(view.getContext(), NutriSuppsActivity.class).putExtra("go_to", 1));
                    //view.getContext().startActivity(new Intent(view.getContext(), NutriSuppsActivity.class));
                    break;



            }
            //you can do anything over here like starting a new activity
        }
    }
}
