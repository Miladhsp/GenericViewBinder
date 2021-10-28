package ir.mich.genericviewbinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class SupperRecyclerViewAdapter<Model, VB extends ViewBinding>
        extends RecyclerView.Adapter<SupperRecyclerViewAdapter.Holder<Model>>
        implements Filterable {

    protected Context context_static = SupperActivity.context_static;
    private final ArrayList<Model> models;
    private ArrayList<Model> filteredModels;
    protected Context context;
    protected VB binding;

    public SupperRecyclerViewAdapter(ArrayList<Model> models) {
        this.models = models;
        this.filteredModels = models;
    }

    @NonNull
    @Override
    public Holder<Model> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        binding = new GenericBinder<VB>(this, 1).inflate(LayoutInflater.from(context), viewGroup);
        return new Holder<Model>(binding.getRoot()) {
            @Override
            public void bind(Model model) {
                onBindViewHolder(model);
            }
        };
    }

    public abstract void onBindViewHolder(Model model);

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder<Model> holder, int position) {
        holder.bind(models.get(position));
    }

    public static abstract class Holder<Model> extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(Model item);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredModels = (ArrayList<Model>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Model> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = models;
                } else {
                    filteredResults = (List<Model>) getFilteredResults(constraint.toString().toLowerCase(),
                            getResult()
                    );
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected abstract Result<Model> getResult();

    public ArrayList<Model> getFilteredResults(String constraint, Result<Model> result) {
        ArrayList<Model> results = new ArrayList<>();
        for (Model item : models) {
            if (result.filterBy(item).toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }

    protected interface Result<Model> {
        String filterBy(Model model);
    }

/*    protected List<Model> getFilteredResults(String constraint) {
        List<Model> results = new ArrayList<>();
        for (Model item : models) {
            if (item.getName().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }*/

}
