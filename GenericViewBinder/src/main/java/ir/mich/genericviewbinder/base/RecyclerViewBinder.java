package ir.mich.genericviewbinder.base;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewBinder<Model, VB extends ViewBinding>
        extends RecyclerView.Adapter<RecyclerViewBinder.Holder<Model>>
        implements Filterable {

    /**
     * Do this:
     * <p>
     * In the Android Manifest file, declare the following.
     * <p>
     * <application
     * ...
     * android:name="ir.mich.genericviewbinder.base.App"
     * >
     * </application>
     */
    protected Activity activity;
    protected Context context;
    protected VB binding;
    protected View view;
    private List<Model> models = new ArrayList<>();
    private List<Model> filteredModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layout;

    public RecyclerViewBinder(RecyclerView recyclerView) {
        this(recyclerView, new LinearLayoutManager(App.getContext()));
    }

    public RecyclerViewBinder(RecyclerView recyclerView, RecyclerView.LayoutManager layout) {
        this.recyclerView = recyclerView;
        this.layout = layout;
        recyclerView.setLayoutManager(new LinearLayoutManager(App.getActivity()));
        init();
    }

    protected void init() {
    }

    @NonNull
    @Override
    public Holder<Model> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        binding = new GenericBinder<VB>(this, 1).inflate(LayoutInflater.from(context), viewGroup);
        activity = (Activity) context;
        view = binding.getRoot();
        return new Holder<Model>(view) {
            @Override
            public void bind(Model model, int position) {
                onBindViewHolder(model, position);
            }
        };
    }

    public abstract void onBindViewHolder(Model model, int position);

    @Override
    public int getItemCount() {
        return (models != null) ? models.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull Holder<Model> holder, int position) {
        holder.bind(models.get(position), position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressLint("NotifyDataSetChanged")
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredModels = (List<Model>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Model> filteredResults;
                if (constraint.length() == 0) {
                    filteredResults = models;
                } else {
                    filteredResults = getFilteredResults(
                            constraint.toString().toLowerCase(),
                            filterBy());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected abstract FilterBy<Model> filterBy();

    public List<Model> getFilteredResults(String constraint, FilterBy<Model> filterBy) {
        List<Model> results = new ArrayList<>();
        for (Model item : models) {
            if (filterBy.item(item).toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }

    public List<Model> getList() {
        return models;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Model> models) {
        recyclerView.setAdapter(this);
        this.models = models;
        notifyDataSetChanged();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public RecyclerView.LayoutManager getLayout() {
        return layout;
    }

    public void setLayout(RecyclerView.LayoutManager layout) {
        this.layout = layout;
    }

    protected interface FilterBy<Model> {
        String item(Model model);
    }

    public static abstract class Holder<Model> extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(Model item, int position);
    }
}
