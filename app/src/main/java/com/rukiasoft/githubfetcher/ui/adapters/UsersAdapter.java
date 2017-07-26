package com.rukiasoft.githubfetcher.ui.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.BR;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityListBinding;
import com.rukiasoft.githubfetcher.databinding.UserBasicItemBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;


import java.util.List;

/**
 * Created by Roll on 21/7/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.BindingHolder>
implements View.OnClickListener{
    private List<UserBasic> mUsers;
    private Context mContext;

    private OnCardClickListener onCardClickListener;

    @Override
    public void onClick(final View view) {
        // Give some time to the ripple to finish the effect
        if (onCardClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onCardClickListener.onCardClick(view, (UserBasic) view.getTag());
                }
            }, 200);
        }

    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }

    public UsersAdapter(Context context, List<UserBasic> recyclerUsers) {
        this.mUsers = recyclerUsers;
        this.mContext = context;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        UserBasicItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_basic_item, parent, false);
        View v = binding.getRoot();
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.user_basic_item, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final UserBasic user = mUsers.get(position);
        holder.getBinding().setVariable(BR.user, user);
        UserBasicItemBinding userBinding = (UserBasicItemBinding) holder.getBinding();
        ImageView imageView = userBinding.profilePic;
        CardView card = userBinding.cardView;
        //ImageView imageView = holder.getBinding().getRoot().findViewById(R.id.profile_pic);
        //CardView card = holder.getBinding().getRoot().findViewById(R.id.card_view);
        card.setOnClickListener(this);
        card.setTag(user);
        Glide.with(mContext)
                .load(user.getAvatarUrl())
                .apply(circleCropTransform()).into(imageView);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface OnCardClickListener{
        void onCardClick(View view, UserBasic user);
    }
}


