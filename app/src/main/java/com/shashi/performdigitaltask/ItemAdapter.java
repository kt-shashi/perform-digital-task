package com.shashi.performdigitaltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shashi.performdigitaltask.data.RecyclerViewData;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {

    private final List<RecyclerViewData> items = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        //Check for viewType
        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.item_square, parent, false);
            return new ViewHolderSquare(view);
        } else if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.item_circle, parent, false);
            return new ViewHolderCircle(view);
        } else {
            view = layoutInflater.inflate(R.layout.item_rectangle, parent, false);

            // To make View Type 3 as Full Span
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    final ViewGroup.LayoutParams lp = view.getLayoutParams();
                    if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                        StaggeredGridLayoutManager.LayoutParams sglp =
                                (StaggeredGridLayoutManager.LayoutParams) lp;

                        sglp.setFullSpan(true);

                        view.setLayoutParams(sglp);
                        final StaggeredGridLayoutManager lm =
                                (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
                        assert lm != null;
                        lm.invalidateSpanAssignments();
                    }
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });

            return new ViewHolderRectangle(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (items.get(position).getData().equals("View\nType\n1")) {

            ViewHolderSquare viewHolderSquare = (ViewHolderSquare) holder;
            viewHolderSquare.textView.setText(items.get(position).getData());

        } else if (items.get(position).getData().equals("View\nType\n2")) {

            ViewHolderCircle viewHolderCircle = (ViewHolderCircle) holder;
            viewHolderCircle.textView.setText(items.get(position).getData());

        } else {

            ViewHolderRectangle viewHolderRectangle = (ViewHolderRectangle) holder;
            viewHolderRectangle.textView.setText(items.get(position).getData());

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (items.get(position).getData().equals("View\nType\n1")) {
            return 0;
        } else if (items.get(position).getData().equals("View\nType\n2")) {
            return 1;
        } else
            return 2;
    }

    //Update items
    public void updateList(ArrayList<RecyclerViewData> updatedItems) {
        items.clear();
        items.addAll(updatedItems);

        notifyDataSetChanged();
    }

    // ViewHolder for View Type 1 (Square)
    static class ViewHolderSquare extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderSquare(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewSquare);
        }
    }

    // ViewHolder for View Type 2 (Circle)
    static class ViewHolderCircle extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderCircle(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewCircle);
        }
    }

    // ViewHolder for View Type 3 (Rectangle)
    static class ViewHolderRectangle extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderRectangle(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewRectangle);
        }
    }

}
